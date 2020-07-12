import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *  Main class for Grocery Hash
 */
public class HashedGrocery {
    private int businessDay = 0;
    private HashMap<String, Item> itemHashMap = new HashMap<>();

    /**
     *  Default Constructor
     */
    public HashedGrocery() {}

    /**
     * @param item
     *  The Item to add
     * @throws ItemAlreadyExistsException
     *  If the item with a previous defined ItemCode exists
     */
    public void addItem(Item item) throws ItemAlreadyExistsException {
        if (itemHashMap.containsKey(item.getItemCode())) {
            throw new ItemAlreadyExistsException();
        }
        this.itemHashMap.put(item.getItemCode(), item);
    }

    /**
     * @param item
     *  The Item to update
     * @param adjustByQty
     *  The quantity to update
     */
    public void updateItem(Item item, int adjustByQty)
            throws ItemNotFoundException, UpdateFailedException {
        // Check if there is a key for the item
        if (!itemHashMap.containsKey(item.getItemCode()))  {
            throw new ItemNotFoundException();
        }
        // Initiate a temp copy of the item
        Item temp = itemHashMap.get(item.getItemCode());
        // If the new quantity is < 0, throw an error
        if (temp.getQtyInStore() < adjustByQty) {
            throw new UpdateFailedException();
        }
        // Set the quantity in store of the item
        temp.setQtyInStore(item.getQtyInStore() + adjustByQty);
        itemHashMap.replace(temp.getItemCode(), temp);
    }

    /**
     * @param filename
     *  The file to get the Items
     * @throws ParseException
     *  The Object can't be parsed
     * @throws IOException
     *  File I/O couldn't find the file
     */
    public void addItemCatalog(String filename)
            throws ParseException, IOException {
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis);
        JSONParser parser = new JSONParser();
        JSONArray objs = (JSONArray) parser.parse(isr);
        for (Object o : objs) {
            JSONObject obj = (JSONObject) o;
            String itemCode = (String) obj.get("itemCode");
            if (itemHashMap.containsKey(itemCode)) {
                System.out.println(String.format("%s: Already exists.",
                        itemCode));
                continue;
            }
            String name = (String) obj.get("itemName");
            int qtyInStore = Integer.parseInt((String) obj.get("qtyInStore"));
            int averageSalesPerDay = Integer.parseInt((String) obj.get("avgSales"));
            int onOrder = Integer.parseInt((String) obj.get("amtOnOrder"));
            double price = Double.parseDouble((String) obj.get("price"));
            Item newItem = new Item(itemCode, name, qtyInStore,
                    averageSalesPerDay, onOrder, price);
            itemHashMap.put(itemCode, newItem);
            System.out.println(String.format("%s: %s is added to the " +
                    "inventory.", itemCode, name));
        }
    }

    /**
     * @param fileName
     *  The file that processes the sales already in
     * @throws IOException
     *  File not found or File I/O Error
     * @throws ParseException
     *  Can't get the values from files or other error
     */
    public void processSales(String fileName)
            throws IOException, ParseException {
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(fis);
        JSONParser parser = new JSONParser();
        JSONArray objs = (JSONArray) parser.parse(isr);
        for (Object o: objs) {
            JSONObject obj = (JSONObject) o;
            String itemCode = (String) obj.get("itemCode");
            int qtySold = Integer.parseInt((String) obj.get("qtySold"));
            if (!itemHashMap.containsKey(itemCode)) {
                System.out.println(String.format("%s: Cannot buy as it is not " +
                        "in the grocery store.", itemCode));
                continue;
            }
            Item getItem = itemHashMap.get(itemCode);
            // Quantity Check: If qtySold > qty in Store
            if (getItem.getQtyInStore() < qtySold) {
                System.out.println(String.format("%s: Not enough stock for " +
                        "sale." +
                        " Not updated.", getItem.getItemCode()));
                continue;
            }
            // Check for min. qty in stock and arrivalDay is 0
            if (getItem.getQtyInStore() <= 3*getItem.getAverageSalesPerDay() && getItem.getArrivalDay() <= 0) {
                System.out.println(String.format("%s: %s units of %s are " +
                        "sold. Order has been placed for %s more units.",
                        getItem.getItemCode(),
                        qtySold,
                        getItem.getName(),
                        (getItem.getOnOrder() + 2*getItem.getAverageSalesPerDay())
                        )
                );
                getItem.setArrivalDay(this.businessDay + 3); // Sets the
                // Arrival Day
                getItem.setOnOrder(2*getItem.getAverageSalesPerDay());
                // Check if the item is already being delivered
            } else if (getItem.getQtyInStore() < 3*getItem.getAverageSalesPerDay()
                    && getItem.getArrivalDay() > 0) {
                System.out.println(String.format("%s: %s units of %s are " +
                                "sold. Order has been placed for %s more units.",
                        getItem.getItemCode(),
                        qtySold,
                        getItem.getName(),
                        (2*getItem.getAverageSalesPerDay())
                        )
                );
                // Otherwise, there is enough in stock and print normally.
            } else {
                System.out.println(String.format("%s: %s units of %s are " +
                                "sold.",
                        getItem.getItemCode(),
                        qtySold,
                        getItem.getName()
                ));
            }
            // Now Update The qty and replace it back to the hash map
            getItem.setQtyInStore(getItem.getQtyInStore() - qtySold);
            itemHashMap.replace(getItem.getItemCode(), getItem);
        }
    }

    /**
     *  Attempts to goes to the next business day and checks for deliveries
     */
    public void nextBusinessDay() {
        this.businessDay++;
        System.out.println(String.format("\nAdvancing business day...\n" +
                "Business Day %s.", this.businessDay));
        StringBuilder deliveries = new StringBuilder();
        // Check for any deliveries
        for (Map.Entry<String, Item> entry : itemHashMap.entrySet()) {
            Item item = entry.getValue();
            if (item.getArrivalDay() == this.businessDay) {
                deliveries.append(String.format("%s: %s units of %s.\n",
                        item.getItemCode(), item.getOnOrder(), item.getName()));
                item.setQtyInStore(item.getQtyInStore() + item.getOnOrder());
                item.setOnOrder(0);
                item.setArrivalDay(0);
                itemHashMap.replace(item.getItemCode(), item);
            }
        }
        if (deliveries.length() > 0) {
            System.out.println("\nOrders have arrived for:");
            System.out.println(deliveries.toString());
        } else {
            System.out.println("No orders have arrived.");
        }
    }

    /**
     * @return
     *  Returns the HashedGrocery as String
     */
    @Override
    public String toString() {
        String outputFormat = "%-12s%-27s%-6s%-11s%-9s%-11s%s";
        String header = String.format(outputFormat, "Item Code", "Name", "Qty",
                "AvgSales", "Price", "OnOrder", "ArrOnBusDay");
        header += "\n" + "-----------------------------------------------------" +
                "---------------------------\n";
        StringBuilder outputReturn = new StringBuilder(header);
        for (Map.Entry<String, Item> entry: itemHashMap.entrySet()) {
            Item item = entry.getValue();
            outputReturn.append(String.format(outputFormat,
                    item.getItemCode(),
                    item.getName(),
                    item.getQtyInStore(),
                    item.getAverageSalesPerDay(),
                    item.getPrice(),
                    item.getOnOrder(),
                    item.getArrivalDay()
            )).append("\n");
        }
        return outputReturn.toString();
    }

    /**
     * @return
     *  Gets the business day
     */
    public int getBusinessDay() {
        return businessDay;
    }

    /**
     * @param businessDay
     *  Sets the business day
     */
    public void setBusinessDay(int businessDay) {
        this.businessDay = businessDay;
    }

    /**
     * @return
     *  Gets the HashMap
     */
    public HashMap<String, Item> getItemHashMap() {
        return itemHashMap;
    }

    /**
     * @param itemHashMap
     *  Sets the HashMap
     */
    public void setItemHashMap(HashMap<String, Item> itemHashMap) {
        this.itemHashMap = itemHashMap;
    }
}

/**
 *  Exception class for item with the same item code (if it exists)
 */
class ItemAlreadyExistsException extends Exception {
    public ItemAlreadyExistsException() {}
}

/**
 *  Exception class for not having
 */
class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {}
}

/**
 *  Exception class for failing to update quantity in store
 */
class UpdateFailedException extends Exception {
    public UpdateFailedException() {}
}
/**
 *  Main class for Item
 */
public class Item {
    private String itemCode = "";
    private String name = "";
    private int qtyInStore = 0;
    private int averageSalesPerDay = 0;
    private int onOrder = 0;
    private int arrivalDay = 0;
    private double price = 0.0;

    /**
     *  Default Constructor
     */
    public Item() {}

    public Item(String itemCode, String name, int qtyInStore,
                int averageSalesPerDay, int onOrder, double price) {
        this.itemCode = itemCode;
        this.name = name;
        this.qtyInStore = qtyInStore;
        this.averageSalesPerDay = averageSalesPerDay;
        this.onOrder = onOrder;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", qtyInStore=" + qtyInStore +
                ", averageSalesPerDay=" + averageSalesPerDay +
                ", onOrder=" + onOrder +
                ", arrivalDay=" + arrivalDay +
                ", price=" + price +
                '}';
    }

    /**
     * @return
     *  Gets the unique code of the Item
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @return
     *  Gets the price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return
     *  Gets the arrival day of the item
     */
    public int getArrivalDay() {
        return arrivalDay;
    }

    /**
     * @return
     *  Gets the average sales per day of the item
     */
    public int getAverageSalesPerDay() {
        return averageSalesPerDay;
    }

    /**
     * @return
     *  Gets the number of ordered for restocking
     */
    public int getOnOrder() {
        return onOrder;
    }

    /**
     * @return
     *  Gets the number of items already in the store
     */
    public int getQtyInStore() {
        return qtyInStore;
    }

    /**
     * @return
     *  Gets the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * @param arrivalDay
     *  Sets the arrival day
     */
    public void setArrivalDay(int arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    /**
     * @param averageSalesPerDay
     *  Sets the average sales per day
     */
    public void setAverageSalesPerDay(int averageSalesPerDay) {
        this.averageSalesPerDay = averageSalesPerDay;
    }

    /**
     * @param itemCode
     *  Sets the item code
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @param name
     *  Sets the name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param onOrder
     *  Sets the number of items to be restocked
     */
    public void setOnOrder(int onOrder) {
        this.onOrder = onOrder;
    }

    /**
     * @param price
     *  Sets the price of the item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param qtyInStore
     *  Sets the number of items already in store
     */
    public void setQtyInStore(int qtyInStore) {
        this.qtyInStore = qtyInStore;
    }
}

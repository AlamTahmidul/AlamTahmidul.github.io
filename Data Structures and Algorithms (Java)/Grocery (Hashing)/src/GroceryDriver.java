import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

/**
 *  Main class for GroceryDriver
 */
public class GroceryDriver {
    /**
     * @param args
     *  Main function with possible CLI arguments
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        HashedGrocery hashedGrocery = new HashedGrocery();
        hashedGrocery.setBusinessDay(1);
        System.out.println("Business Day " + hashedGrocery.getBusinessDay());
        String menu = "Menu :\n" +
                "(L) Load item catalog    \n" +
                "(A) Add items              \n" +
                "(B) Process Sales      \n" +
                "(C) Display all items\n" +
                "(N) Move to next business day  \n" +
                "(Q) Quit    \n" +
                " \n" +
                "Enter option: ";
        while (true) {
            try {
                System.out.print("\n" + menu);
                char c = stdin.next().charAt(0);
                switch (Character.toLowerCase(c)) {
                    case 'l':
                        System.out.print("Enter file to load: ");
                        String filename = stdin.next();
                        filename = "./resources/" + filename;
//                    System.out.println(filename);
                        try {
                            hashedGrocery.addItemCatalog(filename);
                            System.out.println();
                        } catch (ParseException e) {
                            System.out.println("File can't be parsed!");
                            stdin.nextLine();
                        } catch (IOException e) {
                            System.out.println("File can't be opened! Or file dir" +
                                    " error.");
                            stdin.nextLine();
                        }
                        break;
                    case 'a':
                        String itemCode, itemName;
                        int qtyInStore, avgSales;
                        double price;
                        System.out.print("\nEnter item code: ");
                        itemCode = stdin.next();
//                        System.out.println("Press Enter to Continue...");
                        stdin.nextLine();
                        System.out.print("\nEnter item name: ");
                        itemName = stdin.nextLine();
                        System.out.print("\nEnter Quantity in Store: ");
                        qtyInStore = stdin.nextInt();
                        System.out.print("\nEnter Average sales per day: ");
                        avgSales = stdin.nextInt();
                        System.out.print("\nEnter price: ");
                        price = stdin.nextDouble();
                        Item newItem = new Item(itemCode, itemName, qtyInStore,
                                avgSales, 0, price);
                        try {
                            hashedGrocery.addItem(newItem);
                        } catch (ItemAlreadyExistsException e) {
                            System.out.println(itemCode + " already exists!");
                            stdin.nextLine();
                        }
                        break;
                    case 'b':
                        System.out.print("Enter filename: ");
                        String filenameToProcess = stdin.next();
                        filenameToProcess = "./resources/" + filenameToProcess;
                        try {
                            hashedGrocery.processSales(filenameToProcess);
                        } catch (IOException e) {
                            System.out.println("File can't be opened! Or file dir" +
                                    " error.");
                            stdin.nextLine();
                        } catch (ParseException e) {
                            System.out.println("File can't be parsed!");
                            stdin.nextLine();
                        }
                        break;
                    case 'c':
                        System.out.println(hashedGrocery.toString());
                        break;
                    case 'n':
                        hashedGrocery.nextBusinessDay();
                        break;
                    case 'q':
                        System.out.println("Program terminating normally...");
                        System.exit(1);
                    default:
                        System.out.println("Invalid command!");
                }
            } catch (Exception e) {
                System.out.println("Sorry, Something Went Wrong. Try again.");
                stdin.nextLine();
            }
        } // While loop end
    }
}

import java.io.*;
import java.util.Collections;
import java.util.Scanner;

/**
 *  @name Tahmidul Alam
 *  @ID 112784865
 *  @email tahmidul.alam@stonybrook.edu
 *  @course CSE 214
 *  @recitation Recitation 10 (Daniel Calabria)
 *
 *  Main Class for Transplant
 */
public class TransplantDriver {
    public static final String DONOR_FILE = "donors.txt";
    public static final String RECIPIENT_FILE = "recipients.txt";

    /**
     * @param args
     *  Main function
     */
    public static void main(String[] args) {
        boolean debug = true;
        TransplantGraph transplantGraph;
        try {
            System.out.println("Loading data from transplant.obj... ");
            FileInputStream file = new FileInputStream("./resources" +
                    "/transplant.obj");
            ObjectInputStream fin  = new ObjectInputStream(file);
            transplantGraph = (TransplantGraph) fin.readObject();
            fin.close();
        } catch(IOException | ClassNotFoundException e) {
            System.out.println("transplant.obj not found. Creating new " +
                    "TransplantGraph object...");
            transplantGraph = TransplantGraph.buildFromFiles(DONOR_FILE, RECIPIENT_FILE);
        }

        Scanner stdin = new Scanner(System.in);
        String menu = "Menu:\n" +
                "    (LR) - List all recipients\n" +
                "    (LO) - List all donors\n" +
                "    (AO) - Add new donor\n" +
                "    (AR) - Add new recipient\n" +
                "    (RO) - Remove donor\n" +
                "    (RR) - Remove recipient\n" +
                "    (SR) - Sort recipients\n" +
                "    (SO) - Sort donors\n" +
                "    (Q) - Quit\n" +
                " \n" +
                "Please select an option: ";
        String submenu = "(I) Sort by ID\n" +
                "(N) Sort by Number of Donors\n" +
                "(B) Sort by Blood Type\n" +
                "(O) Sort by Organ Needed\n" +
                "(Q) Back to Main Menu" +
                " \n" +
                "Please select an option: ";

        String option;

        while (true) {
            System.out.print(menu);
            option = stdin.next();
            switch (option.toLowerCase()) {
                case "lr":
                    transplantGraph.printAllRecipients();
                    break;
                case "lo":
                    transplantGraph.printAllDonors();
                    break;
                case "ao":
                    stdin.nextLine();
                    Patient donor = new Patient();

                    System.out.print("Please enter the organ donor name: ");
                    String name = stdin.nextLine();
                    donor.setName(name);

                    System.out.print("Please enter the organs " + name + " is" +
                            " " +
                            "donating: ");
                    donor.setOrgan(stdin.next());

                    System.out.print("Please enter the blood type of " + name + ": ");
                    donor.setBloodType(new BloodType(stdin.next()));

                    System.out.print("Please enter the age of  " + name + ": ");
                    donor.setAge(stdin.nextInt());

                    donor.setID(transplantGraph.getDonors().get(transplantGraph.getDonors().size() - 1).getID() + 1);
                    transplantGraph.addDonor(donor);
                    break;
                case "ar":
                    stdin.nextLine();
                    Patient recipient = new Patient();

                    System.out.print("Please enter new recipient's name: ");
                    String nameR = stdin.nextLine();
                    recipient.setName(nameR);

                    System.out.print("Please enter new recipient's blood " +
                            "Type: ");
                    recipient.setBloodType(new BloodType(stdin.next()));

                    System.out.print("Please enter the recipient's age: ");
                    recipient.setAge(stdin.nextInt());

                    System.out.print("Please enter the organ needed: ");
                    recipient.setOrgan(stdin.nextLine());

                    recipient.setID(transplantGraph.getRecipients().get(transplantGraph.getRecipients().size() - 1).getID() + 1);
                    transplantGraph.addRecipient(recipient);
                    break;
                case "ro":
                    stdin.nextLine();
                    System.out.print("Please enter the name of the organ donor to remove: ");
                    String nameDRem = stdin.nextLine();
                    transplantGraph.removeDonor(nameDRem);
                    break;
                case "rr":
                    stdin.nextLine();
                    System.out.print("Please enter the name of the recipient to remove: ");
                    String nameRemove = stdin.nextLine();
                    transplantGraph.removeRecipient(nameRemove);
                    break;
                case "sr":
                    stdin.nextLine();
                    System.out.print(submenu);
                    String subOption = stdin.next();
                    while (!subOption.equalsIgnoreCase("q")) {
                        switch (subOption.toLowerCase()) {
                            case "i":
                                transplantGraph.getRecipients().sort(Patient::compareTo);
                                break;
                            case "n":
                                transplantGraph.getRecipients().sort(new NumConnectionsComparator());
                                break;
                            case "b":
                                transplantGraph.getRecipients().sort(new BloodTypeComparator());
                                break;
                            case "o":
                                transplantGraph.getRecipients().sort(new OrganComparator());
                                break;
                            case "q":
                                break;
                            default:
                                System.out.println("invalid command.");
                        }
                        transplantGraph.printAllRecipients();
                        System.out.print(submenu);
                        subOption = stdin.next();
                    }
                    break;
                case "so":
                    stdin.nextLine();
                    System.out.print(submenu);
                    subOption = stdin.next();
                    while (!subOption.equalsIgnoreCase("q")) {
                        switch (subOption.toLowerCase()) {
                            case "i":
                                transplantGraph.getDonors().sort(Patient::compareTo);
                                break;
                            case "n":
                                transplantGraph.getDonors().sort(new NumConnectionsComparator());
                                break;
                            case "b":
                                transplantGraph.getDonors().sort(new BloodTypeComparator());
                                break;
                            case "o":
                                transplantGraph.getDonors().sort(new OrganComparator());
                                break;
                            case "q":
                                break;
                            default:
                                System.out.println("invalid command.");
                        }
                        transplantGraph.printAllDonors();
                        System.out.print(submenu);
                        subOption = stdin.next();
                    }
                    break;
                case "q":
                    try {
                        FileOutputStream file = new FileOutputStream("./resources" +
                                "/transplant.obj");
                        ObjectOutputStream fout = new ObjectOutputStream(file);
                        fout.writeObject(transplantGraph);
                        fout.close();
                        System.out.println("Writing data to transplant.obj...");
                    } catch (IOException e) {
                        System.out.println("Quitting Error!");
                        stdin.nextLine();
                    } finally {
                        System.exit(1);
                    }
                default:
                    System.out.println("Invalid Command.");
            }
        }
    }
}

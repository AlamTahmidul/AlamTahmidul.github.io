import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  @name Tahmidul Alam
 *  @ID 112784865
 *  @email tahmidul.alam@stonybrook.edu
 *  @course CSE 214
 *  @recitation Recitation 10 (Daniel Calabria)
 *
 * Main class for donor-recipient connection
 */
public class TransplantGraph implements Serializable {
    public static int MAX_PATIENTS = 100;

    private ArrayList<Patient> donors = new ArrayList<Patient>(MAX_PATIENTS);
    private ArrayList<Patient> recipients = new ArrayList<Patient>(MAX_PATIENTS);
    private boolean[][] connections = new boolean[MAX_PATIENTS][MAX_PATIENTS];

    /**
     *  Default Constructor
     */
    public TransplantGraph() {}

    /**
     * @param donorFile
     *  Donor file
     * @param recipientFile
     *  Recipient File
     * @return
     *  Transplant Graph from the files
     */
    public static TransplantGraph buildFromFiles(String donorFile, String recipientFile) {
        donorFile = "./resources/" + donorFile;
        recipientFile = "./resources/" + recipientFile;
        File df = new File(donorFile);
        File rf = new File(recipientFile);
        TransplantGraph tp = new TransplantGraph();;
        try {
            Scanner dfReader = new Scanner(df);
            Scanner rfReader = new Scanner(rf);
            while (dfReader.hasNextLine()) {
                String info = dfReader.nextLine();
                String[] infoArr = info.split(", ");
                Patient donor = new Patient();
                donor.setDonor(true);
                donor.setID(Integer.parseInt(infoArr[0]));
                donor.setName(infoArr[1]);
                donor.setAge(Integer.parseInt(infoArr[2]));
                donor.setOrgan(infoArr[3]);
                donor.setBloodType(new BloodType(infoArr[4]));
                tp.addDonor(donor);
            }
            while (rfReader.hasNextLine()){
                String info = rfReader.nextLine();
                String[] infoArr = info.split(", ");
                Patient recipient = new Patient();
                recipient.setID(Integer.parseInt(infoArr[0]));
                recipient.setName(infoArr[1]);
                recipient.setAge(Integer.parseInt(infoArr[2]));
                recipient.setOrgan(infoArr[3]);
                recipient.setBloodType(new BloodType(infoArr[4]));
                tp.addRecipient(recipient);
            }
            dfReader.close();
            rfReader.close();
            System.out.println("Loading data from 'donors.txt'...\n" +
                    "Loading data from 'recipients.txt'...");
        } catch (FileNotFoundException e) {
            System.out.println(String.format("%s and/or %s  not found.",
                    donorFile, recipientFile));
        }
        return tp;
    }

    /**
     * @param patient
     *  Adds Recipient to the recipients list and updates the connections matrix
     */
    public void addRecipient(Patient patient) {
        this.recipients.add(patient);
        int recipientID = patient.getID();
        for (Patient d : this.donors) {
            BloodType recipient = patient.getBloodType();
            BloodType donor = d.getBloodType();
            // Check if the bloodType and organ are compatible
            if (BloodType.isCompatible(recipient, donor) &&
                    patient.getOrgan().equalsIgnoreCase(d.getOrgan())) {
                this.connections[recipientID][d.getID()] = true;
            }
        }
    }

    /**
     * @param patient
     *  Adds Donor to the donors list and updates the connections matrix
     */
    public void addDonor(Patient patient) {
        this.donors.add(patient);
        int donorID = patient.getID();
        for (Patient r : this.recipients) {
            BloodType recipient = r.getBloodType();
            BloodType donor = patient.getBloodType();
            if (BloodType.isCompatible(recipient, donor) &&
                    patient.getOrgan().equalsIgnoreCase(r.getOrgan())) {
                this.connections[r.getID()][donorID] = true;
            }
        }
    }

    /**
     *  Attempts to reindex the recipients
     */
    private void reIndexR() {
        int i = 0;
        for (Patient recipient : this.recipients) {
            recipient.setID(i);
            i++;
        }
    }

    /**
     *  Attempts to reindex the donors
     */
    private void reIndexD() {
        int i = 0;
        for (Patient donors: this.donors) {
            donors.setID(i);
            i++;
        }
    }

    /**
     *  Re-builds the matrix after add and subtract
     */
    private void buildMatrix() {
        boolean[][] newC = new boolean[MAX_PATIENTS][MAX_PATIENTS];
        for (Patient r : this.recipients) {
            int rID = r.getID();
            for (Patient d : this.donors) {
                if (BloodType.isCompatible(r.getBloodType(),
                        d.getBloodType()) && r.getOrgan().equalsIgnoreCase(d.getOrgan())) {
                    int dID = d.getID();
                    newC[rID][dID] = true;
                }
            }
        }
        this.connections = newC;
    }

    /**
     * @param name
     *  Removes the recipient from the recipients arrayList
     */
    public void removeRecipient(String name) {
        for (Patient recipient : this.recipients) {
            // Check if the name we want exists and if it does...
            if (recipient.getName().equalsIgnoreCase(name)) {

                this.recipients.remove(recipient); // Remove from the recipients
                reIndexR(); // Reindex the Recipients
                buildMatrix();

                System.out.println(recipient.getName() + "  was removed from " +
                        "the organ transplant waitlist.");
                return;
            }
        }
        System.out.println("Person does not exist!");
    }

    /**
     * @param name
     *  Removes the donor from the donors arrayList
     */
    public void removeDonor(String name) {
        for (Patient donor : this.donors) {
            if (donor.getName().equalsIgnoreCase(name)) {

                this.donors.remove(donor);
                reIndexD();
                buildMatrix();

                System.out.println(donor.getName()
                        + " was removed from the organ donor list.");
                return;
            }
        }
        System.out.println(" Person does not exist.");
    }

    /**
     *  Prints all recipients
     */
    public void printAllRecipients() {
        String menuFormat =
                "\n%6s | %-18s | %-3s | %-13s | %-10s | %s" + "\n" + "=".repeat(75);
        System.out.println(String.format(menuFormat, "Index", "Recipient " +
                "Name", "Age", "Organ Needed", "Blood Type", "Donor IDs"));
        String outputFormat = "%s | %s";
        for (Patient recipient : this.recipients) {
            StringBuilder donorIDs = new StringBuilder();
            int count = 0;
            for (boolean c : this.connections[recipient.getID()]) {
                if (c) {
                    recipient.setConnections(recipient.getConnections() + count);
                    donorIDs.append(count).append(", ");
                }
                count++;
            }
            if (donorIDs.length() > 0) {
                donorIDs = new StringBuilder(donorIDs.substring(0, donorIDs.length() - 2));
            }
            System.out.println(String.format(outputFormat, recipient.toString(),
                    donorIDs.toString()));
        }
    }

    /**
     *  Prints all the donors
     */
    public void printAllDonors() {
        String menuFormat =
                "\n%6s | %-18s | %-3s | %-13s | %-10s | %s" + "\n" + "=".repeat(75);
        System.out.println(String.format(menuFormat, "Index", "Donor " +
                "Name", "Age", "Organ Donated", "Blood Type", "Recipient IDs"));
        String outputFormat = "%s | %s";
        for (Patient donor : this.donors) {
            StringBuilder recipientIDs = new StringBuilder();
            for (int i = 0; i < this.connections.length; i++) {
                if (this.connections[i][donor.getID()]) {
                    donor.setConnections(donor.getConnections() + i);
                    recipientIDs.append(i).append(", ");
                }
            }
            if (recipientIDs.length() > 0)  {
                recipientIDs = new StringBuilder(recipientIDs.substring(0,
                        recipientIDs.length() - 2));
            }
            System.out.println(String.format(outputFormat, donor.toString(),
                    recipientIDs.toString()));
        }
    }

    /**
     * @return
     *  gets the arrayList of donors
     */
    public ArrayList<Patient> getDonors() {
        return donors;
    }

    /**
     * @return
     *  Gets the arraylist of Recipients
     */
    public ArrayList<Patient> getRecipients() {
        return recipients;
    }

    /**
     * @return
     *  Gets the connections matrix
     */
    public boolean[][] getConnections() {
        return connections;
    }

    /**
     * @return
     *  Gets the maxPatients
     */
    public static int getMaxPatients() {
        return MAX_PATIENTS;
    }

    /**
     * @param connections
     *  Sets the connections matrix
     */
    public void setConnections(boolean[][] connections) {
        this.connections = connections;
    }

    /**
     * @param donors
     *  Sets the donors
     */
    public void setDonors(ArrayList<Patient> donors) {
        this.donors = donors;
    }

    /**
     * @param maxPatients
     *  Sets the number of Max Patients
     */
    public static void setMaxPatients(int maxPatients) {
        MAX_PATIENTS = maxPatients;
    }

    /**
     * @param recipients
     *  Sets the Recipients arrayList
     */
    public void setRecipients(ArrayList<Patient> recipients) {
        this.recipients = recipients;
    }
}

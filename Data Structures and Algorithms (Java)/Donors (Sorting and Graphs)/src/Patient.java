import java.io.Serializable;

/**
 *  @name Tahmidul Alam
 *  @ID 112784865
 *  @email tahmidul.alam@stonybrook.edu
 *  @course CSE 214
 *  @recitation Recitation 10 (Daniel Calabria)
 *
 * Main class for Patient and Donor info
 */
public class Patient implements Serializable, Comparable {
    private String name = "";
    private String organ = "";
    private int age = -1;
    private BloodType bloodType = null;
    private int ID = -1;
    private boolean isDonor = false;
    private String connections = "";

    /**
     *  Default Constructor
     */
    public Patient() {}

    /**
     * @param o
     *  The patient object
     * @return
     *  -1/0/1 if current ID is bigger/same/less
     */
    @Override
    public int compareTo(Object o) {
        Patient patient = (Patient) o;
        return Integer.compare(this.ID, patient.getID());
    }

    /**
     * @return
     *  String representation of the class
     */
    @Override
    public String toString() {
        // In the beginning: %6s |
        // In the end: | %s
        String outputFormat = "%6s | %-18s | %-3s | %-13s | %-10s";
        return String.format(outputFormat, this.ID, this.name,
                this.age, this.organ, this.bloodType.getBloodType());
    }

    /**
     * @return
     *  Gets name
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     *  Gets bloodType
     */
    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * @return
     *  Gets age
     */
    public int getAge() {
        return age;
    }

    /**
     * @return
     *  Gets ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return
     *  Checks if patient is donor
     */
    public boolean isDonor() {
        return isDonor;
    }

    /**
     * @return
     *  Gets organ
     */
    public String getOrgan() {
        return organ;
    }

    /**
     * @return
     *  Gets the connections
     */
    public String getConnections() {
        return connections;
    }

    /**
     * @param name
     *  Sets name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param age
     *  Sets Age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @param bloodType
     *  Sets the bloodType of the patient
     */
    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * @param donor
     *  Sets donor status
     */
    public void setDonor(boolean donor) {
        isDonor = donor;
    }

    /**
     * @param ID
     *  Sets id
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @param organ
     *  Sets organ
     */
    public void setOrgan(String organ) {
        this.organ = organ;
    }

    /**
     * @param connections
     *  Sets the connections
     */
    public void setConnections(String connections) {
        this.connections = connections;
    }
}

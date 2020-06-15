import java.io.Serializable;

/**
 *  @name Tahmidul Alam
 *  @ID 112784865
 *  @email tahmidul.alam@stonybrook.edu
 *  @course CSE 214
 *  @recitation Recitation 10 (Daniel Calabria)
 *
 * Class for BloodType info
 */
public class BloodType implements Serializable {
    private String bloodType = "";

    /**
     *  Default constructor
     */
    public BloodType() {}

    /**
     * @param bloodType
     *  Default constructor w/ constructor
     */
    public BloodType(String bloodType) {this.bloodType = bloodType;}

    /**
     * @param recipient
     *  The recipient
     * @param donor
     *  The donor
     * @return
     *  The bloodType compatibility
     */
    public static boolean isCompatible(BloodType recipient, BloodType donor) {
        boolean[][] compatibility = {
                {true, false, false, false},
                {true, true, false, false},
                {true, false, true, false},
                {true, true, true, true}
        };
        int indexOfRec = 0;
        int indexOfDon = 0;
        try {
            indexOfRec = getIndex(recipient.getBloodType());
            indexOfDon = getIndex(donor.getBloodType());
        } catch (Exception e) {
            System.out.println("getIndex() error!");
            System.exit(-1);
        }
        return compatibility[indexOfRec][indexOfDon];
    }

    /**
     * @param bloodType
     *  The String equivalent of table placement
     * @return
     *  Index of compatiblity check
     * @throws Exception
     *  If there is an outside value
     */
    private static int getIndex(String bloodType) throws Exception {
        switch (bloodType.toLowerCase()) {
            case "o":
                return 0;
            case "a":
                return 1;
            case "b":
                return 2;
            case "ab":
                return 3;
            default:
                break;
        }
        throw new Exception();
    }

    /**
     * @return
     *  Gets the String value
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * @param bloodType
     *  Sets the bloodType
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}

import java.util.Comparator;

/**
 *  @name Tahmidul Alam
 *  @ID 112784865
 *  @email tahmidul.alam@stonybrook.edu
 *  @course CSE 214
 *  @recitation Recitation 10 (Daniel Calabria)
 *
 *  Class for BloodType Sort
 */
public class BloodTypeComparator implements Comparator<Patient> {
    /**
     * @param o1
     *  Patient 1
     * @param o2
     *  Patient 2
     * @return
     *  -1/0/1 if patient 1's alphabet is bigger/same/less
     */
    @Override
    public int compare(Patient o1, Patient o2) {
        String bt1 = o1.getBloodType().getBloodType();
        String bt2 = o2.getBloodType().getBloodType();
        return bt1.compareToIgnoreCase(bt2);
    }
}

import java.util.Comparator;

/**
 *  Class for ordering by Organs
 */
public class OrganComparator implements Comparator<Patient> {
    /**
     * @param o1
     *  Patient 1
     * @param o2
     *  Patient 2
     * @return
     *  -1/0/1 if patient 1's organ letter is bigger/less/smaller
     */
    @Override
    public int compare(Patient o1, Patient o2) {
        String p1Organ = o1.getOrgan();
        String p2Organ = o2.getOrgan();
        return p1Organ.compareToIgnoreCase(p2Organ);
    }
}

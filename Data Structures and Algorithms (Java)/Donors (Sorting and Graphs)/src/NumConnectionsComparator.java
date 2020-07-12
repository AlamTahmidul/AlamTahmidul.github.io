import java.util.Comparator;

/**
 *  The main class for Number of Connections comparator
 */
public class NumConnectionsComparator implements Comparator<Patient> {
    /**
     * @param o1
     *  Patient Connections 1
     * @param o2
     *  Patient Connections 2
     * @return
     *  -1/0/1 if number of connections in 1 is bigger, same, or less
     */
    @Override
    public int compare(Patient o1, Patient o2) {
        return o1.getConnections().compareToIgnoreCase(o2.getConnections());
    }
}

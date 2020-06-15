/**
 * Class Train that includes information about the train and it's nodes
 */
public class Train {
    private int trainNumber;
    private String destination;
    private int arrivalTime;
    private int transferTime;
    private Train next;
    private Train previous;

    /**
     *  Default constructor w/o parameters
     */
    public Train() {
        this.trainNumber = 0;
        this.destination = "";
        this.arrivalTime = 0;
        this.transferTime = 0;
        this.next = null;
        this.previous = null;
    }

    /**
     * Default constructor with parameters
     * @param trainNumber
     *  Integer value of train number
     * @param destination
     *  String value of destination
     * @param arrivalTime
     *  Integer value of when the train will arrive (24-hour format)
     * @param transferTime
     *  Integer value of when the train will leave (in minutes)
     */
    public Train(int trainNumber, String destination, int arrivalTime,
                 int transferTime) {
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.transferTime = transferTime;
        this.next = null;
        this.previous = null;
    }

    /**
     *  Accesses the next node
     * @return
     *  Returns object type Train node
     */
    public Train getNext() {
        return this.next;
    }

    /**
     * Sets the next node
     * @param train
     *  Type of object Train
     */
    public void setNext(Train train) {
        this.next = train;
    }

    /**
     * Accesses the previous node
     * @return
     *  Type of object Train
     */
    public Train getPrev() {
        return this.previous;
    }

    /**
     * Sets the previous node
     * @param train
     *  Input Type of object Train
     */
    public void setPrev(Train train) {
        this.previous = train;
    }

    /**
     * Compares 2 Train objects
     * @param obj
     *  Input type is Object
     * @return
     *  Boolean expression if 2 train objects are the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Train) {
            Train t = (Train) obj;
            return (t.getTrainNumber() == this.trainNumber);
        }
        return false;
    }

    /**
     * Writes the info in the train class as a string
     * @return
     *  String of train information
     */
    @Override
    public String toString() {
        String output = "";
        String format = "Selected Train:\n" +
                "    Train Number: %d\n" +
                "    Train Destination: %s\n" +
                "    Arrival Time: %04d\n" +
                "    Departure Time: %s";
        output += String.format(format, this.trainNumber, this.destination,
            this.arrivalTime, departureTime());
        return output;
    }

    /**
     * Writes the info of train (alternative method)
     * @return
     *  String of train information
     */
    public String toString2() {
        String output = "";
        output += this.trainNumber + "," + this.destination + "," +
                this.arrivalTime + "," + this.transferTime;
        return output;
    }

    /**
     * Re-writes the arrival + departure time as a format of time (24h format)
     * @return
     *  String of arrival + departure times in 24h format.
     */
    public String departureTime() {
        String format = "%04d";
        int transferTime = this.transferTime;
        int arrivalTime = this.arrivalTime;
        int minutes = (arrivalTime % 100) + transferTime;
        int hoursAdd = transferTime / 60;
        int newHours = ((arrivalTime / 100) + hoursAdd) % 24;
        int hours = newHours * 100;
        int mins = minutes % 60;
        return (String.format(format, (hours + mins)));
    }

    /**
     * Gets the arrival time
     * @return
     *  Integer value of 24h time
     */
    // Getters
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the arrival time
     * @param arrivalTime
     *  Integer input of time in 24h format
     */
    // Setters
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Gets the train number
     * @return
     *  Integer of train number
     */
    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    /**
     * Gets the transfer time
     * @return
     *  Integer value of transferTime
     */
    public int getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(int transferTime) {
        this.transferTime = transferTime;
    }

    /**
     * Gets the destination
     * @return
     *  String of destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination
     * @param destination
     *  String input of destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

}

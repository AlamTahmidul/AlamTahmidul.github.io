/**
 * Track class that creates a Linked List of trains in tracks
 */
public class Track {
    private Train head;
    private Train tail;
    private Train cursor;
    private Track next;
    private Track prev;
    private int trackNumber;
    private int numTrains;
    private int transferTime;
    private double utilizationRate;

    /**
     * Default constructor w/o parameters
     */
    public Track() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
        this.next = null;
        this.prev = null;
        this.numTrains = 0;
        this.transferTime = 0;
    }

    /**
     * Default constructor w/ parameter
     * @param trackNumber
     *  Integer input of track number
     */
    public Track(int trackNumber) {
        this.head = null;
        this.tail = null;
        this.cursor = null;
        this.next = null;
        this.prev = null;
        this.numTrains = 0;
        this.transferTime = 0;
        this.trackNumber = trackNumber;
    }

    /**
     * Adds a train to a track
     * @param newTrain
     *  Input type of train
     * @throws NullPointerException
     *  If no trains exist
     * @throws TrainAlreadyExistsException
     *  If a train with the same number already exists
     * @throws OverLapException
     *  If a train's schedule overlaps with another arranged schedule
     */
    public void addTrain(Train newTrain) throws NullPointerException,
            TrainAlreadyExistsException, OverLapException {
        Train temp = this.head;
        while (temp != null) {
            if (temp.equals(newTrain)) {
                throw new TrainAlreadyExistsException();
            } else if (newTrain.getArrivalTime() >= temp.getArrivalTime() &&
                    newTrain.getArrivalTime() <= temp.getArrivalTime()
                            + temp.getTransferTime()) {
                throw new OverLapException();
            }
            temp = temp.getNext();
        }
        numTrains++;
        this.transferTime += newTrain.getTransferTime();
        if (this.head == null) {
            this.head = newTrain;
            this.cursor = this.head;
            return;
        }
        if (this.cursor.getNext() == null) {
            this.cursor.setNext(newTrain);
            this.cursor.getNext().setPrev(this.cursor);
            this.cursor = this.cursor.getNext();
            return;
        }
        newTrain.setNext(this.cursor.getNext());
        newTrain.setPrev(this.cursor.getPrev());
        this.cursor.getNext().setPrev(newTrain);
        this.cursor.setNext(newTrain);
        this.cursor = newTrain;
    }

    /**
     * Prints information about the selected train node (where the cursor is)
     */
    public void printSelectedTrain() {
        String format = "Selected Train:\n" +
                "    Train Number: %i\n" +
                "    Train Destination: &s\n" +
                "    Arrival Time: %04d\n" +
                "    Departure Time: %s";
        System.out.format(format, this.cursor.getTrainNumber(),
                this.cursor.getDestination(), this.cursor.getArrivalTime(),
                departureTime(this.cursor));
    }

    /**
     * Removes train from the list
     * @return
     *  Train that is in the cursor to be removed
     * @throws EmptyTrainException
     *  There are no trains
     * @throws EmptyTrackException
     *  There are no tracks
     */
    public Train removeSelectedTrain() throws EmptyTrainException, EmptyTrackException { //
        // NullPointerSection
        if (this.head == null) throw new EmptyTrainException();
        if (this.numTrains == 0) throw new EmptyTrackException();
        numTrains--;
        Train removeTrain = this.cursor;
        if (this.cursor == this.head && this.cursor.getNext() == null) { //
            // First Link
            this.head = null;
            this.cursor = null;
            return removeTrain;
        }
        if (this.cursor == this.head && this.cursor.getNext() != null) {
            this.cursor = this.cursor.getNext();
            this.cursor.setPrev(null);
            this.head = this.cursor;
            return removeTrain;
        }
        if (this.cursor.getNext() == null && this.cursor.getPrev() != null) { // Last Link
            this.cursor = this.cursor.getPrev();
            this.cursor.setNext(null);
            return removeTrain;
        }
        this.cursor.getNext().setPrev(this.cursor.getPrev()); // Middle Links
        this.cursor.getPrev().setNext(this.cursor.getNext());
        this.cursor = this.cursor.getNext();
        return removeTrain;
    }

    /**
     * Selects the next train node
     * @return
     *  True if the next train can be selected
     * @throws NoNextTrainException
     *  If there is no next train on the track
     *
     */
    public boolean selectNextTrain() throws NoNextTrainException {
        if (this.cursor.getNext() == null) throw new NoNextTrainException();
        this.cursor = this.cursor.getNext();
        return true;
    }

    /**
     * Select the previous train node
     * @return
     *  True if there is a train behind
     * @throws NoPrevTrainException
     *  If there are no more trains behind
     */
    public boolean selectPrevTrain() throws NoPrevTrainException {
        if (this.cursor.getPrev() == null) throw new NoPrevTrainException();
        this.cursor = this.cursor.getPrev();
        return true;
    }

    /**
     * Writes the Track class as a string
     * @return
     *  String containing train nodes in track
     */
    public String toString() {
        String all = "";
        Train tempCursor = this.head;
        String trainsFormat = "%-11s%-17s%-26s%-17s%-14s\n";
        System.out.println(String.format(trainsFormat, "Selected", "Train " +
                "Number", "Destination", "Arrival Time", "Transfer Time"));
        while (tempCursor != null) {
            String[] trainInfo = tempCursor.toString2().split(",");
            if (tempCursor.equals(this.cursor)) {
                System.out.println(String.format(trainsFormat, "*",
                        trainInfo[0],
                        trainInfo[1], trainInfo[2], departureTime(tempCursor)));
                all += String.format(trainsFormat, "*", trainInfo[0],
                        trainInfo[1], trainInfo[2], departureTime(tempCursor));
            } else {
                System.out.println(String.format(trainsFormat, "", trainInfo[0],
                        trainInfo[1], trainInfo[2], departureTime(tempCursor)));
                all += String.format(trainsFormat, "", trainInfo[0],
                        trainInfo[1], trainInfo[2], departureTime(tempCursor));
            }
            tempCursor = tempCursor.getNext();
        }
        return all;
    }

    /**
     * Attempts to write arrival + departure as a 24h time
     * @param t
     *  The train to write the departure time
     * @return
     *  String of the train's departure time
     */
    public String departureTime(Train t) {
        String format = "%04d";
        int transferTime = t.getTransferTime();
        int arrivalTime = t.getArrivalTime();
        int minutes = (arrivalTime % 100) + transferTime;
        int hoursAdd = transferTime / 60;
        int newHours = ((arrivalTime / 100) + hoursAdd) % 24;
        int hours = newHours * 100;
        int mins = minutes % 60;
        return (String.format(format, (hours + mins)));
    }

    /**
     * Gets utilization rate
     * @return
     *  Double of utilizationRate
     */
    // Getters
    public double getUtilizationRate() {
        return (this.transferTime / 1440.0);
    }

    /**
     * Gets Track Number
     * @return
     *  Integer of track number
     */
    public int getTrackNumber() {
        return trackNumber;
    }

    /**
     * Gets the next track node
     * @return
     *  Object of type Track
     */
    public Track getNext() {
        return next;
    }

    /**
     * Gets the previous track node
     * @return
     *  Object of type Track
     */
    public Track getPrev() {
        return prev;
    }

    /**
     * Gets the current cursor of the train node
     * @return
     *  Object of type Train
     */
    public Train getCursor() {
        return cursor;
    }

    /**
     * Gets the head of the train node
     * @return
     *  Object of type Train
     */
    public Train getHead() {
        return head;
    }

    /**
     * Gets the tail of the train node
     * @return
     *  Object of type Train
     */
    public Train getTail() {
        return tail;
    }

    /**
     * Gets the number of trains in the track
     * @return
     *  Integer of the number of trains
     */
    public int getNumTrains() {
        return numTrains;
    }

    /**
     * Gets the transfer time
     * @return
     *  Integer value of transfer time
     */
    public int getTransferTime() {
        return transferTime;
    }

    // Setters
    /**
     * Sets the cursor of the Train node
     * @param cursor
     *  Object of type Train
     */
    public void setCursor(Train cursor) {
        this.cursor = cursor;
    }

    /**
     * Sets what the train head node is
     * @param head
     *  Object of type Train
     */
    public void setHead(Train head) {
        this.head = head;
    }

    /**
     * Sets the next track node
     * @param next
     *  Object of type Track
     */
    public void setNext(Track next) {
        this.next = next;
    }

    /**
     * Sets the previous track node
     * @param prev
     *  Object of type Track
     */
    public void setPrev(Track prev) {
        this.prev = prev;
    }

    /**
     * Sets the tail of the train node
     * @param tail
     *  Object of type Train
     */
    public void setTail(Train tail) {
        this.tail = tail;
    }

    /**
     * Sets the track number
     * @param trackNumber
     *  Integer of track number
     */
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     * Sets the utilization rate
     * @param utilizationRate
     *  Double value of the rate
     */
    public void setUtilizationRate(double utilizationRate) {
        this.utilizationRate = utilizationRate;
    }

    /**
     * Sets the number of train in the track
     * @param numTrains
     *  Integer value of number of trains
     */
    public void setNumTrains(int numTrains) {
        this.numTrains = numTrains;
    }

    /**
     * Sets the transfer time of the train node
     * @param transferTime
     *  Integer value of the transfer time
     */
    public void setTransferTime(int transferTime) {
        this.transferTime = transferTime;
    }
}

/**
 * Exception class if there is no next train node
 */
class NoNextTrainException extends Exception {
    public NoNextTrainException() {}
}

/**
 * Exception class if there is no previous train node
 */
class NoPrevTrainException extends Exception {
    public NoPrevTrainException() {}
}

/**
 * Exception class if there is a train with the same train number already added
 */
class TrainAlreadyExistsException extends Exception {
    public TrainAlreadyExistsException() {}
}

/**
 * Exception class if there are no trains added
 */
class EmptyTrainException extends Exception {
    public EmptyTrainException() {}
}

/**
 * Exception class if there are no tracks added
 */
class EmptyTrackException extends Exception {
    public EmptyTrackException() {}
}

/**
 * Exception class if there is a overlap in time of a train's arrival time
 */
class OverLapException extends Exception {
    public OverLapException() {}
}
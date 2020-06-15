import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Creates a class Station that holds a Linked List of Track nodes
 */
public class Station {
    private Track head;
    private Track tail;
    private Track cursor;
    private int tracks;

    /**
     * Default constructor w/o parameters
     */
    public Station() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
        this.tracks = 0;
    }

    /**
     * Default constructor w/ parameters
     * @param newTrack
     *  Track object to add
     * @throws TrackExistsException
     *  Track with the same number exists
     */
    public void addTrack(Track newTrack) throws TrackExistsException {
        Track temp = this.head;
        while (temp != null) {
            if (temp.getTrackNumber() == newTrack.getTrackNumber())
                throw new TrackExistsException();
            temp = temp.getNext();
        }
        tracks++;
        if (this.head == null) {
            this.head = newTrack;
            this.cursor = this.head;
            return;
        }
        if (this.cursor.getNext() == null) {
            this.cursor.setNext(newTrack);
            this.cursor.getNext().setPrev(this.cursor);
            this.cursor = this.cursor.getNext();
            return;
        }
        newTrack.setNext(this.cursor.getNext());
        newTrack.setPrev(this.cursor.getPrev());
        this.cursor.getNext().setPrev(newTrack);
        this.cursor.setNext(newTrack);
        this.cursor = newTrack;
    }

    /**
     * Removes a track node
     * @param newTrack
     *  The track to delete
     * @throws EmptyTrackException
     *  No tracks were found
     */
    public void removeSelectedTrack(Track newTrack) throws EmptyTrackException {
        if (this.cursor == null) throw new EmptyTrackException();
        tracks--;
        if (this.cursor == this.head && this.cursor.getNext() == null) { //
            // First Link
            this.head = null;
            this.cursor = null;
            return;
        }
        if (this.cursor == this.head && this.cursor.getNext() != null) {
            this.cursor = this.cursor.getNext();
            this.cursor.setPrev(null);
            this.head = this.cursor;
            return;
        }
        if (this.cursor.getNext() == null && this.cursor.getPrev() != null) { // Last Link
            this.cursor = this.cursor.getPrev();
            this.cursor.setNext(null);
            return;
        }
        this.cursor.getNext().setPrev(this.cursor.getPrev()); // Middle Links
        this.cursor.getPrev().setNext(this.cursor.getNext());
        this.cursor = this.cursor.getNext();
    }

    /**
     * Prints the track info for the selected track
     */
    public void printSelectedTrack() {
        String tableFormat = "Track %d (%3.2f %% Utilization Rate)" + "\n" +
                "%-11s%-17s%-26s%-17s%s\n" +
                        "------------------------------------------" +
                        "-------------------------------------------\n";
        System.out.format(tableFormat, this.cursor.getTrackNumber(),
                this.cursor.getUtilizationRate(),
                "Train Number",
                "Train " +
                "Destination", "Arrival Time", "Departure Time");
        this.cursor.toString();
    }

    /**
     *  Prints information for all the tracks
     */
    public void printAllTracks() {
        Track temp = this.head;
        while (temp != null) {
            if (temp == this.cursor) {
                String tableFormat =
                        "Track %d* (%3.2f %% Utilization Rate)" + "\n" +
                        "%-11s%-17s%-26s%-17s%s\n" +
                        "------------------------------------------" +
                        "-------------------------------------------\n";
                System.out.format(tableFormat, temp.getTrackNumber(),
                        temp.getUtilizationRate(), "Selected",
                        "Train Number",
                        "Train " +
                                "Destination", "Arrival Time", "Departure Time");
            } else {
                String tableFormat = "Track %d (%3.2f %% Utilization Rate)" +
                        "\n" +
                        "%-11s%-17s%-26s%-17s%s\n" +
                        "------------------------------------------" +
                        "-------------------------------------------\n";
                System.out.format(tableFormat, temp.getTrackNumber(),
                        temp.getUtilizationRate(),"Selected",
                        "Train Number",
                        "Train " +
                                "Destination", "Arrival Time", "Departure Time");
            }
            temp.toString();
            temp = temp.getNext();
        }
    }

    /**
     * Selects the track based on the track number
     * @param trackToSelect
     *  Integer value of track number
     * @return
     *  Returns true if the track could be selected
     * @throws TrackNotFoundException
     *  Otherwise, the track does not exist
     */
    public boolean selectTrack(int trackToSelect) throws TrackNotFoundException {
        Track pick = this.head;
        while (pick != null) {
            if (pick.getTrackNumber() == trackToSelect) {
                this.cursor = pick;
                return true;
            }
            pick = pick.getNext();
        }
        throw new TrackNotFoundException();
    }

    /**
     * Attempts to add the list of track information into string
     * @return
     *  String of the track information
     */
    public String toString() {
        String all = "";
        System.out.format("Station (%d tracks):\n", this.cursor.getNumTrains());
        all += String.format("Station (%d tracks):\n", this.cursor.getNumTrains());
        Track temp = this.head;
        while (temp != null) {
            System.out.format("\t Track %d: %d trains arriving (%3.2f%% " +
                    "Utilization Rate)\n", temp.getTrackNumber(),
                    temp.getNumTrains(), temp.getUtilizationRate());
            all += String.format("\t Track %d: %d trains arriving (%3.2f%% " +
                            "Utilization Rate)\n", temp.getTrackNumber(),
                    temp.getNumTrains(), temp.getUtilizationRate());
            temp = temp.getNext();
        }
        return all;
    }

    /**
     * The main method to run (with menu)
     * @param args
     *  If there are any additional parameters
     */
    public static void main(String[] args) {
        String menu = "|-------------------------------" +
                "----------------------------------------------|\n" +
                "| Train Options                       " +
                "| Track Options                         |\n" +
                "|    A. Add new Train                 " +
                "|    TA. Add Track                      |\n" +
                "|    N. Select next Train             " +
                "|    TR. Remove selected Track          |\n" +
                "|    V. Select previous Train         " +
                "|    TS. Switch Track                   |\n" +
                "|    R. Remove selected Train         " +
                "|   TPS. Print selected Track           |\n" +
                "|    P. Print selected Train          " +
                "|   TPA. Print all Tracks               |\n" +
                "|---------------------------------------" +
                "--------------------------------------|\n" +
                "| Station Options                     " +
                "                                        |\n" +
                "|   SI. Print Station Information       " +
                "                                      |\n" +
                "|    Q. Quit                          " +
                "                                        |\n" +
                "|--------------------------------------" +
                "---------------------------------------|";
        Scanner stdin = new Scanner(System.in);
        String user;
        Station station = new Station();
        while (true) {
            System.out.println(menu);
            System.out.print("Enter an operation: ");
            user = stdin.next();
            switch (user) {
                case "A":
                case "a":
                    if (station.getTracks() == 0) {
                        System.out.println("Train not added: There is no " +
                                "Track to add the Train to!\n");
                    } else {
                        try {
                            System.out.print("Enter train number: ");
                            int num = stdin.nextInt();
                            System.out.print("Enter train destination: ");
                            stdin.nextLine();
                            String dest = stdin.nextLine();
                            System.out.print("Enter train arrival time: ");
                            int arrival = stdin.nextInt();
                            if (arrival / 100 > 23 || arrival / 100 < 0) {
                                System.out.println("Invalid Input!");
                                break;
                            } else if (arrival % 100 > 59) {
                                System.out.println("Invalid Input!");
                                break;
                            }
                            System.out.print("Enter train transfer: ");
                            int transfer = stdin.nextInt();
                            Train t = new Train(num, dest, arrival, transfer);
                            station.getCursor().addTrain(t);
                            System.out.println("Train successfully added!");
                        } catch (OverLapException e) {
                            System.out.println("Conflict in train schedule.");
                            stdin.nextLine();
                        } catch (NullPointerException e) {
                            System.out.println("NULL POINTER EX in 'a'");
                            stdin.nextLine();
                        } catch (Exception e) {
                            System.out.println("Error in command 'a'. Invalid " +
                                    "input! ");
                            stdin.nextLine();
                        }
                    }
                    break;
                case "N":
                case "n":
                    try {
                        if (station.getCursor().getNext() == null) {
                            System.out.println("Selected train not updated: " +
                                    "Already at end of Track list.");
                        } else {
                            station.getCursor().selectNextTrain();
                            System.out.println("Cursor has been moved to " +
                                    "next train.");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("NULL POINTER EX in 'n'");
                        stdin.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error in command 'n'.");
                        stdin.nextLine();
                    }
                    break;
                case "V":
                case "v":
                    try {
                        if (station.getCursor().getCursor().getPrev() == null) {
                            System.out.println("Selected train not updated: " +
                                    "Already at front of Track list.");
                        } else {
                            station.getCursor().selectPrevTrain();
                            System.out.println("Cursor has been moved to " +
                                    "previous train.");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("NULL POINTER EX in 'v'");
                        stdin.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error in command 'v'.");
                        stdin.nextLine();
                    }
                    break;
                case "R":
                case "r":
                    try {
                        String rFormat = "Train No. %d to %s has been " +
                                "removed from Track %d.\n";
                        System.out.format(rFormat,
                                station.getCursor().getCursor().getTrainNumber(),
                                station.getCursor().getCursor().getDestination(),
                                station.getCursor().getTrackNumber());
                        station.getCursor().removeSelectedTrain();
                    } catch (EmptyTrainException e) {
                        System.out.println("No Trains found!");
                        stdin.nextLine();
                    }  catch(EmptyTrackException e) {
                        System.out.println("Track is empty!");
                        stdin.nextLine();
                    } catch (NullPointerException e) {
                        System.out.println("Empty Track/Train.");
                        stdin.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error in 'r'");
                        stdin.nextLine();
                    }
                    break;
                case "P":
                case "p":
                    try {
                        if (station.getCursor().getNumTrains() == 0) {
                            System.out.println("No trains added!");
                        } else
                            System.out.println(station.getCursor().getCursor().toString());
                    } catch (NullPointerException e) {
                        System.out.println("NULL POINTER EX in 'p'");
                        stdin.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error in 'p'");
                        stdin.nextLine();
                    }
                    break;
                case "TA":
                case "ta":
                    try {
                        System.out.println("Enter Track Number: ");
                        int trackNum = stdin.nextInt();
                        Track track = new Track(trackNum);
                        station.addTrack(track);
                    } catch(InputMismatchException e) {
                        System.out.println("Invalid Input!");
                        stdin.nextLine();
                    } catch (TrackExistsException e) {
                        System.out.println("Track already exists!");
                        stdin.nextLine();
                    } catch (NullPointerException e) {
                        System.out.println("NULL POINTER EX in 'ta'");
                        stdin.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error in 'ta'");
                        stdin.nextLine();
                    }
                    break;
                case "TR":
                case "tr":
                    try {
                        System.out.format("Closed Track %d.\n",
                                station.getCursor().getTrackNumber());
                        station.removeSelectedTrack(station.getCursor());
                    } catch (EmptyTrackException e) {
                        System.out.println("No tracks were added!");
                    } catch (NullPointerException e) {
                        System.out.println("Tracks are empty! Can't be " +
                                "removed!");
                        stdin.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error in 'tr'");
                        stdin.nextLine();
                    }
                    break;
                case "TS":
                case "ts":
                    try {
                        if (station.getTracks() == 0) {
                            System.out.println("Empty Tracks");
                        } else {
                            System.out.print("Enter the Track number: ");
                            int trackNumSelect = stdin.nextInt();
                            station.selectTrack(trackNumSelect);
                        }
                    } catch(TrackNotFoundException e) {
                        System.out.println("Track Not Found!");
                        stdin.nextLine();
                    } catch (NullPointerException e) {
                        System.out.println("NULL POINTER EX in 'ts'");
                        stdin.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error in 'ts'");
                        stdin.nextLine();
                    }
                    break;
                case "TPS":
                case "tps":
                    try {
                        station.getCursor().toString();
//                        System.out.println(station.getCursor().getNumTrains());
                    } catch (NullPointerException e) {
                        System.out.println("No tracks can be removed. Empty " +
                                "tracks!");
                        stdin.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error in 'tps'");
                        stdin.nextLine();
                    }
                    break;
                case "TPA":
                case "tpa":
                    try {
                        station.printAllTracks();
                    } catch (NullPointerException e) {
                        System.out.println("NULL POINTER EX in 'tpa'");
                        stdin.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error in 'tpa'");
                        e.printStackTrace();
                        stdin.nextLine();
                    }
                    break;
                case "SI":
                case "si":
                    try {
                        station.toString();
                    } catch (NullPointerException e) {
                        System.out.println("NULL POINTER EX in 'si'");
                        stdin.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error in 'si'");
                        stdin.nextLine();
                    }
                    break;
                case "Q":
                case "q":
                    System.out.println("Program terminating normally...\n");
                    System.exit(1);
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    /**
     * Accesses the Track node in the Station
     * @return
     *  Object of type Track
     */
    public Track getCursor() {
        return cursor;
    }

    /**
     * Accesses the head node in the Station
     * @return
     *  Object of type Track
     */
    public Track getHead() {
        return head;
    }

    /**
     * Accesses the tail node in the station
     * @return
     *  Object of type Track
     */
    public Track getTail() {
        return tail;
    }

    /**
     * Gets the number of tracks in the station
     * @return
     *  Integer value of the number of tracks
     */
    public int getTracks() {
        return tracks;
    }

    /**
     * Sets the tail node
     * @param tail
     *  Object of type Track
     */
    public void setTail(Track tail) {
        this.tail = tail;
    }

    /**
     * Sets the head node
     * @param head
     *  Object of type Track
     */
    public void setHead(Track head) {
        this.head = head;
    }

    /**
     * Sets the cursor node
     * @param cursor
     *  Object of type Track
     */
    public void setCursor(Track cursor) {
        this.cursor = cursor;
    }

    /**
     * Sets the number of tracks on the platform
     * @param tracks
     *  Integer number of tracks
     */
    public void setTracks(int tracks) {
        this.tracks = tracks;
    }
}

/**
 * Exception class if the track added is the same Track number as a previous one
 */
class TrackExistsException extends Exception {
    public TrackExistsException() {}
}

/**
 * Exception class if the track does not exist
 */
class TrackNotFoundException extends Exception {
    public TrackNotFoundException() {}
}
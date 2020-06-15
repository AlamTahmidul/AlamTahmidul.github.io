/**
 * @name Tahmidul Alam
 * @ID 112784865
 * @email tahmidul.alam@stonybrook.edu
 * @course CSE 214
 * @recitation Recitation 10 (Daniel Calabria)
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  DiningSimulator Traits
 */
public class DiningSimulator {
    private Collection<Restaurant> restaurants = new ArrayList<Restaurant>();
    private int chefs = 1;
    private int duration = 1;
    private double arrivalProb = 0.1;
    private int maxCustomerSize = 1;
    private int numRestaurants = 1;
    private int customersLost = 0;
    private int totalServiceTime = 0;
    private int customersServed = 0;
    private int profit = 0;

    /**
     * Default Constructor
     */
    public DiningSimulator() {}

    /**
     * @return
     *  Simulates the Restaurant cases and returns the average time spent in
     *  restaurant.
     */
    public double simulate() {
        for (int i = 0; i < this.numRestaurants; i++) {
            Restaurant restaurant = new Restaurant(maxCustomerSize);
            restaurants.add(restaurant);
        }
        int temp = 1;
        int restCount = numRestaurants;
        while (temp <= this.duration) {
            System.out.println("\nTime: " + temp);
            for (Restaurant diner : restaurants) {
                if (Math.random() <= arrivalProb) { // Enqueue
                    Customer c = new Customer(randInt(0, 4));
                    System.out.format("Customer #%s has entered " +
                                    "Restaurant %s\n",
                            Customer.getTotalCustomers(),
                            (restCount % numRestaurants) + 1);
                    try {
                        diner.enqueue(c);
                        String f = "Customer #%s has been seated with the " +
                                "order \"%s\".\n";
                        System.out.format(f, Customer.getTotalCustomers(),
                                c.getFood());
                    } catch (NoSpaceException e) {
                        System.out.format("Customer #%s cannot be seated! " +
                                        "They" +
                                " have left the restaurant\n",
                                Customer.getTotalCustomers());
                        this.customersLost++;
                    }
                } // Enqueue end

                if (diner.getCustomerCount() > 0) {
                    int tempPeekCounter = diner.getPeekCounter();
                    int tempCounter = diner.getCounter();
                    while (tempPeekCounter != tempCounter) {
                        if (tempPeekCounter > diner.getCustomers().length - 1)
                            tempPeekCounter = 0; // Wrap around to beginning
                        if (diner.getCustomers()[tempPeekCounter].isState()) { //
                            // If still in restaurant

                            // Change wait time
                            diner.getCustomers()[tempPeekCounter].setTimeToServe(diner.getCustomers()[tempPeekCounter].getTimeToServe() - 5);
                            this.totalServiceTime += 5;

                            // Check to dequeue
                            if (diner.getCustomers()[tempPeekCounter].getTimeToServe() == 0) {
                                System.out.format("Customer #%s has enjoyed their " +
                                                "food! $%s profit.\n",
                                        diner.getCustomers()[tempPeekCounter].getOrderNumber(),
                                        diner.getCustomers()[tempPeekCounter].getPriceOfFood());
                                this.profit += diner.getCustomers()[tempPeekCounter].getPriceOfFood();
                                this.customersServed++;
                                if (tempPeekCounter == diner.getPeekCounter()) {
                                    try {
                                        diner.dequeue();
                                    } catch (EmptyRestaurantException e) {
                                        System.out.println("Empty Restaurant");
                                    }
                                }
                            }
                            tempPeekCounter++; // Change tempPeekCounter
                        }
                    } // End while
                }
                restCount++;
            } // for loop

            for (Restaurant diner : restaurants) { // Prints info
                System.out.println("R" +  ((restCount % numRestaurants) + 1) +
                        ": " + diner.toString());
                restCount++;
            }
            temp++;
        }
        return (double) this.totalServiceTime / (this.customersServed == 0 ?
                1 : this.customersServed);
    }

    /**
     * @param minVal
     *  The min value of the number bound
     * @param maxVal
     *  The max value of the number bound
     * @return
     *  A random integer between minVal and maxVal, inclusive
     */
    private int randInt(int minVal, int maxVal) {
        if (minVal == 0) return (int) (Math.random() * maxVal);
        return (int) ((Math.random()*(minVal + 1)) + (maxVal - minVal));
    }

    /**
     * @param args
     *  Main method calls simulate and prints final stats
     */
    public static void main(String[] args) {
        DiningSimulator ds = new DiningSimulator();
        Scanner stdin = new Scanner(System.in);
        System.out.println("Starting simulator...\n");
        while (true) {
            try {
                System.out.print("Enter the number of restaurants: ");
                ds.setNumRestaurants(stdin.nextInt());
                System.out.print("Enter the maximum number of customers a restaurant" +
                        " can serve: ");
                ds.setMaxCustomerSize(stdin.nextInt());
                System.out.print("Enter the arrival probability of a customer: ");
                ds.setArrivalProb(stdin.nextDouble());
                System.out.print("Enter the number of chefs: ");
                int numChefs = stdin.nextInt();
                ds.setChefs((numChefs > 1 ? numChefs : 0));
                System.out.print("Enter the number of simulation units: ");
                ds.setDuration(stdin.nextInt());
                double avgTime = ds.simulate();
                System.out.println("\nSimulation ending...");
                String endFormat = "Total customer time: %s minutes\n" +
                        "Total customers served: %s\n" +
                        "Average customer time lapse: %s minutes per order\n" +
                        "Total Profit: $%s\n" +
                        "Customers that left: %s\n";
                System.out.format(endFormat, ds.getTotalServiceTime(),
                        ds.getCustomersServed(), avgTime, ds.getProfit(),
                        ds.getCustomersLost());
                System.out.print("\nDo you want to try another simulation? " +
                        "(y/n): ");
                if (stdin.next().equalsIgnoreCase("y")) {
                    main(args);
                } else {
                    System.exit(1);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Try again.");
                stdin.nextLine();
            }
            catch (Exception e) {
                System.out.println("Partial Functionality!");
            }
        }
    }

    // Getters
    public Collection<Restaurant> getRestaurants() {
        return restaurants;
    }

    public double getArrivalProb() {
        return arrivalProb;
    }

    public int getChefs() {
        return chefs;
    }

    public int getCustomersLost() {
        return customersLost;
    }

    public int getCustomersServed() {
        return customersServed;
    }

    public int getDuration() {
        return duration;
    }

    public int getMaxCustomerSize() {
        return maxCustomerSize;
    }

    public int getNumRestaurants() {
        return numRestaurants;
    }

    public int getProfit() {
        return profit;
    }

    public int getTotalServiceTime() {
        return totalServiceTime;
    }

    //Setters
    public void setArrivalProb(double arrivalProb) {
        this.arrivalProb = arrivalProb;
    }

    public void setChefs(int chefs) {
        this.chefs = chefs;
    }

    public void setCustomersLost(int customersLost) {
        this.customersLost = customersLost;
    }

    public void setCustomersServed(int customersServed) {
        this.customersServed = customersServed;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMaxCustomerSize(int maxCustomerSize) {
        this.maxCustomerSize = maxCustomerSize;
    }

    public void setNumRestaurants(int numRestaurants) {
        this.numRestaurants = numRestaurants;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setRestaurants(Collection<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void setTotalServiceTime(int totalServiceTime) {
        this.totalServiceTime = totalServiceTime;
    }
}

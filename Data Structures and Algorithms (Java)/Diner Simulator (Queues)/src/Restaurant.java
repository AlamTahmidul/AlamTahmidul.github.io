/**
 * @name Tahmidul Alam
 * @ID 112784865
 * @email tahmidul.alam@stonybrook.edu
 * @course CSE 214
 * @recitation Recitation 10 (Daniel Calabria)
 */

import java.util.Arrays;

/**
 * Restaurant traits
 */
public class Restaurant {
    private Customer[] customers = new Customer[1];
    private int counter;
    private int peekCounter;
    private int maxCustomers;
    private int customerCount = 0;

    /**
     * Default Constructor
     */
    public Restaurant() {}

    /**
     * @param maxCustomers
     *  Def. Constructor that gets the max number of customers in restaurants
     */
    public Restaurant(int maxCustomers) {
        this.customers = new Customer[maxCustomers];
        this.maxCustomers = maxCustomers;
        this.counter = this.customers.length / 2;
    }

    /**
     * @param c
     *  Attempts to add a customer into a queue
     * @throws NoSpaceException
     *  If there are no more spots available in restaurants
     */
    public void enqueue(Customer c) throws NoSpaceException {
        if (this.counter > customers.length-1) {
            this.counter = 0;
            customers[counter] = c;
            counter++;
        }
        else if (isEmpty()) {
//            System.out.println("Counter:" + counter);
            customers[counter] = c;
            this.peekCounter = counter;
            counter++;
        } else if (this.counter == peekCounter && customerCount == maxCustomers) {
            customerCount = 1;
            throw new NoSpaceException();
        } else {
            customers[counter] = c;
            this.counter++;
        }
        customerCount++;
    }

    /**
     * @return
     *  Customer value of the person done eating
     * @throws EmptyRestaurantException
     *  There are no customers
     */
    public Customer dequeue() throws EmptyRestaurantException {
        if (peekCounter == customers.length-1) { // Last Element
            peekCounter = 0;
            customerCount--;
            return customers[customers.length-1];
        } else if (isEmpty()) {
            throw new EmptyRestaurantException();
        }
        Customer temp = customers[peekCounter];
        peekCounter++;
        customerCount--;
        return temp;
    }

    /**
     * @return
     *  Returns the customer at the top of the queue
     */
    public Customer peek() {
        return customers[peekCounter];
    }

    /**
     * @return
     *  The number of customers in the restaurant
     */
    public int size() {
        return customerCount;
    }

    /**
     * @return
     *  True if there are no customers, false otherwise
     */
    public boolean isEmpty() {
        return customerCount == 0;
    }

    /**
     * @return
     *  Attempts to convert the Restaurant class into string
     */
    @Override
    public String toString() {
        String s = new String();
        s += "{";
        int tempPeekCounter = peekCounter;
        while (tempPeekCounter != counter) {
            if (tempPeekCounter > customers.length-1) tempPeekCounter = 0;
            if (customers[tempPeekCounter] == null) break;
            s += customers[tempPeekCounter].toString() + ", ";
            tempPeekCounter++;
        }
        if (s.lastIndexOf(",") > 0) {
            s = s.substring(0, s.lastIndexOf(","));
        }
        s += "}";
        return s;
//        String[] s = new String[customerCount];
//        int c = this.peekCounter;
//        int tempCustomerCount = this.customerCount;
//        int j = 0;
//        for (int i = 0; i < s.length && tempCustomerCount > 0; i++,
//                tempCustomerCount--) {
//            if (c > customers.length - 1) {
//                c = 0;
//            }
//            if (customers[c].isState()) {
//                s[j] = customers[c].toString();
//                j++;
//                tempCustomerCount--;
//            }
//            c++;
//        }
//        return Arrays.toString(s);
    }

    // Getters
    /**
     * @return
     *  returns the array of customers
     */
    public Customer[] getCustomers() {
        return customers;
    }

    /**
     * @return
     *  Gets the counter (the last queue)
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @return
     *  number of customers
     */
    public int getCustomerCount() {
        return customerCount;
    }

    /**
     * @return
     * max customers in restaurant
     */
    public int getMaxCustomers() {
        return maxCustomers;
    }

    /**
     * @return
     *  top of the queue
     */
    public int getPeekCounter() {
        return peekCounter;
    }

    /**
     * @param counter
     *  sets the end of the queue
     */
    // Setters
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * @param customerCount
     *  sets the number of customers
     */
    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    /**
     * @param customers
     *  sets the array of customers
     */
    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    /**
     * @param maxCustomers
     *  sets the max number of customers
     */
    public void setMaxCustomers(int maxCustomers) {
        this.maxCustomers = maxCustomers;
    }

    /**
     * @param peekCounter
     *  sets the top of the queue
     */
    public void setPeekCounter(int peekCounter) {
        this.peekCounter = peekCounter;
    }
}

/**
 *  Exception for full restaurant
 */
class NoSpaceException extends Exception {
    public NoSpaceException() {}
}

/**
 *  Exception for no customers in restaurant
 */
class EmptyRestaurantException extends Exception {
    public EmptyRestaurantException() {}
}
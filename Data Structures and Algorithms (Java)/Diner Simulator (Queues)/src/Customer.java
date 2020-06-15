/**
 * @name Tahmidul Alam
 * @ID 112784865
 * @email tahmidul.alam@stonybrook.edu
 * @course CSE 214
 * @recitation Recitation 10 (Daniel Calabria)
 */

import java.util.Arrays;

/**
 * Customer Traits
 */
public class Customer {
    private static int totalCustomers = 0;
    private int orderNumber;
    private String food;
    private String shortenedFood;
    int priceOfFood;
    int timeArrived;
    int timeToServe;
    private boolean state;

    final String[] foodOptions = {"Cheeseburger", "Steak", "Grilled Cheese",
            "Chicken Tenders", "Chicken Wings", "C", "S", "GC", "CT", "CW"};
    final int[] prices = {15, 25, 10, 10, 20};
    final int[] waitTime = {25, 30, 15, 25, 30};

    /**
     * Default constructor
     */
    public Customer() {this.state = false;}

    /**
     * @param choice
     *  Def. constructor with the food choice randomized from main
     */
    public Customer(int choice) {
        totalCustomers++;
        this.orderNumber = totalCustomers;
        this.priceOfFood = prices[choice];
        this.timeToServe = waitTime[choice];
        this.shortenedFood = foodOptions[choice + 5];
        this.food = foodOptions[choice];
        this.state = true;
    }

    /**
     * @return Converts the Customer class in a array of String
     */
    @Override
    public String toString() {
        String[] s = {"#" + this.orderNumber, this.shortenedFood,
                this.timeToServe + " min."};
        return Arrays.toString(s);
    }

    // Getters
    public int getOrderNumber() {
        return orderNumber;
    }

    public int getPriceOfFood() {
        return priceOfFood;
    }

    public int getTimeArrived() {
        return timeArrived;
    }

    public int getTimeToServe() {
        return timeToServe;
    }

    public static int getTotalCustomers() {
        return totalCustomers;
    }

    public int[] getPrices() {
        return prices;
    }

    public int[] getWaitTime() {
        return waitTime;
    }

    public String getFood() {
        return food;
    }

    public String[] getFoodOptions() {
        return foodOptions;
    }

    public String getShortenedFood() {
        return shortenedFood;
    }

    public boolean isState() {
        return state;
    }

    // Setters
    public void setFood(String food) {
        this.food = food;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setPriceOfFood(int priceOfFood) {
        this.priceOfFood = priceOfFood;
    }

    public void setTimeArrived(int timeArrived) {
        this.timeArrived = timeArrived;
    }

    public void setTimeToServe(int timeToServe) {
        this.timeToServe = timeToServe;
    }

    public static void setTotalCustomers(int totalCustomers) {
        Customer.totalCustomers = totalCustomers;
    }

    public void setShortenedFood(String shortenedFood) {
        this.shortenedFood = shortenedFood;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
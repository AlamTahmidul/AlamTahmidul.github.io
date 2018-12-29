/**
 * cash.c
 *
 * PSET1
 * CS50AP
 *
 * Tahmidul Alam
 *
*/
#include <cs50.h>
#include <stdio.h>
#include <math.h>

// Coin constants
#define QTR 25
#define DIM 10
#define NIC 5

int main(void)
{
    // Get User Input until it's not negative
    int user_input;
    do
    {
        user_input = roundf(get_float("Change owed: ") * 100.0);
    }
    while (user_input < 0);

    int coins = 0;

    // Finding the whole number of quarters
    int quarters = user_input / QTR;

    // Remaining Coins (Not quarters)
    user_input %= QTR;

    // Whole number of dimes
    int dimes = user_input / DIM;

    // Remaining Coins (Not Dimes)
    user_input %= DIM;

    // Whole number of nickels
    int nickels = user_input / NIC;

    // Rest of the coins
    user_input %= NIC;

    // Adding the number of coins
    coins = user_input + quarters + nickels + dimes;

    // What the user sees
    printf("%i\n", coins);
}
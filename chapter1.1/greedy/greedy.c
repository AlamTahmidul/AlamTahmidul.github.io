#include <stdio.h>
#include <cs50.h>
#include <math.h>

int main(void)
{
    printf("Hello. How much change would you like to receive in the minimum amount of coins?\n");
    float value_given;
    do
    {
        value_given = get_float();
        if (value_given <= 0)
        {
            printf("You must be losing some brain cells.\nPlease input the correct number of change in a format like : 0.41\n");
        }
    }
    while (value_given < 0);

    int value = (int)round(value_given * 100);

    int quarter = 25;
    int quarter_count = 0;

    int dimes = 10;
    int dime_count = 0;

    int nickel = 5;
    int nickel_count = 0;

    int pennies = 1;
    int penny_count = 0;

    int remaining_value;

// Modular Approach

    quarter_count = value / quarter;
    remaining_value = value % quarter;

    dime_count = remaining_value / dimes;
    remaining_value %= dimes;

    nickel_count = remaining_value / nickel;
    remaining_value %= nickel;

    penny_count = remaining_value / pennies;
    remaining_value %= pennies;

    printf("%i\n", quarter_count + dime_count + nickel_count + penny_count);
    /* Finding how many of each coin to satisfy the bottom comments */
    printf("%i quarter(s), %i dime(s), %i nickel(s), %i penny(ies).\n", quarter_count, dime_count,
           nickel_count, penny_count);
}

/*
    Thought Process:

    0.61 % 0.25 = 0.11 (2 Quarters)
    0.11 % 0.10 = 0.01 (1 Dime)
    0.01 % 0.05 = 0.01 (0 Nickels)
    0.01 % 0.01 = 0.00 (1 Penny)

    Output: 4 Coins

    3.69 % 0.25 = 0.19 (14 Quarters)
    0.19 % 0.10 = 0.09 (1 Dime)
    0.09 % 0.05 = 0.04 (1 Nickel)
    0.04 % 0.01 = 0.00 (4 Pennies)

    Output: 4 Coins

    2.87 % 0.25 = 0.12 (11 Quarters)
    0.12 % 0.10 = 0.02 (1 Dime)
    0.02 % 0.05 = 0.02 (0 Nickels)
    0.02 % 0.01 = 0.00 (2 Pennies)

    Output: 4 Coins

    ** Generates via ./greedy **
*/

// Created by: Tahmidul Alam
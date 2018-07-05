/**
 * pennies.c
 *
 * CS50 AP
 * Old Friends
 *
 * Calculates how many pennies a user would get if their change
 * doubles each day using command line information
 *
 * Tahmidul Alam
 */

#include <cs50.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>

/* // defined constants
#define MAX_DAYS 31
#define MIN_DAYS 28
*/

int main(int argc, string argv[])
{
    if (argc != 3)
    {
        return 1;
    }
    else
    {

        int days;
        int pennies;
        if (((atoi(argv[1]) < 28) || (atoi(argv[2]) > 31)) || (atoi(argv[2]) < 0))
        {
            return 1;
        }
        // get a number of days in [28, 31]
        days = atoi(argv[1]);
        // get a number of pennies in [0, inf)
        pennies = atoi(argv[2]);


        // relatively few pennies required to get out of bounds of int
        long long total = pennies;

        // add double the amount of pennies of the previous day each day
        for (int i = 1; i < days; i++)
        {
            total += pennies * pow(2, i);
        }

        // output the total as dollars and cents only
        printf("$%.2f\n", ((double) total) / 100);

        return 0;
    }
}

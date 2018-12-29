// Generates pseudorandom numbers in [0,LIMIT), one per line

#define _XOPEN_SOURCE

#include <cs50.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Constant
#define LIMIT 65536

int main(int argc, string argv[])
{
    // Command Line Input is not 2 or 3
    if (argc != 2 && argc != 3)
    {
        printf("Usage: generate n [s]\n");
        return 1;
    }

    // Converting CLI as a integer and placing it into n
    int n = atoi(argv[1]);

    // If there are 3 CLI, generate the random number from the last input
    if (argc == 3)
    {
        srand48((long int) atoi(argv[2]));
    }
    else
    {
        srand48((long int) time(NULL));
    }

    // loop through the value of the 1st CLI until it hits the limit.
    for (int i = 0; i < n; i++)
    {
        printf("%i\n", (int)(drand48() * LIMIT));
    }

    // Success
    return 0;
}

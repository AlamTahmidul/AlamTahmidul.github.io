/**
 * water.c
 *
 * PSET1
 * CS50AP
 *
 * Tahmidul Alam
 *
*/
#include <stdio.h>
#include <cs50.h>

int main(void)
{
    // Get minutes
    int minutes = get_int("Minutes: ");
    // Print output
    printf("Bottles: %i\n", minutes * 12);
}
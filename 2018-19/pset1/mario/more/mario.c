/**
 * mario.c (Less Comfortable)
 *
 * PSET1
 * CS50AP
 *
 * Tahmidul Alam
 *
*/
#include <stdio.h>
#include <cs50.h>
#include <math.h>

int main(void)
{
    // Get Validate User Input by checking negative or above 23
    int half_pyramid_height = 0;
    do
    {
        half_pyramid_height = get_int("Enter Height that is Below 23: ");
    }
    while (half_pyramid_height < 0 || half_pyramid_height > 23);

    // Outside loop based on the height
    for (int i = 0; i < half_pyramid_height; i++)
    {

        // Inside loop 1: Printing out the spaces on the left column
        for (int block_space = i; block_space < half_pyramid_height - 1; block_space++)
        {
            printf(" ");
        }
        // Inside loop 2: Printing out the pyramid on the left column
        for (int block_left = 0; block_left < i + 2; block_left++)
        {
            printf("#");
        }
        // Inside loop 3: Printing out the spaces on the right column
        printf("  ");
        // Inside loop 4: Printing out the pyramid on the right column
        for (int block_right = 0; block_right < i + 1; block_right++)
        {
            printf("#");
        }
        // Puts the pyramid down
        printf("\n");
    }
    return 0;
}
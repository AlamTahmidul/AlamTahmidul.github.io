/**
 * Tahmidul Alam
 * mario.c
 * Ms. Tanzosh
 *
*/
#include <stdio.h>
#include <cs50.h>
#include <math.h>

int main(void)
{
    int half_pyramid_height;
    half_pyramid_height = 0;
    do
    {
        half_pyramid_height = get_int("Height: ");
    }
    while (half_pyramid_height < 0 || half_pyramid_height > 23);

    for (int i = 0; i < half_pyramid_height; i++)
    {
        for (int spacing = 0; spacing < (half_pyramid_height - i) - 1; spacing++)
        {
            printf(" ");
        }

        for (int pyramid = 0; pyramid < i + 2; pyramid++)
        {
            printf("#");
        }
        printf("\n");
    }
    return 0;
}
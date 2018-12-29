/**
 * initials.c
 * Less Comfortable
 *
 * PSET2
 * CS50AP
 *
 * Tahmidul Alam
 *
*/
#include <cs50.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>

int main(void)
{
    // Get User Input
    string name = get_string();
    // Check input NULL
    if (name != NULL)
    {
        // Print first letter
        printf("%c", toupper(name[0]));
        // Loop through the rest of the string
        for (int i = 0, len = strlen(name); i < len; i++)
        {
            // If the string is not at the end and if the string has a space
            // then print out the next letter after the space
            if (name[i + 1] != '\0' && name[i] == ' ')
            {
                // print in uppercase
                printf("%c", toupper(name[i + 1]));
            }
        }
        // For clarity
        printf("\n");
    }
}
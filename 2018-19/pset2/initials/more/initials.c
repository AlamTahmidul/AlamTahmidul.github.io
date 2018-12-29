/**
 * initials.c
 * More Comfortable
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
        // Loop through the rest of the string
        for (int i = 0, len = strlen(name); i < len; i++)
        {
            // Check for the first letter of the first name
            if (i == 0 && isalpha(name[0]))
            {
                // Print out the First Character of the name
                printf("%c", toupper(name[0]));
            }
            // Check if there is a letter next to a space
            else if (name[i] == ' ' && isalpha(name[i + 1]))
            {
                // If there is, print out the letter
                printf("%c", toupper(name[i + 1]));
            }
        }
        // For clarity
        printf("\n");
    }
}
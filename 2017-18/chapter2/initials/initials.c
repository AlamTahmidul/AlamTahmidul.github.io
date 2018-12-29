/**
 * Tahmidul Alam
 * Computer Science
 * Marking Period 2
 * Initials
 * N
 *
 * Create a program that prints out the first letter of each word in the name
*/

#include <cs50.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <ctype.h>

int main(void)
{
    string name = get_string();
    if (name != NULL)
    {
        printf("%c", toupper(name[0]));
        for (int i = 0, n = strlen(name); i < n; i++)
        {
            if (name[i] == ' ' && name[i + 1] != '\0')
            {
                printf("%c", toupper(name[i + 1]));
            }
        }
        printf("\n");
    }
}
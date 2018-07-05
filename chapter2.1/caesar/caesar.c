/**
 *
 * Tahmidul Alam
 * CS50
 * Ms. Tanzosh
 *
 * Caesar Cypher
*/

#include <stdio.h>
#include <cs50.h>
#include <string.h>
#include <ctype.h>

int main(int argc, string argv[])
{
    if (argc != 2)
    {
        printf("Usage: ./caesar 13\n");
        return 1;
    }

    int input = atoi(argv[1]);

    if (input < 0)
    {
        printf("Usage: ./caesar 13\n");
        return 1;
    }
    else
    {
        string input_code = get_string("Enter Text: ");
        printf("ciphertext: ");
        for (int i = 0, n = strlen(input_code); i < n; i++)
        {
            if (islower(input_code[i]))
            {
                printf("%c", (((input_code[i] + input) - 'a') % 26) + 'a');
            }
            else if (isupper(input_code[i]))
            {
                printf("%c", (((input_code[i] + input) - 'A') % 26) + 'A');
            }
            else
            {
                printf("%c", input_code[i]);
            }
        }
    }
    printf("\n");
    return 0;
}
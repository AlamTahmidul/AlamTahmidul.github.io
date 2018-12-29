/**
 * caesar.c
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

void caesar(string input, int cipher);

int main(int argc, string argv[])
{
    // If CLI does not have exactly 2 Args
    if (argc != 2)
    {
        printf("Usage: ./caesar 13\n");
        return 1;
    }

    // 2nd CLI is a number. Must convert it
    int input = atoi(argv[1]);

    // If the input is negative, it's false
    if (input < 0)
    {
        printf("Usage: ./caesar 13\n");
        return 1;
    }
    // Otherwise, get the user input as it is.
    else
    {
        // User input
        string input_code = get_string("Enter Text: ");

        // Run Caesar Cipher Function
        // Input_code => the User Text
        // Input => Ciphering based on the user input of the number
        caesar(input_code, input);
    }
    printf("\n");
    return 0;
}

// Caesar Function
void caesar(string input, int cipher)
{
    // Must display
    printf("ciphertext: ");
    for (int i = 0, n = strlen(input); i < n; i++)
    {
        // If lowercase, print out lowercase
        if (islower(input[i]))
        {
            // Done
            printf("%c", (((input[i] + cipher) - 'a') % 26) + 'a');
        }
        // If uppercase, print out uppercase
        else if (isupper(input[i]))
        {
            printf("%c", (((input[i] + cipher) - 'A') % 26) + 'A');
        }
        // Otherwise, print as it is
        else
        {
            printf("%c", input[i]);
        }
    }
    return;
}
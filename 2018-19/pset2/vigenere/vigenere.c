/**
 * vigenere.c
 *
 * PSET2
 * CS50AP
 *
 * Tahmidul Alam
 *
*/
#include <cs50.h>
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>

void vigenere(string input, string input_key, int len_key);
bool vigenere_key_check(string cli);

int main(int argc, string argv[])
{
    // If the argument count is not 2, user input is wrong
    if (argc != 2)
    {
        printf("Usage: ./vigenere abcde");
        return 1;
    }

    // Check if the 2nd command line argument is in letters
    if (!vigenere_key_check(argv[1]))
    {
        return 1;
    }

    // Get user input
    string input_code = get_string("plaintext: ");

    if (input_code != NULL)
    {
        // Get the key from argv[1] - CLI
        string input_key = argv[1];

        // Determine the key length
        int input_key_strlen = strlen(input_key);

        // Run Function vigenere based on the 3 inputs from above
        vigenere(input_code, input_key, input_key_strlen);

        // For Clarity
        printf("\n");
        return 0;
    }
}

void vigenere(string input, string input_key, int len_key)
{
    // Print out the text
    printf("ciphertext: ");
    for (int i = 0, j = 0, n = strlen(input); i < n ; i++, j++)
    {
        // Creating a "key" to convert letters to it's numbers
        int code_key = tolower(input_key[j % len_key]) - 'a';

        // If input is uppercase
        if (isupper(input[i]))
        {
            // Print out an uppercase based on the "key"
            // Dial up or down
            printf("%c", (((input[i] + code_key) - 'A') % 26) + 'A');
        }
        // If input is lowercase
        else if (islower(input[i]))
        {
            // Print out a lowercase based on the "key"
            // Dial up or down
            printf("%c", (((input[i] + code_key) - 'a') % 26) + 'a');
        }
        else
        {
            // Print out the input
            printf("%c", input[i]);
            j--;
        }
    }
    return;
}

bool vigenere_key_check(string cli)
{
    // Temp var to check if the input follows condition
    bool check_pass = true;

    // Iterate through string and check if the input has any letters
    for (int i = 0, len = strlen(cli); i < len; i++)
    {
        if (!isalpha(cli[i]))
        {
            printf("Usage: ./vigenere abcde\n");
            check_pass = false;
        }
    }

    // Return the bool
    // True = Condition follows
    // False = Condition not follow
    return check_pass;
}
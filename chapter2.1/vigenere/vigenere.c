/**
 * Tahmidul Alam
 * vigenere.c
 * Ms. Tanzosh
 *
 * Encrypted caesar's cipher with letters
 *
 */
#include <cs50.h>
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, string argv[])
{
    if (argc != 2)
    {
        printf("Usage: ./vigenere toasty");
        return 1;
    }
    for (int i = 0; i < strlen(argv[1]); i++)
    {
        if (!isalpha(argv[1][i]))
        {
            return 1;
        }
    }
    string plaintext = get_string("plaintext: ");
    string input_key = argv[1];
    int input_key_strlen = strlen(input_key);
    printf("ciphertext: ");
    for (int i = 0, j = 0, n = strlen(plaintext); i < n ; i++, j++)
    {
        int code_key = tolower(input_key[j % input_key_strlen]) - 'a';

        if (isupper(plaintext[i]))
        {
            printf("%c", (((plaintext[i] + code_key) - 'A') % 26) + 'A');
        }
        else if (islower(plaintext[i]))
        {
            printf("%c", (((plaintext[i] + code_key) - 'a') % 26) + 'a');
        }
        else
        {
            printf("%c", plaintext[i]);
            j--;
        }
    }
    printf("\n");
    return 0;
}
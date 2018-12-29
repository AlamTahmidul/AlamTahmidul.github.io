/**
 * hello.c
 *
 * CS50 AP
 * Old Friends
 *
 * Greets a user by their name.
 *
 * Tahmidul Alam
 */

#include <cs50.h>
#include <stdio.h>

int main(int argc, string argv[])
{
    // collect a string from the user, then print their name
    if (argc != 2)
    {
        printf("Usage: ./hello <name>\n");
        return 1;
    }
    else
    {
        printf("Hello, %s!\n", argv[1]);
    }
}
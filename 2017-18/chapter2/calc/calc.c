/**
 * Tahmidul Alam
 * Ms. Tanzosh
 * CS50
 *
 * Calc
 *
*/
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <cs50.h>

int main(int argc, string argv[])
{
    if (argc != 4)
    {
        return 1;
    }
    if (argv[2][0] != '+' && argv[2][0] != '-' && argv[2][0] != 'x' && argv[2][0] != '/'
        && argv[2][0] != '%')
    {
        return 1;
    }
    string input = argv[2];
    char sign = input[0];
    float first = atof(argv[1]);
    float second = atof(argv[3]);
    int mod;

    switch (sign)
    {
        case '+':
            printf("%f\n", first + second);
            break;
        case 'x':
            printf("%f\n", first * second);
            break;
        case '-':
            printf("%f\n", first - second);
            break;
        case '/':
            printf("%f\n", first / second);
        case '%':
            mod = (int) (first / second);
            printf("%f\n", first - (second * mod));
    }
    return 0;
}
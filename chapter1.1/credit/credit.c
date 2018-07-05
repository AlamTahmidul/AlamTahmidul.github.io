#include <stdio.h>
#include <math.h>
#include <cs50.h>

int main(void)
{
// Asking for User Input
    long long card_number;
    do
    {
        card_number = get_long_long("Enter Card Number: ");
    }
    while (card_number < 0);

// Validate the amount of Digits Entered

    int digits = 0;

    while (digits <= 0)
    {
    digits = card_number / 10;
    }
    printf("%i\n", digits);
}

// 378282246310005
#include <stdio.h>
#include <math.h>

#define QUARTER 25;
#define DIMES 10;
#define NICKELS 5;

int main(int argc, char const *argv[])
{
    int q, d, n;
    float userInput;

    do {
        printf("Change: \n");
        scanf("%f", &userInput);
        if (userInput <= 0.0f) {
            printf("Invalid Value! Enter a value > 0.00");
        }
    } while (userInput < 0);

    int wholeVal = (int) round(userInput * 100);
    
    q = wholeVal / QUARTER;
    wholeVal %= QUARTER;

    d = wholeVal / DIMES;
    wholeVal %= DIMES;

    n = wholeVal / NICKELS;
    wholeVal %= NICKELS;

    int total = q + d + n + wholeVal;
    printf("Quarters: %d\nDimes: %d\nNickels: %d\nPennies: %d\n", q, d, n, wholeVal);
    printf("Total Coins: %d", total);
    return 0;
}

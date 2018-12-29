#include <stdio.h>
#include <cs50.h>
#include <math.h>

int main(void)
{
    int x;

    do
    {
        x = get_int("Days in month: ");
    }
    while  ((x <= 27) || (x >= 32));


    int p;

    do
    {
        p = get_int("Pennies on first day: ");

    }
    while (p < 1);

    double z = pow(2, x);
    printf("$%.2lf\n", (((((p * z)  - p) / 100))));

}
// Created by: Tahmidul Alam
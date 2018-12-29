#include <stdio.h>
#include <math.h>
#include <cs50.h>

int main(void) {
    int d;
    long p;

    do
    {
    d = get_int("Days is month: ");
    }
    while (d < 28 || d > 31);

    do
    {
        p = get_int("Pennies on first day: ");
    }
    while (p <= 0);

    long long total = 0;
    for (int i = 0; i < d; i++)
    {
        total += p;
        p *= 2;
    }
    double dollar_form = (double) total / 100;
    printf("$%.2lf\n", dollar_form);
}
/**
 * isbn.c
 *
 * Ms. Tanzosh
 * Period 7
 *
 * Validate ISBN
 *
 * Tahmidul Alam
*/
#include <stdio.h>
#include <math.h>
#include <cs50.h>

int main(void)
{
    long long isbn;

    do
    {
        isbn = get_long_long("ISBN: ");
    }
    while (isbn < 0);

    int new_isbn;
    new_isbn = 0;
    int sum_isbn;
    sum_isbn = 0;

    for (int i = 10; i >= 0; i--)
    {
        new_isbn = isbn % 10;
        sum_isbn += new_isbn * i;
        isbn /= 10;
    }

    if (sum_isbn % 11 == 0)
    {
        printf("YES\n");
    }
    else
    {
        printf("NO\n");
    }
    return 0;
}
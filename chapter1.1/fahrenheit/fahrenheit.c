#include <stdio.h>
#include <cs50.h>

int main(void)
{
    float C = get_float("C: ");
    float fahrenheit = (9.0 / 5.0) * C + 32;
    printf("F: %.1f \n", fahrenheit);
}
// Name: Tahmidul Alam
// Homework: fahrenheit.c
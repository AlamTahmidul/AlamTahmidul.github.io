#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <cs50.h>

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    double a = get_double();
    double b = get_double();
    int n = get_int();

    double converta = a / b;
    double convertb = b / a;
    double input;
    char type;

    for (int i = 0; i < n; i++)
    {
        input = get_double();
        type = get_char();

        if (type == 'A')
        {
            printf("%f\n", input * convertb);
        }
        else
        {
            printf("%f\n", input * converta);
        }
    }

    return 0;
}

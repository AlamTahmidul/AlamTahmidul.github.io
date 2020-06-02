#include <stdio.h>

int main(int argc, char argv[])
{
    float f;
    printf("Fahrenheit to Celsius Program.\n Type in temperature in Fahrenheit: ");
    scanf("%f", &f);
    
    float c = (f - 32) * (5.0 / 9.0);
    printf("Temperature in F: %.2f\n", f);
    printf("Temperature in C: %.2f", c);

    return 0;
}
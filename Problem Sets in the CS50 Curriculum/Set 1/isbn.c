#include <stdio.h>
#include <math.h>

int main(int argc, char const *argv[])
{
    long long isbn;

    do {
        printf("Enter ISBN Number: ");
        scanf("%lld", &isbn);
    } while (isbn < 0);

    int sum = 0;
    for (int i = 10; i > 0; i--)  {
        sum += (isbn % 10) * i;
        isbn /= 10;
    }

    (sum % 11 == 0) ? printf("Valid ISBN!\n") : printf("Invalid ISBN!!\n");

    return 0;
}

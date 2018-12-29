#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <cs50.h>

void sort(long long values[], long long values2[], int n)
{
    // TODO: implement an O(n^2) sorting algorithm
    int counter = 1;
    while (counter > 0)
    {
        counter = 0;
        for (int i = 0; i < n - 1; i++)
        {
            if (values[i] > values[i+1])
            {
                int temp = values[i];
                int temp2 = values2[i];
                values[i] =  values[i+1];
                values2[i] = values2[i+1];
                values[i+1] = temp;
                values2[i+1] = temp2;
                counter++;
            }
        }
    }
    return;
}


int main(void) {
    // read the instance
    int N;
    long long M;
    N = get_int();
    M = get_long_long();

    long long A[N];
    long long B[N];

    for (int i = 0; i < N; i++)
    {
        A[i] = get_long_long();
    }

    for (int i = 0; i < N; i++)
    {
        B[i] = get_long_long();
    }


    sort(A, B, N);

    for (int i = 0; i < N; i++) {
        if (B[i] > A[i] && M >= A[i])
        {
            // This task is reasonable, Dudu has enough money
            M += B[i] - A[i];
        }
    }

    printf("%lld\n", M);

    return 0;
}

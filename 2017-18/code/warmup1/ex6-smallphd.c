#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <cs50.h>


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

    bool solved[N];
    for (int i = 0; i < N; i++)
    {
        solved[i] = false;
    }

    while (true) {
        // find any "reasonable" task that Dudu can solve
        int cansolve = -1;
        for (int i = 0; i < N; i++) {
            if (B[i] > A[i] && M >= A[i] && !solved[i])
            {
                // This task is reasonable, Dudu has enough money, and it wasn't
                // solved yet.
                cansolve = i;
                break;
            }
        }

        if (cansolve == -1)
        {
            // Dudu can't solve any new tasks
            break;
        }
        else
        {
            // Solve it
            solved[cansolve] = true;
            M += B[cansolve] - A[cansolve];
        }
    }
    printf("%lld\n", M);

    return 0;
}

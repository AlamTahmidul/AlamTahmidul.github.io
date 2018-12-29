#include <cstdio>
using namespace std;

/******************************************************************************
 * This problem is way simpler than it seems.
 *
 * While there are several possible solutions, I will explain the simplest
 * one:
 *
 * The total amount of charge the phone has is C+D coulombs, and it uses its
 * charge at a rate of A - B.
 *
 * So the amount of time it will last is (C+D)/(A-B)
 *
 * The only caveat is that C+D might be too big to fit in an int, so we need
 * to use long long (or long in Java).
 *****************************************************************************/

int main() {
    long long A, B, C, D;
    scanf("%lld %lld %lld %lld", &A, &B, &C, &D);
    printf("%lld\n", (C+D)/(A-B));
    return 0;
}

#include <cstdio>
using namespace std;

/******************************************************************************
 * Let's start considering a naive approach for this problem:
 *
 * We can do something like (in pseudo-code below):
 *
 * long long answer = 1; // Dudu's throne
 * for (long long x = 0; x <= N; x++) {
 *   for (long long y = 0; y <= N; y++) {
 *     if (x * y > N) {
 *       answer++;
 *     }
 *   }
 * }
 * print(answer);
 *
 *
 * This solution works in time O(N^2), which for the input of N <= 1000000
 * implies in a staggering 1 trillion basic operations (a modern computer would
 * take several tens of minutes to run this).
 *
 * We can do better by realizing that the second for loop (the one on y) is
 * unnecessary.
 *
 * In particular, we have that the if statement is true if and only if (some algebra):
 * x * y > N
 * therefore
 * y > N / x (assuming x > 0, if x == 0 the statement is always false)
 *
 * This statement will be FALSE for y = 0, 1, ..., N / x (rounded down), which
 * means that it will be FALSE for N/x + 1 different values of y.
 *
 * Since there are N + 1 possible values for y, the statement will be true for
 * N - N/x different values of y.
 *
 * We arrive in the code below, that runs in time O(N).
 *
 * We can do even faster than this. The "large" version of this problem will be
 * in the Warm Up 2 \o/. (There will be even more algebra involved.)
 ******************************************************************************/

int main() {
    long long N;
    scanf("%lld", &N);
    long long answer = 1; // Dudu's throne
    for (long long x = 1; x <= N; x++) {
        answer += N - N / x;
    }
    printf("%lld\n", answer);
    return 0;
}

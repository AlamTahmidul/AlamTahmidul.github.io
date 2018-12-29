#include <cstdio>
#include <vector>
using namespace std;

/******************************************************************************
 * For this problem, the first thing one has to realize is that it doesn't make
 * any sense to attempt a task where B <= A, since Dudu wouldn't get any money
 * by solving such a task.
 *
 * Among the "reasonable" tasks a greedy approach works. A greedy approach is:
 * At each time Dudu solves any task for which he has enough money to attempt. 
 *
 * Note that in the solution below the "while loop" runs for at most N
 * iterations, each one with a "for loop" that runs for N iterations, so this
 * code is O(N^2).
 *
 * For N <= 1000 we are doing 1M operations, which is fast enough. This solution
 * won't work for the "large" input, though (with N <= 100K, which means 10B
 * operations). We need to be smarter.
 *****************************************************************************/

int main() {
    // read the instance
    int N;
    long long M;
    scanf("%d %lld", &N, &M);
    vector<long long> A(N), B(N);
    for (int i = 0; i < N; i++) scanf("%lld", &A[i]);
    for (int i = 0; i < N; i++) scanf("%lld", &B[i]);

    vector<bool> solved(N, false); // marks which tasks were solved already

    while (true) {
        // find any "reasonable" task that Dudu can solve
        int cansolve = -1;
        for (int i = 0; i < N; i++) {
            if (B[i] > A[i] && M >= A[i] && !solved[i]) {
                // This task is reasonable, Dudu has enough money, and it wasn't
                // solved yet.
                cansolve = i;
                break;
            }
        }
        if (cansolve == -1) { // Dudu can't solve any new tasks
            break;
        } else {
            // Solve it
            solved[cansolve] = true;
            M += B[cansolve] - A[cansolve];
        }
    }
    printf("%lld\n", M);

    return 0;
}

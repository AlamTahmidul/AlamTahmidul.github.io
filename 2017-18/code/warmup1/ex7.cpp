#include <cstdio>
#include <algorithm>
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
 * As noted on the code for the "small" version, simply doing it greedly would
 * be too slow, so we have to figure out something else.
 *
 * First discard all unreasonable tasks. There is no reason to think about them.
 *
 * Now, what if Dudu always solves the cheapest reasonable task available
 * instead of any of them?
 *
 * If we want to do this we can simply solve the reasonable tasks in order of
 * cost.
 *
 * Apart from the sorting the code below runs in O(N). Sorting N elements takes
 * time O(N log N), so the code runs in time O(N log N) in total.
 *
 * For N <= 100K this means more or less 1.7M operations, which is blazing fast.
 *****************************************************************************/

int main() {
    // read the instance
    int N;
    long long M;
    scanf("%d %lld", &N, &M);
    vector<pair<long long, long long>> tasks(N);
    for (int i = 0; i < N; i++) scanf("%lld", &tasks[i].first);
    for (int i = 0; i < N; i++) scanf("%lld", &tasks[i].second);
 
    // Now sort all tasks.
    //
    // pair in C++ is sorted lexicographically (like "alphabetic order"), which
    // is exactly what we want, since we want to sort by cost, which is .first.
    sort(tasks.begin(), tasks.end());

    for (auto &task : tasks) {
        if (task.second <= task.first) {
            continue; // this task is unreasonable, go to the next one
        }
        if (task.first <= M) // can solve this reasonable task
            M += task.second - task.first;
        else 
            break; // This "else break" is unnecessary. Why is it correct? Why is it unnecessary?
    }
    printf("%lld\n", M);
    return 0;
}

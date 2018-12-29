#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

/******************************************************************************
 * To solve this problem, the first thing one has to realize is that the best
 * possible way (the *optimal* way) for Dudu's team to solve all problems is to
 * solve them in increasing order of time (there is a proof of this after the
 * code, for those of you who are curious.
 *
 * After you know that it becomes a simple question of keeping track of who is
 * free and how many problems are open.
 *
 * The algorithm works as follows:
 * 1) sort all tasks by time
 * 2) set all N team members as available at time 0
 * 3) for each task with time to process t:
 * 3.a) Let first_free be the time of the first team member to become available
 * 3.b) This member will finish solving the task at time_end = first_free + t.
 * 3.c) Add time_end to the answer
 * 3.d) Mark that team_member as being "free again" at time_end.
 *
 * There are some considerations when coding parts 2 and 3. These considerations
 * will be explained after the code.
 *****************************************************************************/

int main() {
    int N, M;
    scanf("%d %d", &N, &M);
   
    // equivalent to List<Integer> time_to_solve = new ArrayList<>(); in Java
    vector<int> time_to_solve;
    for (int i = 0; i < M; i++) {
        int temp;
        scanf("%d", &temp);
        // equivalent to time_to_solve.add(temp);
        time_to_solve.push_back(temp);
    }
    sort(time_to_solve.begin(), time_to_solve.end()); // sort it
    // The answer might be larger than what fits in an int.
    // This is equivalent to "long answer = 0;" in Java
    long long answer = 0;

    vector<int> time_member_will_be_free(N, 0); // initialize all members to being free at 0

    for (int t : time_to_solve) {
        // get the next time a team member will be available
        int first_to_be_free = 0;
        for (int i = 1; i < N; i++) {
            if (time_member_will_be_free[i] < time_member_will_be_free[first_to_be_free]) {
                first_to_be_free = i;
            }
        }
        int time_start = time_member_will_be_free[first_to_be_free];
        int time_end = time_start + t;
        answer += time_end;
        // the solver that will solve the current problem will be available
        // again at time "time_end"
        time_member_will_be_free[first_to_be_free] = time_end;
    }
    printf("%lld\n", answer);
    return 0;
}

/******************************************************************************
 * This problem has two complications, and here we address both of them.
 *
 * == How to do parts 2 and 3 more efficiently ==
 * The trivial way of doing parts 2 and 3 would be to create a vector (or list,
 * or List, depending on your choice of language) with the time each person
 * becomes available. Then the code for part 3.a would be something like:
 *
 * int member_first_free = 0;
 * for (int i = 1; i < N; i++) {
 *   if (time_free[i] < time_free[member_first_free]) {
 *      member_first_free = i;
 *   }
 * }
 *
 * (Some languages provide functions to get this value automatically, like
 * "min_element" in C++, but these functions simply do this for behind the
 * scene).
 *
 * If we do this the time necessary to solve an instance with N members and M
 * tasks will be proportional to N*M (this is called O(N*M) in Computer Science
 * linguo.)
 *
 * For N = 10000 and M = 10000 this would mean 100M (one hundred million) basic
 * operations, which for nowadays computers is small enough for the time limit
 * of Hackerrank.
 *
 * We could have made this problem considerably more complex by making, say, the
 * number of tasks M be up to 1M. In this case N*M would be 10B, which a modern
 * computer cannot do in 2 seconds.
 *
 * To do this faster (in time O(N + M) excluding the time to sort) we can use
 * a queue of team members, with the member that will be the first to be free
 * in front. A code using this approach follows this comment.
 *
 * == Why sorting the tasks by time is the best we can do? ==
 *
 * Send an email to df288@cornell.edu in case you are curious about this. :)
 */


// Bellow is the code using a queue, that is about 5000 times faster than the code
// using vectors for N = M = 10000.

// This is just another way of making a big block of comments. Change 0 to 1 so
// that your editor will color this code according to syntax (so that it's
// easier to read it)
#if 0
int main() {
    int N, M;
    scanf("%d %d", &N, &M);
   
    // equivalent to List<Integer> time_to_solve = new ArrayList<>(); in Java
    vector<int> time_to_solve;
    for (int i = 0; i < M; i++) {
        int temp;
        scanf("%d", &temp);
        // equivalent to time_to_solve.add(temp);
        time_to_solve.push_back(temp);
    }
    sort(time_to_solve.begin(), time_to_solve.end()); // sort it
    // The answer might be larger than what fits in an int.
    // This is equivalent to "long answer = 0;" in Java
    long long answer = 0;
    // Tthis queue maintains the time of when the next solver will be
    // available to solve a problem.
    queue<int> time_of_free_member;

    // In the beginning all N team members are free
    for (int i = 0; i < N; i++) {
        time_of_free_member.push(0);
    }

    for (int t : time_to_solve) {
        // get the next time a team member will be available
        int time_start = time_of_free_member.front();
        time_of_free_member.pop();
        int time_end = time_start + t;
        answer += time_end;
        // the solver that will solve the current problem will be available
        // again at time "time_end"
        //
        // Try to prove that this works. In other words, that time_end is not
        // smaller than any other time in the queue, so that its correct
        // position is at the end.
        time_of_free_member.push(time_end);
    }
    printf("%lld\n", answer);
    return 0;
}
#endif

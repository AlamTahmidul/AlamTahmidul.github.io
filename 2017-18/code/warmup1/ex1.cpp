#include <cstdio>
using namespace std;

/******************************************************************************
 * This problem can be solved by "simply" parsing the input and multiplying by
 * the corresponding number.
 *
 * For instance, if "x of unit A" is "y of unit B" then we know that:
 * 1) z of unit A == z*y/x of unit B
 * 2) w of unit B == w*x/y of unit A
 *****************************************************************************/

int main() {
    double x, y;
    scanf("%lf %lf", &x, &y);
    int N;
    scanf("%d", &N);
    for (int i = 0; i < N; i++) {
        double value;
        char unit;
        scanf("%lf %c", &value, &unit);
        if (unit == 'A') {
            printf("%.10f\n", value * y / x);
        } else { // unit == 'B'
            printf("%.10f\n", value * x / y);
        }
    }
    return 0;
}

#include <cstdio>
#include <cmath>
using namespace std;

/******************************************************************************
 * Let's do some algebra. :)
 *
 * V is at position (xv, yv) and S is at position (xs, ys).
 *
 * We want to find a position for Dudu at (x, 0) such his distance to S is
 * exactly twice as his distance to V. In formulas (by using the Pythagorean
 * theorem):
 *
 * sqrt( (xs - x)^2 + ys^2) = 2 * sqrt( (xv - x)^2 + yv^2)
 *
 * These square roots are not nice to work with, so let's square both sides of
 * the equation to get:
 *
 * (xs - x)^2 + ys^2 = 4 * ((xv - x)^2 + yv^2)
 *
 * By expanding the squares we get:
 *
 * xs^2 - 2 * xs * x + x^2 + ys^2 = 4 * xv^2 - 8 * xv * x + 4*x^2 + 4*yv^2
 *
 * By rearranging terms we get:
 *
 * 3x^2 + (2*xs - 8 * xv)x + (4 * xv^2 + 4 * yv^2 - xs^2 - ys^2) = 0
 *
 * This is in the form:
 * a*x^2 + b*x + c = 0
 *
 * So we can use the quadratic formula to solve for x
 *****************************************************************************/

int main() {
    double xv, yv, xs, ys;
    scanf("%lf %lf %lf %lf", &xv, &yv, &xs, &ys);
    // Build the coefficients of the quadratic formula:
    double A = 3;
    double B = 2*xs - 8 * xv;
    double C = 4*xv*xv + 4*yv*yv - xs*xs - ys*ys;

    // solve it:
    double delta = B*B - 4 * A * C; // if this is < 0 then there are no real solutions
    if (delta < 0) {
        printf("NO\n");
    } else {
        // the smallest of the two solutions is (-B - sqrt(delta)) / (2 * A)
        printf("%.10lf\n", (-B - sqrt(delta)) / (2 * A));
    }
    return 0;
}

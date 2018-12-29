#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <cs50.h>


int main(void)
{
    double xv = get_double();
    double yv = get_double();
    double xs = get_double();
    double ys = get_double();

    // create coefficients
    double A = 3;
    double B = 2*xs - 8 * xv;
    double C = 4*xv*xv + 4*yv*yv - xs*xs - ys*ys;

    // solve it
    double delta = B*B - 4 * A * C; // if this is < 0 then there are no real solutions
    if (delta < 0) {
        printf("NO\n");
    } else {
        // the smallest of the two solutions is (-B - sqrt(delta)) / (2 * A)
        printf("%.10lf\n", (-B - sqrt(delta)) / (2 * A));
    }
    return 0;
}
// Helper functions

#include <cs50.h>
#include <stdio.h>

#include "helpers.h"

// Returns true if value is in array of n values, else false
bool search(int value, int values[], int n)
{
    // Binary Search
    int start = 0;
    int end = n - 1;
    // Until the code finishes based on checks and last number
    while (end >= start)
    {
        // Midpoint
        int halfway_point = (end + start) / 2;
        // Find value in the midpoint
        if (values[halfway_point] == value)
        {
            return true;
        }
        // If value is to the left of the middle
        else if (values[halfway_point] < value)
        {
            start = halfway_point + 1;
        }
        // If value is to the right of the middle
        else
        {
            end = halfway_point - 1;
        }
    }
    // value not found
    return false;
}

// Sorts array with Counting Sort
void sort(int values[], int n)
{
    int i, min, max;

    min = max = values[0];
    for (i = 1; i < n; i++)
    {
        if (values[i] < min) {
            min = values[i];
        } else if (values[i] > max) {
            max = values[i];
        }
    }
    return;
}

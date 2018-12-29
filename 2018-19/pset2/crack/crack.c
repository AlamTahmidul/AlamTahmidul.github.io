#include <stdio.h>
#include <cs50.h>
#include <stdlib.h>
#include <string.h>

#define _XOPEN_SOURCE
#include <unistd.h>

void crack();

int main(void)
{
    crack();
}

void crack()
{
    string access = {
        "50xcIMJ0y.RXo"
        "50mjprEcqC/ts"
        "50GApilQSG3E2"
        "50n0AAUD.pL8g"
        "50CcfIk1QrPr6"
        "509nVI8B9VfuA"
        "50JIIyhDORqMU"
        "50JGnXUgaafgc"
        "51u8F0dkeDSbY"
        "50cI2vYkF0YU2"
    };

    string access_two = {
    "50xcIMJ0y.RXo"
    "50mjprEcqC/ts"
    "50GApilQSG3E2"
    "50n0AAUD.pL8g"
    "50CcfIk1QrPr6"
    "509nVI8B9VfuA"
    "50JIIyhDORqMU"
    "50JGnXUgaafgc"
    "51u8F0dkeDSbY"
    "50cI2vYkF0YU2"
    };

    int rtrcmp;
    for (int i = 0, ac_len = strlen(access); i < ac_len; i++) {
        if (access[i] == access_two[i]) {
            printf("%c", access[i]);
        }
    }
}
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <cs50.h>

char temp[1000];

string make_template(string letters)
{
    int length = strlen(letters);

    for (int i = 0; i < length; i++)
    {
        temp[i] = '.';
    }

    temp[length] = '\0';

    char next_letter = 'A';
    for (int i = 0; i < length; i++)
    {
        if (temp[i] == '.')
        {
            temp[i] = next_letter;

            for (int j = i + 1; j < length; j++)
            {
                if (letters[i] == letters[j])
                {
                    temp[j] = next_letter;
                }
            }
            next_letter++;
        }
    }
    for (int i = 0; i < length; i++)
    {
        letters[i] = temp[i];
    }
    return letters;
}


int main(void) {
    int n = get_int();
    string s = get_string();
    s = make_template(s);

    // printf("%s\n", s);

    string names[n];

    for (int i = 0; i < n; i++)
    {
        names[i] = get_string();
        names[i] = make_template(names[i]);

        // printf("%s\n", names[i]);
    }

    // printf("s is now: %s\n", s);

    bool match;

    for (int i = 0; i < n; i++)
    {
        match = false;
        char sub[strlen(names[i])+ 1];
        for (int start = 0; start < strlen(s) - strlen(names[i]) + 1; start++)
        {
            // make substring
            for (int z = 0; z < strlen(names[i]); z++)
            {
                sub[z] = s[start + z];
            }
            sub[strlen(names[i])] = '\0';
            string sub_template = make_template(sub);
            if (!strcmp(sub_template, names[i]))
            {
                printf("%i\n", start);
                match = true;
                break;
            }

        }
        if (!match)
        {
            printf("-\n");
        }
    }

    return 0;
}
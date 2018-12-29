#include <iostream>
#include <cctype> // tolower is here
#include <string>
#include <vector>
using namespace std;

/******************************************************************************
 * Consider a string S consisting. Let's consider the "representative" of S as
 * being the string where the first unique element is translated to 'A', the
 * second unique element is translated to 'B', etc...
 *
 * For instance, the representative of "XYXXQ" is "ABAAC", because "X" is the
 * first element to ever appear in the string, then "Y", then "Q", therefore we
 * transform "X" into "A", "Y" into "B" and "Q" into "C".
 *
 * Another example: the representativa of "blob" is "ABCA" (note that the
 * representative is always done with upper case letters).
 *
 * Then it is clear that a station name st can be in position i of the text T if
 * and only if the representative of st is the same as the representative of
 * T[i, i+1, ..., i + st.size() - 1]. The code below implements this idea.
 *
 * This code runs in time O(|s| * (|st1| + |st2| + ... + |stN|)) where |s| is
 * the size of the text Dudu has, and |sti| is the size of the name of the i-th
 * station. This implies 1M operations in total, which is fast enough.
 *****************************************************************************/

// Assumes "input" consists of only lower case letters
string representative(const string& input) {
    // Computes the representative of the input string.
    // There are 26 possible values for a char, and we maintain to what other
    // char it should be converted to. '.' means "we don't know how to convert
    // yet".
    vector<char> translate(26, '.');
    char next_free_letter = 'A';

    string output;
    for (char c : input) {
        int index = c - 'a'; // transforms 'a' into 0, 'b' into 1, etc
        if (translate[index] == '.') {
            translate[index] = next_free_letter;
            next_free_letter++;
        }
        output += translate[index];
    }
    return output;
}

int main() {
    int N;
    string text;
    cin >> N >> text;
    // transform text into lower case characters, so that taking representatives
    // is possible.
    for (char &c : text) c = tolower(c);

    for (int i = 0; i < N; i++) {
        string station_name;
        cin >> station_name;
        string station_name_rep = representative(station_name);
        // try all substrings of length station_name.size() of text
        bool found = false;
        for (int start = 0; start + station_name.size() <= text.size(); start++) {
            if (representative(text.substr(start, station_name.size())) == station_name_rep) {
                // Found a subtext with exactly the same representative!
                cout << start << "\n";
                found = true;
                break; // it's breaking the "for start" loop, not the "for i" loop
            }
        }
        if (!found) {
            // in case we couldn't find the station name in the text we should
            // output a dash.
            cout << "-\n";
        }
    }
    return 0;
}

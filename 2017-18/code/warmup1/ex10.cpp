#include <iostream>
#include <string>
#include <cctype> // for isalpha and tolower
#include <unordered_set>
using namespace std;

/******************************************************************************
 * This problem doesn't have any "tricks", it's just hard to implement
 * because of the number of rules.
 *
 * One thing that helps in the implementation is realizing that the rules should
 * be apply in each word independently, regardless of line breaks, so we can
 * read on word at a time, transform it, and output the correct answer.
 *
 * In C++ this can be done by reading using cin, in Java by using a Scanner
 * and calling ".hasNext()" and ".next()".
 *****************************************************************************/

unordered_set<string> of_words = {"of", "to", "into", "onto", "above", "below", "from", "by", "is", "at"};

bool is_vowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
}

int main() {
    string word;
    int cur_line_length = 0;
    cin >> word; // ignore the number of lines
    while (cin >> word) {
        // rule 1:
        for (char &c : word) c = tolower(c);
        // rule 2:
        if (of_words.find(word) != of_words.end())
            word = "of";
        // rule 3:
        int K = 0;
        for (char c : word)
            if (is_vowel(c))
                K++;
        int to_remove = K / 2;
        string temp_word;
        for (char c : word) {
            if (is_vowel(c)) {
                if (to_remove == 0) // already removed all vowels
                    temp_word += c;
                else
                    to_remove--;
            } else { // nonvowels always "survive"
                temp_word += c;
            }
        }
        // copy back to word:
        word = temp_word;

        // rule 4:
        temp_word = "";
        for (char c : word)
            if (isalpha(c))
                temp_word += c;
        word = temp_word;

        // rule 5 is done by default, by considering one word at a time
        // rule 6 is done by default, by considering one word at a time
        // if the word completely disappeared (for instance, if it doesn't have
        // any letters) we stop here.
        if (word.size() == 0) continue;

        // print the word
        if (cur_line_length != 0) cout << " "; // no leading spaces
        cout << word;

        // rule 7:
        cur_line_length += word.size();
        if (cur_line_length > 20) {
            cout << "\n";
            cur_line_length = 0;
        }
    }
    return 0;
}

#include <iostream>
#include <string>
using namespace std;

/******************************************************************************
 * The number of possible songs that are like Anjunabeats song is:
 *
 * size(song)! / (number_of_A(song)! * number_of_B(song)! * ... *
 * number_of_Z(song)!)
 *
 * So the number of Anjunabeats but wrong is that number minus 1.
 *****************************************************************************/

int main() {
    long long factorial[21]; // precompute all factorials from 0 to 20
    factorial[0] = 1; // 0! = 1
    for (int i = 1; i < 21; i++)
        factorial[i] = i * factorial[i - 1]; // i! = i*(i-1)!

    // read the song
    string song;
    cin >> song;

    long long answer = factorial[song.size()];
    for (char cur_char = 'A'; cur_char <= 'Z'; cur_char++) {
        int count = 0; // how many times cur_char appears in the song
        for (char beat : song) {
            if (beat == cur_char) count++;
        }
        answer /= factorial[count];
    }
    printf("%lld\n", answer - 1);
    return 0;
}

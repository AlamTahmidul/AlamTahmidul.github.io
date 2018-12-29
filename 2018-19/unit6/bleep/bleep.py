'''
    Tahmidul Alam
    CS50AP
    bleep.py
    Bleep
'''
from cs50 import get_string
import sys


def main():
    # Check for the number of command line inputs
    if len(sys.argv) != 2:
        print("Usage: python bleep.py banned.txt")
        exit(1)

    # Get User Input
    print("Text: ", end="")
    text = get_string()

    # Make user input split into a list
    spl = text.split(" ")

    # The banned words are placed in a set list
    ban_txt = set()

    # The banned words are in the CLI of the input
    file = open(sys.argv[1])

    # Add the banned words into the set
    for line in file:
        ban_txt.add(line.strip())

    # Counter for printing *s as bleep
    c = 0

    # Finding banned words
    for elem in spl:
        # Checking if any of the items in the list is in banned words
        if elem.lower() in ban_txt:
            # Get the length of the banned words
            length = len(elem)
            # Remove the banned word
            spl.remove(elem)
            # Replace the banned word(s) with *s
            spl.insert(c, '*' * length)
        # Increase counter
        c += 1

    # Finally, join up the list for the user input and print the output
    print(" ".join(spl))


# Checking for any unnecessary errors
if __name__ == "__main__":
    main()
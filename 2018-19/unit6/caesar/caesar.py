'''
    Tahmidul Alam
    CS50AP
    Caesar
    caesar.py
'''

import sys
import cs50


def caesar(inp, cipher_code):
    # Print out the pre-text
    print("ciphertext: ", end="")

    # Counter for iteration
    c = 0
    # For each character in string inp
    for i in inp:
        # Check lowercase
        if inp[c].islower():
            # Encryption formula (Put the math in numbers)
            lower_characters_num = (((ord(inp[c]) + cipher_code) - ord('a')) % 26) + ord('a')
            # Encryption formula generated (the result is an ASCII code; put it in the corresponding character)
            lower_characters = chr(lower_characters_num)
            print("{}".format(lower_characters), end="")
        # Check uppercase
        elif inp[c].isupper():
            # Encryption formula (Put the math in numbers)
            upper_characters_num = (((ord(inp[c]) + cipher_code) - ord('A')) % 26) + ord('A')
            # Encryption formula generated (the result is an ASCII code; put it in the corresponding character)
            upper_characters = chr(upper_characters_num)
            # Print out the character
            print("{}".format(upper_characters), end="")
        else:
            # The text may have spaces or punctuations
            print(inp[c], end="")
        # Counter increase to access the next index
        c += 1
    # Print a new line
    print()


def main():
    # Number of command line inputs
    # Only 2 is permitted
    if len(sys.argv) != 2:
        print("Usage: python caesar.py 13")
        exit(1)

    # Assuming that CLI passed, get the cipher code that we want to encrypt
    cipher_code = int(sys.argv[1])

    # The cipher code must be positive
    if (cipher_code < 0):
        print("Usage: python caesar.py 13")
        exit(1)
    # Cipher code test pass
    else:
        # Pre-text
        print("Enter Text: ", end="")
        # Ask the user for the text they want to encrypt
        input_code = cs50.get_string()
        # Launch the encryption
        caesar(input_code, cipher_code)


# Check for any unnecessary errors
if __name__ == "__main__":
    main()
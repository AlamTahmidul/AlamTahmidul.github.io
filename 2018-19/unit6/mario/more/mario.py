'''
    Tahmidul Alam
    CS50AP
    Mario (more)
    mario.py
'''

from cs50 import get_int


def main():
    # User validation
    while True:
        print("Height: ", end="")
        height = get_int()
        if height >= 0 and height <= 23:
            break

    # Standard mario.py Start
    for i in range(height):
        for j in range(height - i - 1):
            print(" ", end="")
        for k in range(i + 1):
            print("#", end="")
    # Standard mario.py End
    # Start the right pyramid (put a space after the first and
    # continue)
        print("  ", end="")
    # Finally, print the right pyramid along with the left pyramid
        for m in range(i + 1):
            print("#", end="")
    # Print a new line
        print()


# Main Function
if __name__ == "__main__":
    main()
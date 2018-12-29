'''
    Tahmidul Alam
    CS50AP
    Mario (less)
    mario.py
'''


import cs50


def main():
    # User Validation
    while True:
        print("Height: ", end="")
        height = cs50.get_int()
        if height >= 0 and height <= 23:
            break
    # Printing Out the Pyramid
    for i in range(height):
        # Printing out the spaces
        for j in range(height - i - 1):
            print(" ", end="")
        # Finally printing out the pyramid
        for k in range(i + 2):
            print("#", end="")
        # New line
        print("")


# Check for any unnecessary errors
if __name__ == "__main__":
    main()
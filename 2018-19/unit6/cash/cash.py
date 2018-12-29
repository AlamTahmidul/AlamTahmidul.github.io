'''
    Tahmidul Alam
    CS50AP
    Cash
    cash.py
'''

from cs50 import get_float

QTR = 25
DIM = 10
NIC = 5


def main():
    # Validate user input
    while True:
        print("Change: ", end="")
        user_input = round(get_float() * 100)
        if user_input >= 0:
            break
    # Declare a variable for counting coins
    coins = 0

    # Get the whole number of quarters from the change
    quarters = user_input // QTR

    # Find the remaining value after quarters
    user_input %= QTR

    # Get the whole number of dimes from the remaining change
    dimes = user_input // DIM

    # Find the remaining value after dimes
    user_input %= DIM

    # Get the whole number of nickels from the remaining change
    nickels = user_input // NIC

    # The rest of the value must be pennies
    user_input %= NIC

    # Add up the amount of coins
    coins = user_input + quarters + nickels + dimes

    print(coins)


if __name__ == "__main__":
    main()
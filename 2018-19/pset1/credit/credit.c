/**
 * credit.c
 * Based on Luhn's Algorithm
 *
 * PSET1
 * CS50AP
 *
 * Tahmidul Alam
 *
*/
#include <cs50.h>
#include <stdio.h>

// Telling code that there are functions defined after the main
void check(long long cardNum);
int cardLength(long long numLength);
bool checkLen(int crdLen);
bool checkSum(long long cardNum, int crdLen);
void cardType(long long cardNum);

// Main function
int main(void)
{
    // Get User Input for Bank Account Number (accountNum)
    long long accountNum;
    do
    {
        accountNum = get_long_long("Account Number: ");
    }
    while (accountNum < 0);

    // Running validity tests
    check(accountNum);
}

// Validate accountNum
void check(long long cardNum)
{
    // Checking for Card Length (returning a value)
    int crdLen = cardLength(cardNum);
    // Checking Condition: Card Length and Checksum must match up
    // Note: The checkLen(crdLen) should determine if the digits are either 13, 15, or 16
    if (!(checkLen(crdLen) && checkSum(cardNum, crdLen)))
    {
        printf("INVALID\n");
        return;
    }
    // Return the Type of Card (Visa, AMEX, or MasterCard)
    cardType(cardNum);
}

// Test 1: Credit Card Length (Find Length)
int cardLength(long long numLength)
{
    // Measuring how many digits there are
    int len = 0;
    // Getting how many digits there are
    while (numLength > 0)
    {
        len++;
        // Iterating through the numbers
        numLength /= 10;
    }
    // Return an int
    return len;
}

// Test 2: Credit Card Length (Validity: 13, 15, or 16)
bool checkLen(int crdLen)
{
    // Checking if there are 13, 15 or 16 digits in total
    if (crdLen == 13 || crdLen == 15 || crdLen == 16)
    {
        return true;
    }
    // Default: False
    return false;
}

// Test 3: Checksum (Luhn's algorithm / (T/F) )
bool checkSum(long long cardNum, int crdLen)
{
    int sum = 0;
    // Iterating through cardNum length
    for (int i = 0; i < crdLen; i++, cardNum /= 10)
    {
        // Condition 1: Even Digits Check
        if (i % 2 == 0)
        {
            sum += (cardNum % 10);
        }
        // Condition 2: The remaining digits check
        else
        {
            int digit = (2 * (cardNum % 10));
            sum += (digit / 10) + (digit % 10);
        }
    }
    // Return true if the sum matches with the end of digit to be a 0
    if (sum % 10 == 0)
    {
        return true;
    }
    // Return False: default
    return false;
}
// Test 4: Matching the Card to it's brand
void cardType(long long cardNum)
{
    // American Express: 34 or 37 with 15 digits in total
    if ((cardNum >= 340000000000000 && cardNum < 350000000000000) || (cardNum >= 370000000000000 && cardNum < 380000000000000))
    {
        printf("AMEX\n");
    }
    // MasterCard: 51-55 with 16 digits in total
    else if (cardNum >= 5100000000000000 && cardNum < 5600000000000000)
    {
        printf("MASTERCARD\n");
    }
    // Visa: 4 with 13 or 16 digits in total
    else if ((cardNum >= 40000000000000 && cardNum < 50000000000000) || (cardNum >= 4000000000000000 && cardNum < 5000000000000000))
    {
        printf("VISA\n");
    }
    // The card number is probably not valid
    else
    {
        printf("INVALID\n");
    }
}
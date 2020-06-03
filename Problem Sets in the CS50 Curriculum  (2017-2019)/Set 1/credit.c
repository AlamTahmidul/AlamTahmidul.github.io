// Based on Luhn's Algorithm

#include <stdio.h>
#include <math.h>
#include <stdbool.h>

bool cardLenValid(long long cardNum) {
  int counter = 0;
  do {
    cardNum /= 10;
    counter++;
  } while (cardNum > 0);
  return (counter == 13 || counter == 15 || counter == 16);
}

int cardLen(long long cardNum) {
  int counter = 0;
  do {
    cardNum /= 10;
    counter++;
  } while(cardNum > 0);
  // printf("Counter: %d", counter);
  return counter;
}

bool checkSum(long long cardNum) {
    int sum = 0;
    int len = cardLen(cardNum);

    for (int i = 0; i < len; i++, cardNum /= 10)
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
    // printf("sum: %d\n", sum);
    return (sum % 10 == 0);
}

void printCard(long long cardNum) {
  // Visa Check: Starts with 4 and has exactly 13 or 16 digits total
  if ((cardNum >= 40000000000000 && cardNum < 50000000000000) || 
      (cardNum >= 4000000000000000 && cardNum < 5000000000000000) ) {
        printf("Visa\n");
  } else if ( (cardNum >= 340000000000000 && cardNum < 350000000000000) || (cardNum >= 370000000000000 && cardNum < 380000000000000) ) { 
    // American Express check: Starts with 34 or 37 and has exactly 15 digits total
    printf("American Express\n");
  } else if (cardNum >= 5100000000000000 && cardNum < 5600000000000000) {
    // MasterCard check: Starts with a number between 51-55 inclusive and has exactly 16 digits total
    printf("MasterCard\n");
  } else {
    printf("Other Card\n");
  }
}

void check(long long cardNum) {
  bool lenVal = cardLenValid(cardNum);
  bool sumCheck = checkSum(cardNum);

  if (lenVal && sumCheck) {
    printf("Valid Card!\n");
    printCard(cardNum);
  } else {
    printf("Invalid Card!\n");
  }
  return;
}

int main()
{
  long long cardNum;

  printf("Enter Card Number: ");
  scanf("%lld", &cardNum);
  
  printf("Your card number is: %lld\n", cardNum);

  check(cardNum);
  return 0;
}
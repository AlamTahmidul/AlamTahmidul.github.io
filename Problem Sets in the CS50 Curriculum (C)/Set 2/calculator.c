#include <stdio.h>
#include <string.h>

void push();
void pop();
void peek();

int main(int argc, char const *argv[])
{
    char c[50];
    printf("Enter equation: ");
    fgets(c, sizeof(c), stdin);

    for (int i = 0; i < strlen(c); i++) {
      printf("Result: %c\n", c[i]);
    }
    return 0;
}

void push() {
  
}

void pop() {

}

void peek() {

}
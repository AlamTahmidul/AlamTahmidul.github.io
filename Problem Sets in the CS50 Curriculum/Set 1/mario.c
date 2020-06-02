#include <stdio.h>
#include <math.h>

int main(int argc, char const *argv[])
{
    int height;
    
    do {
        printf("Height: ");
        scanf("%d", &height);
        if (height < 1) {
            printf("\nInvalid Height. Place a height > 0!\n");
        }
    }  while (height < 1);
    
    for (int i = 0; i < height; i++) {
        // Left Pyramid
        for (int space = i; space < height; space++) {
            printf(" ");
        }
        for (int blocks = 0; blocks < i + 1; blocks++) {
            printf("#");
        }

        printf(" ");

        // Right Pyramid
        for (int blocks = 0; blocks < i + 1; blocks++) {
            printf("#");
        }
        printf("\n");
    }

    return 0;
}

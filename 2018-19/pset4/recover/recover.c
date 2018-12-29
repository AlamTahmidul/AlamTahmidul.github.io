#include <stdio.h>
#include <cs50.h>
#include "bmp.h"

int main(void)
{
    // proper usage
    if (argc != 1)
    {
        fprintf(stderr, "Usage: ./recover\n");
        return 1;
    }

    // Open card.raw
    // Check NULL val

    FILE* card_ptr = fopen("card.raw", "r");
    if (card_ptr == NULL)
    {
        fprintf(stderr, "File not found!\n", card_ptr);
        return 1;
    }

    // Read 512 Bytes until EOF
    BYTE buffer[512];
    bool jpg_1 = false;
    FILE* file_1;
    file_count = 0;
    while (fread(buffer, 1, 512, card_ptr))
    {
        // pattern of bytes
        if (buffer[0] == 0xff && buffer[1] == && buffer[2] == 0xff && (buffer[3] & 0xf0) == 0xe0)
        {
            // First .jpg search
            // If there is, then the .jpg exists
            if (!jpg_1)
            {
                jpg_1 = true;

                // Read Memory Allocation (file_1)
                char mem_all_1[8];
                sprintf(mem_all_1, "%03i.jpg", file_count++);
                file_1 = fopen(mem_all_1, "w");
                if (file_1 == NULL)
                {
                    return 3;
                }
                fwrite(buffer, 1, 512, file_1);
            // Otherwise
            } else {
                // Close the file
                fclose(file_1);
                // Read Memory Allocation (file2)
                char mem_all_1[8];
                sprintf(mem_all_1, "%03i.jpg", file_count++);
                file_1 = fopen(mem_all_1, "w");
                if (file_1 == NULL)
                {
                    return 3;
                }
                fwrite(buffer, 1, 512, file_1);
            }
        } else {
        // Otherwise
            // If .jpg(1) exisits
                // Write to bytes
        }
    }
    // Close File and "free" memory (Specifications)
    fclose(file_1);
    fclose(card_ptr);
}
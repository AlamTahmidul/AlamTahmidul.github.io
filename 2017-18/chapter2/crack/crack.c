#define _XOPEN_SOURCE
#include <unistd.h>
char *crypt(const char *key, const char *salt);
#define _GNU_SOURCE
#include <crypt.h>
char *crypt_r(const char *key, const char *salt,
              struct crypt_data *data);
int main(int argc, string argv[])
{
    string salt = "50";
    string
}
#include <stdio.h>
#include <float.h>
#include <limits.h>
#include <stdarg.h>
#include <stdbool.h>
#include <stdlib.h>
#include <ctype.h>
#include <math.h>
#include <stdint.h>
#include <string.h>

typedef char *string;

string *__all_strings = NULL;
size_t __number_of_strings = 0;
size_t __capacity_of_strings = 0;

static void teardown(void) {
	if (__all_strings) {
		for (size_t i = 0; i < __number_of_strings; i++) {
			free(__all_strings[i]);
		}
		free(__all_strings);
	}
}

static void __run_at_start(void) __attribute__((constructor));
static void __run_at_start(void) {
#ifdef __RUNNING_LOCAL__
	setvbuf(stdout, NULL, _IONBF, 0);
#endif
	__all_strings = malloc(10 * sizeof(string));
	__capacity_of_strings = 10;
	atexit(teardown);
}

void __eprintf(const string file, int line, const string format, ...) {
	fprintf(stderr, "%s:%d: ", file, line);
	va_list ap;
	va_start(ap, format);
	vfprintf(stderr, format, ap);
	va_end(ap);
}

#define eprintf(...) __eprintf(__FILE__, __LINE__, __VA_ARGS__)

char buf[1000001]; // I guarantee that there won't be any line longer than 1000000 characters long in any input in the contest. This makes the code considerably easier.

string get_string(void) {
	scanf(" %[^\n]", buf);
	int L = strlen(buf);
	string answer = malloc(L + 1);
	strcpy(answer, buf);

	if (__number_of_strings == __capacity_of_strings) {
		// double the capacity for strings
		__capacity_of_strings *= 2;
		__all_strings = realloc(__all_strings, __capacity_of_strings * sizeof(string));
	}
	__all_strings[__number_of_strings++] = answer;

	return answer;
}

char get_char(void) {
	char c;
	scanf(" %c", &c);
	return c;
}

double get_double(void) {
	double d;
	scanf(" %lf", &d);
	return d;
}

float get_float(void) {
	float f;
	scanf(" %f", &f);
	return f;
}

int get_int(void) {
	int i;
	scanf(" %d", &i);
	return i;
}

long long get_long_long(void) {
	long long ll;
	scanf("%lld", &ll);
	return ll;
}
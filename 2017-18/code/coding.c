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

int cmpfunc (const void * a, const void * b) {
  return ( *(int*)a - *(int*)b);
}
int toScore(long long int f, long long int b){
   return ((80 * f) + (20 * b));
}


int main() {
  long long int N = get_long_long();
  long long int K = get_long_long();

  long long int f[N];
  long long int b[N];

  for(int i = 0; i < N; i++){
    f[i] = get_long_long();
    b[i] = get_long_long();
  }

  long long int score[N];
  long long int original[N];

  for(int i = 0; i < N; i++) {
    score[i] = toScore(f[i], b[i]);
    original[i] = score[i];
  }

  qsort(score, N, sizeof(long long int), cmpfunc);

  //for(int i = 0; i < N; i++) {
    //printf("%lld  %lld\n", score[i], original[i]);
  //

  long long int output[K];
  long long int outputs = 0;

  for (int i = N; i >= (N - K); i--){
    for (int j = 0; j < N; j++){
      if (score[i] == original[j]){
        output[outputs] = j + 1;
        outputs++;
        break;
      }
    }
  }

  qsort(output, K, sizeof(long long int), cmpfunc);


   for (int i = 0; i < K; i++){
     printf("%lld ", output[i]);
   }

}

/**
//
// int cmpfunc (const void * a, const void * b) {
//   return ( *(int*)a - *(int*)b);
// }
//
// int toScore(long long int f, long long int b){
//   return ((80 * f) + (20 * b));
//}


long long int N = get_long_long();
long long int K = get_long_long();

long long int f[N];
long long int b[N];

for(int i = 0; i < N; i++){
  f[i] = get_long_long();
  b[i] = get_long_long();
}

long long int score[N];
long long int original[N];

for(int i = 0; i < N; i++) {
  score[i] = toScore(f[i], b[i]);
  original[i] = score[i];
}

qsort(score, N, sizeof(long long int), cmpfunc);

//for(int i = 0; i < N; i++) {
  //printf("%lld", score[i]);
//}

int output[K];

 for (int i = K; i > 0; i--){
   for (int j = 0; j < N; j++){
     if (original[j] == score[i]){
       output[i] == j + 1;
       break;
     }
   }
 }
 qsort(output, K, sizeof(int), cmpfunc);

 for (int i = 0; i < K; i++){
   printf("%i ", output[i]);
 }
*/

/*
string N = get_string();

int len = strlen(N);
//int open [6] = {2001, 2001, 2001, 2001, 2001, 2001};
//int close [6] = {2001, 2001, 2001, 2001, 2001, 2001};
int conjugations = 0;
int tense = 0;
// 1 Yo 2 Tu 3 El/Ella 4 Nosotros 5 Vosotros 6 Ellos

bool inCombination = false;

for (int i = 0; i < len; i++){
  if (N[i] == '['){
    //open[conjugations] = i;
    inCombination = true;
    if (N[i+1] == 'y'){
      tense = 1;
    }
    else if (N[i+1] == 't'){
      tense = 2;
    }
    else if (N[i+1] == 'e' && (N[i+3] == '/')){
      tense = 3;
    }
    else if (N[i+1] == 'n'){
      tense = 4;
    }
    else if (N[i+1] == 'v'){
      tense = 5;
    }
    else if (N[i+1] == 'e'){
      tense = 6;
    }
  }
  else if (N[i] == ']'){
    //close[conjugations] = i;
    if (N[i - 5] == 'e' && N[i - 6] == ' '){
      switch(tense){
        case 1:
          printf("estoy");
          break;
        case 2:
          printf("estas");
          break;
        case 3:
          printf("esta");
          break;
        case 4:
          printf("estamos");
          break;
        case 5:
          printf("estais");
          break;
        case 6:
          printf("estan");
          break;
      }
    }
      else if (N[i - 3] == 's' && N[i - 4] == ' '){
        switch(tense){
          case 1:
            printf("soy");
            break;
          case 2:
            printf("eres");
            break;
          case 3:
            printf("es");
            break;
          case 4:
            printf("somos");
            break;
          case 5:
            printf("sois");
            break;
          case 6:
            printf("son");
            break;
        }
    }
    else {
      int space;
      for (int j = 3; j < 24; j++){
        if (N[i - j] == ' '){
          space = i - j;
          break;
        }
      }
      for (int j = space + 1; j < (i - 2); j++){
        printf("%c", N[j]);
      }
      switch(tense){
        case 1:
          printf("o");
          break;
        case 2:
          printf("as");
          break;
        case 3:
          printf("a");
          break;
        case 4:
          printf("amos");
          break;
        case 5:
          printf("ais");
          break;
        case 6:
          printf("an");
          break;
      }
    }
    inCombination = false;
  }
  else{
    if (!inCombination){
      printf("%c", N[i]);
    }
  }
}
printf("\n");
*/

// Dudu Want a Cookie
/*

*/

/*
string N = get_string();
long long int len = strlen(N);

long long int bIndex[len];
long long int bNumber = 0;
long long int uIndex[len];
long long int uNumber = 0;
long long int hIndex[len];
long long int hNumber = 0;

for (long long int i = 0; i < len; i++){
  if (N[i] == 'b'){
    bIndex[bNumber] = i;
    bNumber++;
  }
  else if (N[i] == 'u'){
    uIndex[uNumber] = i;
    uNumber++;
  }
  else if (N[i] == 'g'){
    hIndex[hNumber] = i;
    hNumber++;
  }
}
//printf("%lld", bNumber);
//printf("%lld", uNumber);
//printf("%lld", gNumber);

long long int sum = 0;

for (int i = (bNumber - 1); i >= 0 ; i--){
  if (bNumber == 0 || uNumber == 0 || hNumber == 0){
    break;
  }
  for (int j = (uNumber - 1); j >= 0; j--){
    for (int k = 0; k < hNumber; k++){
      if (bIndex[i] < uIndex[j] && uIndex[j] < hIndex[k]){
        sum += (hNumber - k);
        //printf("%lli\n", hNumber - k);
        break;
      }
    }
    if (bIndex[i] > uIndex[j]){
      break;
    }
  }

}
printf("%lld\n", sum);
*/

#include <stdio.h>
#include <string.h>

/*  Assignment 3
    Abdullah Karson
    Date: 03/07/2020*/

#define INPUT_LENGTH32 32
#define INPUT_LENGTH256 256

const char alphabet[] = {' ','A','B','C','D','E','F','G','H','I',
                   'J','K','L','M','N','O','P','Q','R',
                   'S','T','U','V','W','X','Y','Z','\0'};

const char *morse_code[] = {" ",".-", "-...", "-.-.", "-..", ".", "..-.",
                      "--.", "....", "..", ".---", "-.-", ".-..",
                      "--","-.", "---", ".--.", "--.-", ".-.",
                      "...", "-", "..-", "...-", ".--", "-..-",
                      "-.--", "--..","\0"};

void word_to_morse(char *usr_array){
    /*change user input to morse code*/
    for (int i = 0; i < strlen(usr_array); i++) {
        printf("%c ", usr_array[i]);
        /*finding the corresponding morse code*/
        for (int j = 0; j < strlen(alphabet); j++){
            /*retrieving the corresponding alphabet position*/
            if (usr_array[i] == alphabet[j]){
                printf("\t%s\n", morse_code[j]);
                break;
            }
        }
    }
}

void morse_to_word(char *coded_morse){
    char buffer[256];
    char *buf_ptr = buffer;

    /*change users morse code input to letters and complete words*/
    for (int i=0; i<strlen(coded_morse); i++) {
        *buf_ptr = coded_morse[i];

        /*take the morse code that is divided by space and append its position to the buffer*/
        if (coded_morse[i] == ' ') {
            *buf_ptr = '\0';
            buf_ptr = buffer;
            printf("%s", buffer);
            /*retrieving the corresponding morse code position*/
            for (int j = 0; j < sizeof(morse_code); j++) {
                if (strcmp(buffer, morse_code[j]) == 0) {
                    printf("\t%c\n", (alphabet[j]));
                    break;
                }
            }
        } else { buf_ptr++; }
    }
}

int main() {

    /* User is only allowed to enter 32 Characters, for simplicity sake
     *  - limit located in the define as (INPUT_LENGTH)*/
    char message[INPUT_LENGTH32];
    char coded_stream[INPUT_LENGTH256];

    printf("Enter the message: ");
    gets(message);
    printf("\n");

    word_to_morse(message);

    printf("\n------------------------\n");

    printf("Enter the coded stream:");
    gets(coded_stream);
    printf("\n");

    morse_to_word(coded_stream);

    return 0;
}
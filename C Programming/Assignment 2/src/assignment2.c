#include <stdio.h>
#include <string.h>

//Assignment 2
//Abdullah Karson
//Date: 02/10/2020

#define INPUT_LENGTH 32

int main() {

    /* User is only allowed to enter 32 Characters, for simplicity sake - limit located in the define as (INPUT_LENGTH)*/
    char user_array[INPUT_LENGTH];

    char alphabet[] = {' ','A','B','C','D','E','F','G','H','I',
                       'J','K','L','M','N','O','P','Q','R',
                       'S','T','U','V','W','X','Y','Z'};

    char *morse_code[] = {" ",".-", "-...", "-.-.", "-..", ".", "..-.",
                         "--.", "....", "..", ".---", "-.-", ".-..",
                         "--","-.", "---", ".--.", "--.-", ".-.",
                         "...", "-", "..-", "...-", ".--", "-..-",
                         "-.--", "--.."};

    printf("Enter the message: ");
    scanf("%[^\n]", user_array);

    //change user input to morse code
    for (int i = 0; i < strlen(user_array); i++) {
        printf("\n%c ",user_array[i]);
        //finding the corresponding
        for (int k = 0; k <strlen(alphabet); k++){
            //retrieving the corresponding alphabet position
            if (user_array[i] == alphabet[k]){
                printf("%s ", morse_code[k]);
                break;
            }
        }
    }

    return 0;
}

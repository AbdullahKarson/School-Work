"""
Student Name:   Abdullah Karson
Program Title:  Message Redaction
Description:    A program that removes all
                desired letters from any user-entered
                sentence or phrase.
"""

#Functions
    #User Input Fuction
def userPhrase():
    #Prograg Status
    program = True
    #Run Program if True
    while program is True:
        #Counter for redacted letters
        counter = 0
        Exit = "quit"
        Phrase = input("Type a phrase (or 'quit' to exit program): ")
        #if Phrase = quit, than Program stops
        if Phrase.lower() == Exit:
            program = False
        #else keep running
        else:
            redact = input("\nType a comma-seperated list of letters to redact: ")
            #put redact letters in a list and split at commas
            redactList = redact.split(",")
            print("\nRedacted Phrase: ",end="")
            for letter in Phrase.capitalize():
                for letter2 in redactList:
                    if letter == letter2:
                        counter += 1
                        letter = "_"
                print(letter,end="")
            print("\nNumbers of letters redacted: {0}\n".format(counter))
    return

#Welcoming Messege
print("\nWelcome to the Message Redaction Program!\n")

userPhrase()
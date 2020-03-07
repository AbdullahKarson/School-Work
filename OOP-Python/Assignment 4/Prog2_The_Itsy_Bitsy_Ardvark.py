"""
Student Name:   Abdullah Karson
Program Title:  The Itsy Bitsy Aardvark
Description:    a program that presents the
                user with a “Mad Libs” type game,
                where a random choice of words 
                are read from a file, then 
                interjected into a story read from 
                another file.
"""
import csv

def myFileFunction(fileName,accessMode):
    #exeptiont check for OSErrors
    try:
        #Opening the File
        myFile = open(fileName,accessMode)
    except OSError:
        #if error happens print error message
        print('File has not been Found!!!')
    return myFile

def csvToListFunction(p_File):
    cL = []
    fileContent = csv.reader(p_File)
    for i in fileContent:
        #appendiing the csv to a list
        cL.append(i)
    return cL

def storyFunction(p_File):
    #assigning the Story to storyText
    storyText = p_File.read()
    return storyText

def choiceFunction(cL):
    wordCheck = True
    word = []
    for row in cL:
        #the please choose a ... for loop
        print(f"\nPlease Choose {row[0]}:")
        for i in range(1,len(row)):
            #the content of that for loop
            print(f"{i}: {row[i]}")
        # check if user entered the correct value
        while wordCheck == True:
            num = input("Enter choice (1-5): ")
            if num.isnumeric() and int(num) <= 5 and int(num) >= 1:
                for i in range(1,len(row)):
                    if int(num) == i:
                        #append it to a list called "word"
                        word.append(row[i].upper())
                break
            else:
                #repeat
                continue
    return word

def endTextFunction(listWord,storyText):
    #the position that are going to change in a list
    position = ["_1_","_2_","_3_","_4_","_5_","_6_","_7_"]
    
    #the altered story will now be called newStory
    newStory = storyText

    for word in range(0,len(listWord)):
        for pos in range(0,len(position)):
            #if word position and storynumber position are equal then...
            if word == pos:
                #relpace the storynumber with the users choosen word
                newStory = newStory.replace(position[pos],listWord[word])
    #Print It. and now BattleShips :)
    print("\n" + newStory + "\n")
        




def main(): #<-- Don't change this line!
    #Write your code below. It must be indented!

    #the relative file location of Story and choice
    CFFN = "P2 - Aardvark\\the_choices_file.csv"
    SFFN = "P2 - Aardvark\\the_story_file.txt"

    #access mode
    aM = "r"

    #opening both Files
    choiceFile = myFileFunction(CFFN,aM)
    storyFile = myFileFunction(SFFN,aM)

    print("\nThe Itsy Bitsy Aardvark")

    #readint the file
    story = storyFunction(storyFile)

    #Turning the csv file to a 2d List
    choiceList = csvToListFunction(choiceFile)

    #Get the List of users Choices
    wordList = choiceFunction(choiceList)

    #ptints out the Complete text eith the correct values.
    endTextFunction(wordList,story)

    #closing both Story and choice files.
    choiceFile.close()
    storyFile.close()
    #Your code ends on the line above

#Do not change any of the code below!
if __name__ == "__main__":
    main()
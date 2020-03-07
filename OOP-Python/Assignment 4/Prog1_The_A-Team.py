"""
Student Name:   Abdullah Karson
Program Title:  The A-Team 
Description:    a program that reads the text
                from a provided text file into 
                a list, displays the text on-screen,
                makes some alterations to the text 
                and outputs the changed text to the
                screen, then saves the altered 
                text as a new file.
"""
"""
1. Import random
2. Opening Original A-Team File with error handling
3. Print out original text
4. Open altered File to append later the altered text
5. Check each line for amount of letters (if > 20 than .lower()) (if < 20 than .upper())
6. Use a random number (that is between the amount of lines in file) and change the line to a Empty string
7. Append the Altered Text to the Altered File
"""
#Imported random for random line altering
import random

def myFileFunction(fileName,accessMode):
    #exeptiont check for OSErrors
    try:
        #Opening the File
        myFile = open(fileName,accessMode)
    except OSError:
        #if error happens print error message
        print('File has not been Found!!!')
    return myFile

def originalTextFunction(myFile):
    #List for File Content
    aTeamList = []
    #readng first file line and striping line break 
    fileContent = myFile.readline().strip("\n")
    while fileContent:
        #appending file line 
        aTeamList.append(fileContent)
        #readng file line after first and striping line break
        fileContent = myFile.readline().strip("\n")
    for i in range(0,len(aTeamList),1):
        #Printing every Line
        print("Line {0}: {1}".format(i + 1,aTeamList[i]))
    return aTeamList

def ATLFunction(OGTL,fileName,accessMode):
    #exeptiont check for OSErrors
    try:
        #opens Altered File
        myFile = open(fileName,accessMode)
    except OSError:
        print('File has not been Found!!!')
    #Counter for Letter amount
    counter = 20
    #random number between 0-9 so 10 digits
    randomNumber = random.randint(0,9)
    for i in range(0,len(OGTL),1):
        if len(OGTL[i]) > counter:
            #change to lower case if under 20 Letters
            OGTL[i] = OGTL[i].lower()
        elif len(OGTL[i]) <= counter:
            #change to upper case if under 20 Letters
            OGTL[i] = OGTL[i].upper()
        #check if randint isn't line position
        if i != randomNumber:
            #Print Changed Text
            print("Line {0}: {1}".format(i + 1,OGTL[i]))
            #Writes in to Altered File
            myFile.write("Line {0}: {1}\n".format(i + 1,OGTL[i]))
        #if it is: Print an empty string.
        else:
            #Print Omited Text as Empty
            print("")
            #Writes in to Altered File
            myFile.write("\n")
    return myFile
            
        

def main(): #<-- Don't change this line!
    #Write your code below. It must be indented!
    # File Location of Original Text
    fNOG = 'P1 - TheATeam\\ateam_Original.txt'
    #Accress mode of OG File : Read
    aMOG = 'r'

    #File Location of Altered Text
    fNA = 'P1 - TheATeam\\ateam_Altered.txt'
    #Accress mode of A File : Write
    aMA = 'w'

    #Opening OG File in Function
    aTeamFile = myFileFunction(fNOG,aMOG)

    print("\nOriginal Text:")

    #Printing and adding file content to a list
    OGTextList = originalTextFunction(aTeamFile)

    print("\nAltered Text:")

    #Altered File, Changing to .lower and .upper depending on letter amount, writing new information to Altered File, Printing altered Text 
    alteredFile = ATLFunction(OGTextList,fNA,aMA)

    #Close both Files
    alteredFile.close()
    aTeamFile.close()

#Do not change any of the code below!
if __name__ == "__main__":
    main()
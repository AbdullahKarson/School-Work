"""
Student Name:   Abdullah Karson
Program Title:  BattleShip
Description:    A program that replicates the functionality
                of the provided sample application, 
                a simple version of the game Battleship.
"""
def myFileFunction(fileName,accessMode):
    #exeptiont check for OSErrors
    try:
        #Opening the File
        myFile = open(fileName,accessMode)
    except OSError:
        #if error happens print error message
        print('File has not been Found!!!')
    return myFile

def mapListFunction(myFile):
    #the amount of lines in the map.txt 
    lines = 10
    #map list duh...
    mapList = []
    #append to fileContent the first line without the \n splited by ","
    fileContent = myFile.readline().strip("\n").split(",")
    while lines != 0:
        #append that to maplist
        mapList.append(fileContent)
        #append to fileContent the next line without the \n splited by ","
        fileContent = myFile.readline().strip("\n").split(",")
        #line Countdown :/
        lines -= 1
    # print(mapList)
    return mapList

def UserInputFunction(answerMap,visibleMap,missil):
    #check te input for correct values
    inputCheck = True
    # # hits needed to win
    hitstoWin = 17
    #to use exit after the missies have reached 0
    import sys
    #Turning the letters to Integers
    letterInt = ["A",'B',"C","D","E","F","G","H","I","J"]
    #User missile location
    usrmissile = []

    #the missile amount printed out for the user
    if missil == 30:
        print(f"\nYou have {missil} missiles to fire to sink all five Ships!\n")
    elif missil <=29 and missil > 1:
        print(f"\nYou have {missil} missiles remaining.\n")
    elif missil == 1:
        print(f"\nYou have {missil} missil left, Good Luck!\n")
    elif missil == 0:
        print(f"you hit {userhits} of {hitstoWin}, but didn't sink all the ships.")
        print(f"\nYou have {missil} missiles.\nGAME OVER.\n")
        exit()
    
    #printing out the visible map to the User
    for List in range(0,len(visibleMap)):
        for values in range(0,len(visibleMap[List])):
            print(visibleMap[List][values],end=" ")
        print("")
    #the user Input
    while inputCheck == True:
        usrInput = input("\nChoose your target (Ex. A1): ").upper()
        if usrInput[0].isalpha() and usrInput[1].isnumeric():
            if len(usrInput) > 2:
                if int(usrInput[1: len(usrInput)]) <= 10:
                    break
                else:
                    print("wrong Input, try again!!!")
                    continue
            break
        else:
            print("wrong Input, try again!!!")
            continue
    #append users Input to the usrmissile location list
    for letterPos in range(0,len(letterInt)):
        if usrInput[0] == letterInt[letterPos]:
            usrmissile.append((letterPos))
    usrmissile.append((int(usrInput[1:3]) - 1))
    print(usrmissile)

    #result is if its a 1 or a 0
    result = int(answerMap[(usrmissile[1])][(usrmissile[0])])

    if result == 0:
        #print to the user that its a miss in the visible table
        visibleMap[(usrmissile[1] + 1)][(usrmissile[0] + 1)] = "O"
        print("Miss")
    elif result == 1:
        #print to the user that its a hit in the visible table
        visibleMap[(usrmissile[1] + 1)][(usrmissile[0] + 1)] = "X"
        print("HIT!!!!!")

    #NOT WORKING, Check how many hits the user has successfuly managed
    for List in range(0,len(visibleMap)):
        for values in range(0,len(visibleMap[List])):
            if visibleMap[List][values] == "X":
                userhits += 1
    
    if userhits == hitstoWin :
        print(f"You hit {userhits} of {hitstoWin}, which sank all the ships\nYou Won, Congratulations!")
        exit()

def main(): #<-- Don't change this line!
    #Write your code below. It must be indented!
    
    #Check if Game is still active
    gameFlag = True

    #missile at the end of the Game
    missiles = 0

    #Opening the relative path to map.txt
    mapName = "P3 - Battleship\map.txt"
    #Access Mode
    aM = 'r'
    #amount of hits user has done
    #Opening the File 
    mapFile = myFileFunction(mapName,aM)

    #appending the map to a List
    listMap = mapListFunction(mapFile)

    #The vivsible Table to the User
    newTable = [[' '," A","B","C","D","E","F","G","H","I","J"],["1 "," "," "," "," "," "," "," "," "," "," "],
                ["2 "," "," "," "," "," "," "," "," "," "," "],["3 "," "," "," "," "," "," "," "," "," "," "],["4 "," "," "," "," "," "," "," "," "," "," "],
                ["5 "," "," "," "," "," "," "," "," "," "," "],["6 "," "," "," "," "," "," "," "," "," "," "],["7 "," "," "," "," "," "," "," "," "," "," "],
                ["8 "," "," "," "," "," "," "," "," "," "," "],["9 "," "," "," "," "," "," "," "," "," "," "],["10"," "," "," "," "," "," "," "," "," "," "]]

    #Welcome message
    print("\nLet's play BattleShip!\n")

    #while game is active keep running
    while gameFlag == True:
        #the 30 missile that the user has are his turns
        for turns in range(30,missiles - 1,-1):
            #The hard Part :(
            UserInputFunction(listMap,newTable,turns)


    #Your code ends on the line above
    mapFile.close()
#Do not change any of the code below!
if __name__ == "__main__":
    main()
"""
Student Name:   Abdullah Karson
Program Title:  Girl Guide Cookie Sell-off
Description:    The organizers of the annual Girl Guide cookie
                sale event want to raise the stakes on the number
                of cookies sold and are offering cool prizes to 
                those guides who go above and beyond in their 
                sales efforts. The organizers want a program to 
                print the guide list and their prizes.
"""

def guideNames(guideAmount):
    #creating the guide List,which contains names and boxes Sold
    guideList = []

    #variable to check correct Input
    name = True
    boxes = True
    #running for statement for each guide
    for i in range(1,(guideAmount + 1),1):
        while name == True:
            guideName = input("\nEnter the name of guide #{0}: ".format(i))
            if guideName.isalpha():
                #adding guide to List
                guideList.append(guideName)
                break
            else:
                print("ERROR! Wrong Input!!!")
        while boxes == True:
            boxesSold = input("Enter the number of boxes sold by {0}: ".format(guideName))
            if boxesSold.isnumeric():
                #adding boxes Sold to list
                guideList.append(int(boxesSold))
                break
            else:
                print("ERROR! Wrong Input!!!")
    #returning guideLise
    return guideList

def averagrFunction(GCS):
    #total = length of the List
    total = 0
    for i in range(1,len(GCS),2):
        total += GCS[i]
    #average of the list using the number values
    average = total / (len(GCS) / 2)
    #average output
    print("The average number of boxes sold by each guide was {0:.1f}".format(average))
    #returning average for last output
    return average

def answer(GCS,average):
    #new list only with the number values
    cookieBoxList = []

    print("Guides     " + "     Prizes Won:")
    print("--------------------------------")
    #appending the number values to the cookieBoxList
    for i in range(1,len(GCS),2):
        cookieBoxList.append(GCS[i])
    #for loop for names and cookieBoxList for max Value
    for i in range(1,len(GCS),2):
        if GCS[i] == max(cookieBoxList):
            print("{0}\t\t-Trip to Girl Guide Jamboree in Aruba!".format(GCS[i - 1]))
        elif GCS[i] > average and GCS[i] != max(cookieBoxList):
            print("{0}\t\t-Super Seller Badge".format(GCS[i - 1]))
        elif GCS[i] <= average and GCS[i] != max(cookieBoxList) and GCS[i] > 0:
            print("{0}\t\t-Left Over Cookies".format(GCS[i - 1]))
        elif GCS[i] == 0:
            print("{0}\t\t-".format(GCS[i - 1]))
    return

def main():
    #Welcoming message to the User
    print("welcome to the Girl Guide Cookie Sell-off!\n")

    #variable to check correct Input
    program = True

    #check if guides is numeric | else let user enter again
    while program == True:
        guides = input("Enter the number of guides selling cookies: ")
        if guides.isnumeric():
            break
        else:
            #error message output
            print("ERROR! Wrong Input!!!")

    #function call for all guide names and cookie boxes sold
    guidesCookiesSold = guideNames(int(guides))

    #function call for average calculation
    ave =  averagrFunction(guidesCookiesSold)

    #End function call for last Output
    answer(guidesCookiesSold,ave)

if __name__ == "__main__":
    main()
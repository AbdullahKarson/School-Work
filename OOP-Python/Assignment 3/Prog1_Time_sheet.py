#Don't forget to rename this file after copying the template
#for a new program!
"""
Student Name:   Abdullah Karson
Program Title:  Time Sheet
Description:    A program that accepts the number
                of hours worked on each of five work days from the
                user, then displays different information calculated
                about those entries as output.
"""

#functions
    #Worker Hour Funftion
def workedHoursFunction():
    #List of worked Hours
    p_workedHourList = []

    #variable to check correct Input
    program = True

    #number of Days
    days = [1,2,3,4,5]

    #for loop for all Days
    for i in days:
        #while loop to check correct Input
        while program == True:
            p_workedHours = input("Enter hours worked on Day #{0}: ".format(i))
            if p_workedHours.isnumeric():
                p_workedHourList.append(int(p_workedHours))
                break
            else:
                print("ERROR! Wrong Input!!!")
    return p_workedHourList

    #Max Hours Worked
def maxHourFunction(WHL):
    for i in range(0,len(WHL),1):
        if WHL[i] == max(WHL):
            #What day he worked most
            maxIndex = i + 1
            #Max Hours worked
            maxHours = max(WHL)
            print("The most hours worked was on: \nDay #{0} when you worked {1:.0f} hours.".format(maxIndex,maxHours))

    #Average Hours Worked
def aveHourFunction(WHL):
    #sum of all hours combined
    totalHourseWorked = sum(WHL)
    #average of WHL
    averagehoursWorked = totalHourseWorked / len(WHL)
    print("The total number of hours worked was: {0:.0f} \nThe average number of hours worked each day was {1:.1f}".format(totalHourseWorked,averagehoursWorked))

    #Hours slacked off :)
def slackHourFunction(WHL):
    #if worked less than 7 Hours than you slacked off
    slacktime = 7
    for i in WHL:
        if i < slacktime:
            slackIndex = (WHL.index(i)) + 1
            print("Day #{0}: {1} hours".format(slackIndex,i))
    return
    
#Welcome message to the user
print("\nWelcome to the Time Sheet Calculator!")
print("--------------------------------")


#calling work hour Function
workedHourList = workedHoursFunction()
print("--------------------------------")

#calling the max hour Function
maxHourFunction(workedHourList)
print("--------------------------------")

#calling the average Function
aveHourFunction(workedHourList)
print("--------------------------------")

#calling the slack Function
print("Days you slacked off (i.e. worked less than 7 hours):")
slackHourFunction(workedHourList)
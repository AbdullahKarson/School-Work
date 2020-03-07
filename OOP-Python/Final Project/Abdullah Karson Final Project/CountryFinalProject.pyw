import sys

from PyQt5.QtWidgets import QApplication, QMainWindow, QMessageBox

from PyQt5.QtGui import QPixmap
#ADD IMPORT STATEMENT FOR YOUR GENERATED UI.PY FILE HERE
import Ui_FlagDesign
#      ^^^^^^^^^^^ Change this!

#CHANGE THE SECOND PARAMETER (Ui_ChangeMe) TO MATCH YOUR GENERATED UI.PY FILE
class MyForm(QMainWindow, Ui_FlagDesign.Ui_MainWindow):
#                         ^^^^^^^^^^^   Change this!
    #Declare a 2d global list
    countryListInMemory = []

    #As long as no save to file friggered unsaved_changes = False
    unsaved_changes = False
    # DO NOT MODIFY THIS CODE
    def __init__(self, parent=None):
        super(MyForm, self).__init__(parent)
        self.setupUi(self)
    # END DO NOT MODIFY

        # ADD SLOTS HERE
        #Load the Country List
        self.actionLoad_Countries.triggered.connect(self.LoadMenuTriggered)
        #Displays the current Country Information
        self.CountryList.currentRowChanged.connect(self.DisplayCountryData)
        #Update the memory when clicking the Update Country Buttom
        self.UPButtom.clicked.connect(self.updateCountryPop)
        #Save countryListInMemory in file
        self.actionSave_To_File.triggered.connect(self.save_action_clicked)
        #exit trigger props user popup and exists
        self.actionExit.triggered.connect(self.exit_application)
        #let the radio button jump back up to Miles when country changed
        self.RBKM.clicked.connect(self.RBKMFunc)
        #
        self.RBMiles.clicked.connect(self.PopulationDensityFunction)
        #
        self.DDMilesKM.currentIndexChanged.connect(self.DDMilesKMFunc)
        #Hides everything exept the list and list Title
        self.mainFrame.hide()

    # ADD SLOT FUNCTIONS HERE
    def LoadMenuTriggered(self):
        #load from file the Countries
        self.LoadCountriesFromFile()
        #Puts the countriest into the List
        self.LoadCountriesListBox()

    def DisplayCountryData(self, newIndex):
        #ComboBox Index gets retrieved
        comboVar = self.DDMilesKM.currentIndex()
        #current country Name
        countryName = self.countryListInMemory[newIndex][0]
        #current country population
        countryPopulation = int(self.countryListInMemory[newIndex][1])
        #current country size
        countrySize = float(self.countryListInMemory[newIndex][2])
        #show the Country flag of the current selected country
        self.LoadCountryFlag(countryName)
        #show the Country Name of the current selected country
        self.FlagName.setText(countryName)
        #show the current selected country's percentage of the worlds population
        self.PercentWPFunction(countryPopulation)
        #show the Country density of the current selected country
        self.PopulationDensityFunction()
        #show the Country population of the current selected country
        self.Popamount.setText(f"{countryPopulation:,}")
        #Set the Miles Radio Button as Checked
        self.RBMiles.setChecked(True)
        # show the Country size of the current selected country depending on the seleced area type(sq.Miles or sq.KM)
        if comboVar == 0:
            self.DDMilesFunction()
        elif comboVar == 1:
            self.DDKMFunction()
        #show what was hidden
        self.mainFrame.show()

    #set the population density
    def setKM(self , popDensKM):
        self.PopulationDensity.setText(f"{popDensKM:.2f}")

    #Drop down function to set country size in km or miles
    def DDMilesKMFunc(self):
        comboVar = self.DDMilesKM.currentIndex()

        if comboVar == 0:
            self.DDMilesFunction()
        elif comboVar == 1:
            self.DDKMFunction()


    #ADD HELPER FUNCTIONS HERE
    def LoadCountriesListBox(self):
        #clears the country list if there is anything inside it
        self.CountryList.clear()
        self.actionLoad_Countries.setEnabled(False)
        #adds the countries to the list widgets
        for country in self.countryListInMemory:
            self.CountryList.addItem(country[0])
    
    def LoadCountriesFromFile(self):
        #file location (change if in different location) & access mode
        fileName = "Basic_CountriesOfTheWorld\countries.txt"
        accessMode = "r"

        #opening the file
        myFile = open(fileName, accessMode)
        
        #creating the countryListInMemory List
        self.countryListInMemory = []    #Clear list before loading from file

        #appending the information from country.txt to the countryListInMemory list 
        for lines in myFile:
            fileContent = lines.strip("\n").split(",")
            self.countryListInMemory.append(fileContent)
        myFile.close()

    def LoadCountryFlag(self , countryName):
        #changeing the space to a (_) to find the coresponding flag
        FlagName = countryName.replace(" " , "_")

        #variable image gets the image value
        image = QPixmap(f"Basic_CountriesOfTheWorld\Flags\\{FlagName}.png")
        
        #FlagImage label displays the image
        self.FlagImage.setPixmap(image)

    def PopulationDensityFunction(self):
        #the selected contry position
        selected_index = self.CountryList.currentRow()
        #the corresponding population
        countryPopulation = self.countryListInMemory[selected_index][1]
        #the corresponding Country Size
        countrySize = self.countryListInMemory[selected_index][2]
        #Calculating population Desity from the selected country
        popDensity = int(countryPopulation) / float(countrySize)

        #Display population Desity
        self.PopulationDensity.setText(f"{popDensity:.2f}")

    def PercentWPFunction(self,countryPop):
        #Sum of Population
        sumPop = 0

        #Calculating the sum of population
        for values in self.countryListInMemory:
            sumPop += float(values[1])
        
        #Calculating current selected country's percentage of the worlds population
        PWP = float((100 / sumPop) * float(countryPop))

        #Displaying the current selected country's percentage of the worlds population
        self.PercentWP.setText(f"{PWP:.4f}%")

    #The Total Area in Miles (When choosen in the Drop down)
    def DDMilesFunction(self):
        selected_index = self.CountryList.currentRow()
        countrySize = self.countryListInMemory[selected_index][2]
        countrySize = float(countrySize)
        self.TotalAreaNr.setText(f"{countrySize:,.1f}")

    #The Total Area in Miles (When choosen in the Drop down)
    def DDKMFunction(self):
        selected_index = self.CountryList.currentRow()
        countrySize = self.countryListInMemory[selected_index][2]
        squareKM = 2.58999
        CSKM = float(countrySize) * squareKM
        self.TotalAreaNr.setText(f"{CSKM:,.1f}")

    #The Total Area in KM (When choosen in the Drop down)
    def RBKMFunc(self , enabled):
        selected_index = self.CountryList.currentRow()
        countryPopulation = self.countryListInMemory[selected_index][1]
        countrySize = self.countryListInMemory[selected_index][2]
        squareKM = 2.58999
        popDensity = int(countryPopulation) / float(countrySize)
        popDensityKM = popDensity * squareKM
        if enabled:
            self.setKM(popDensityKM)

    def updateCountryPop(self):
        #current country
        selected_index = self.CountryList.currentRow()
        # stringPOP = self.countryListInMemory[selected_index][1]
        
        try:
            #replace the comma to an empty string and make it to a string to write it later to a file
            stringPOP = self.Popamount.text().replace(",","")

            if stringPOP.isnumeric():
                self.countryListInMemory[selected_index][1] = stringPOP
                self.actionSave_To_File.setEnabled(True)
                #reload the list in case the name was changed
                self.LoadCountriesListBox()

                # popup a message to the user to let them know that the data was updated
                QMessageBox.information(self,
                                        'Updated',
                                        'Data has been updated in memory, but hasn''t been updated in the file yet',
                                        QMessageBox.Ok)

                #Select the current country instead of going all the way down
                self.CountryList.setCurrentRow(selected_index)

                #unsaved file changes have happend, so unsaved_changes = True
                self.unsaved_changes = True
            else:
                QMessageBox.information(self, 'Invalid', 'Data is invalid so not updated in memory', QMessageBox.Ok)
                self.unsaved_changes = False
                countryPopulation = int(self.countryListInMemory[selected_index][1])
                self.Popamount.setText(f"{countryPopulation:,}")
        except ValueError:
            QMessageBox.information(self, 'Invalid', 'Data is invalid so not updated in memory', QMessageBox.Ok)
            self.unsaved_changes = False
            countryPopulation = int(self.countryListInMemory[selected_index][1])
            self.Popamount.setText(f"{countryPopulation:,}")

    def save_action_clicked(self):
        # call the save_changes_to_file helper function which does the heavy lifting
        self.save_changes_to_file()

        # popup a message to the user confirming that the changes were saved to the file
        QMessageBox.information(self, 'Saved', 'Changes were saved to the file', QMessageBox.Ok)

        # toggle the unsaved_changes variable back to False because we no longer have any unsaved changes
        self.unsaved_changes = False

    def save_changes_to_file(self):

        fileName = "Final Project\\Basic_CountriesOfTheWorld\\countries.txt"
        accessMode = "w"

        myFile = open(fileName, accessMode)

        for value in self.countryListInMemory:
            myFile.write(",".join(value) + "\n")

    def closeEvent(self, event):
        #Popup to check if user realy wants to exit
        quit_msg = "Are you sure you want to exit?"

        reply = QMessageBox.question(self, 'Exit?',
                 quit_msg, QMessageBox.Yes, QMessageBox.No)
        
        if reply == QMessageBox.Yes:
            event.accept()
        else:
            event.ignore()
        
        #pop up for user to ask if he wants to save the updated memorylist to the file
        if self.unsaved_changes == True:

            msg = "Save changes to file before closing?"

            reply = QMessageBox.question(self, 'Save?',
                     msg, QMessageBox.Yes, QMessageBox.No)

            if reply == QMessageBox.Yes:
                self.save_changes_to_file()
                event.accept()
    
    #Exit program for exit trigger
    def exit_application(self):
        QApplication.closeAllWindows()


if __name__ == "__main__":
    app = QApplication(sys.argv)
    the_form = MyForm()
    the_form.show()
    sys.exit(app.exec_())
# END DO NOT MODIFY
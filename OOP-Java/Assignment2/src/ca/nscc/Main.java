package ca.nscc;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //Variables
        String person = "";
        int studentYears = 0;
        int staffYears = 0;
        String personName;
        String personAddress;
        Object[] buttonSelect = {"Student","Staff","Finish"};

        //the two class arrays
        ArrayList<Staff> staffMem = new ArrayList<>();
        ArrayList<Student> studentMem = new ArrayList<>();

        //student / staff selection
        int option = JOptionPane.showOptionDialog(null,"Select Student or Staff"
                ,"Accounting App",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE
                ,null,buttonSelect,buttonSelect[0]);

        //get student and staff information until user chooses Finish
        while (option != 2) {
            //if user chooses student use student related years
            if (option == 0) {
                person = "Student";
                do {
                    String sStudentYears = JOptionPane.showInputDialog(null
                            , "Enter " + buttonSelect[0] + " Year (1-4)"
                            , "Input", JOptionPane.QUESTION_MESSAGE);
                    try {
                        studentYears = Integer.parseInt(sStudentYears);
                        if (studentYears < 1 || studentYears > 4){
                            JOptionPane.showMessageDialog(null
                                    , "Please enter a valid number."
                                    , "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null
                                ,"Please enter a number.", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }while (studentYears < 1 || studentYears > 4);
            }
            //if user chooses staff use staff related years
            if (option == 1) {
                person = "Staff";

                do {
                    String sStaffYears = JOptionPane.showInputDialog(null
                            , "Enter " + buttonSelect[1] + " Years of Service"
                            ,"Input",JOptionPane.QUESTION_MESSAGE);

                    try {
                        staffYears = Integer.parseInt(sStaffYears);
                        if (staffYears <= 0 || staffYears > 30){
                            JOptionPane.showMessageDialog(null
                                    , "Please enter a valid number.", "Message"
                                    , JOptionPane.INFORMATION_MESSAGE);
                        }
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null
                                , "Please enter a number.", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }while (staffYears <= 0 || staffYears > 30);
            }
            //Get the Name
            do {
                personName = JOptionPane.showInputDialog(null
                        , "Enter " + person + " Name", "input", JOptionPane.QUESTION_MESSAGE);
                if (personName.equals("")) {
                    JOptionPane.showMessageDialog(null, "Pleas Enter valid information!"
                            , "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }while (personName.equals(""));

            //Get the Address
            do {
                personAddress = JOptionPane.showInputDialog(null
                        , "Enter " + person + " Address", "Input", JOptionPane.QUESTION_MESSAGE);
                if (personAddress.equals("")){
                    JOptionPane.showMessageDialog(null
                            ,"Pleas Enter valid information!","Message",JOptionPane.INFORMATION_MESSAGE);
                }
            }while (personAddress.equals(""));

            //append them to their corresponding class array
            if (option == 0) { //
                studentMem.add(new Student(personName, personAddress, studentYears)); //Add new student
            } else if (option == 1) {
                staffMem.add(new Staff(personName, personAddress, staffYears)); //Add new staff
            }

            //ask user (Student,Staff,Finish) again
            option = JOptionPane.showOptionDialog(null,"Select Student or Staff"
                    ,"Accounting App",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE
                    ,null,buttonSelect,buttonSelect[0]);
        }

        //variables
        String studentReport = "";
        String staffReport = "";
        double staffOutgoing = 0;
        double studentIncoming = 0;
        int studentCount = 1;
        int staffCount = 1;


        //for each Student het the report and their fee
        for (Student currentStudent: studentMem) {
            studentReport = studentReport.concat(studentCount + currentStudent.toString());
            studentCount = studentCount + 1;
            studentIncoming = studentIncoming + currentStudent.getFee();
        }
        //for each staff get their report and pay
        for (Staff currentStaff: staffMem) {
            staffReport = staffReport.concat(staffCount + currentStaff.toString());
            staffCount = staffCount + 1;
            staffOutgoing = staffOutgoing + currentStaff.getPay();
        }

        studentIncoming = studentIncoming / 2;
        staffOutgoing = staffOutgoing / 26;

        //Total calculation
        double total = studentIncoming - staffOutgoing;

        //END REPORT
        JOptionPane.showMessageDialog(null,"Students: [Total" + studentMem.size() + "]\n"
                + studentReport + "\nStaff: [Total" + staffMem.size() + "]\n" + staffReport + "\n\n" + "Result:\n"
                        + "Outgoing: $" + String.format("%.2f",staffOutgoing) + "\n" + "Incoming: $"
                        + String.format("%.2f",studentIncoming) + "\n" + "Total: $" + String.format("%.2f",total)
                ,"Report",JOptionPane.INFORMATION_MESSAGE);
    }
}
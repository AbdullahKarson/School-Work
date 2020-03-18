#import <stdio.h>

int main() {
    double hoursWorked;
    double hourlyWage;

    double vctBonus = 0.04;

    int numOfPaysPerYear = 26;

    double employmentAmt = 1245.00;
    double cppExceptionAmt = 3500.00;
    double fedPersonalAmt = 13229.00;
    double provPersonalAmt = 11481.00;

    double federalTaxDeduction = 0.15;
    double provincialTaxDeduction = 0.08481;

    double cppDeduction = 0.0525;
    double eiDeduction = 0.0158;

    double grossPay;
    double vacation;
    double totalIncome;

    double annualIncome;
    double annualCpp;
    double annualEI;

    double federalTax;
    double provincialTax;
    double totalTax;

    double cpp;
    double ei;

    double netPay;

    printf("Welcome to the pay cheque calculator!\n\n");

    printf("Enter the number of hours worked:");
    scanf("%lf", &hoursWorked);

    printf("Enter the wage per hour: $");
    scanf("%lf", &hourlyWage);

    printf("\n");

    grossPay = hoursWorked * hourlyWage;
    vacation = grossPay * vctBonus;
    totalIncome = grossPay + vacation;

    /*Annual Income, CPP, EI*/
    annualIncome = totalIncome * numOfPaysPerYear;
    annualCpp = (annualIncome - cppExceptionAmt) * cppDeduction;
    annualEI = annualIncome * eiDeduction;

    /*Tax Calculation using the annual values*/
    federalTax = (annualIncome - fedPersonalAmt - annualCpp
                 - annualEI - employmentAmt)
                 * federalTaxDeduction / numOfPaysPerYear;
    provincialTax = (annualIncome - annualCpp - annualEI - provPersonalAmt)
                    * provincialTaxDeduction / numOfPaysPerYear;
    totalTax = federalTax + provincialTax;

    cpp = annualCpp / numOfPaysPerYear;
    ei = annualEI / numOfPaysPerYear;

    netPay = totalIncome - totalTax - cpp - ei;

    //Display Paycheck
    printf("Gross Pay:\t$%.2f\n", grossPay);
    printf("Vacation:\t$%.2f\n", vacation);
    printf("Total Income:\t$%.2f\n\n",totalIncome);

    printf("Federal Tax:\t$%.2f\n",federalTax);
    printf("Provincial Tax:\t$%.2f\n", provincialTax);
    printf("Total Tax:\t$%.2f\n\n",totalTax);

    printf("CPP:\t\t$%.2f\n", cpp);
    printf("EI:\t\t$%.2f\n\n", ei);

    /*Net pay*/
    printf("Net Pay:\t$%.2f\n", netPay);

    return 0;
}

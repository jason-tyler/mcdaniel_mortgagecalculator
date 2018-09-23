import java.text.NumberFormat;

class Mortgage {
    private short term;
    private float rate;
    private float principal;

    /*
     * Default constructor.
     * The arguments will be validated and an illegal argument exception will be thrown if arguments do not
     * satisfy the business requirements.
     */
    Mortgage(short loanTerm, float loanRate, float loanPrincipal) throws IllegalArgumentException {
        //Validate the arguments.
        if (this.isValidPrincipalAmount(loanPrincipal) == false) {
            throw new IllegalArgumentException(loanPrincipal +
                    " is an invalid loan amount. Mortgage principal must be greater than or equal to $50,000 and " +
                    "less than or equal to $1,000,000.");
        }

        if (this.isValidRate(loanRate) == false) {
            throw new IllegalArgumentException(loanRate + " is invalid. Mortgage rate must be between 0.01% and 50%");
        }

        if (this.isValidTerm(loanTerm) == false) {
            throw new IllegalArgumentException(loanTerm +
                    " is invalid. Mortgage term must be 5, 10, 15, or 30.");
        }

        // Convert the term from years to months.
        this.term = (short)(loanTerm * 12);
        // Convert to annual interest rate to a decimal and then to a monthly rate.
        this.rate = loanRate / 100.0f / 12.0f;
        this.principal = loanPrincipal;
    }

    /*
     * Print the monthly payment of the mortgage.
     */
    void printMonthlyPayment(){
        System.out.print("Monthly payment on $" + this.principal + " for " + this.term + " months " +
                "at a monthly rate of " + this.rate + "%: ");
        System.out.print(this.formatCurrency(this.calculateMonthlyPayment()));
    }

    void printTotalPayments(){
        // Calculate total interest.
        float totalPayments = this.calculateMonthlyPayment() * this.term;

        float interestPaid = totalPayments - this.principal;

        System.out.println("Principal: " + formatCurrency(this.principal));
        System.out.println("Interest: " + formatCurrency(interestPaid));
        System.out.println("Total: " + formatCurrency(this.principal + interestPaid));
    }

    void printPaymentSchedule(){
        float monthlyPayment = this.calculateMonthlyPayment();
        float balance = this.principal;
        float interestPayment = 0.0f;
        float principalPayment = 0.0f;

        System.out.print("Month \t\t");
        System.out.print("Principal\t\t");
        System.out.print("Interest\t\t");
        System.out.println("Total Payment\t\t");
        System.out.println("----------------------------------------------------------------------------------");

        for (short month = 0; month < this.term; month++){
            // Calculate the payment portions
            interestPayment = balance * this.rate;
            principalPayment = monthlyPayment - interestPayment;
            balance -= principalPayment;
            // Output the numbers
            System.out.print(month + 1 + "\t\t\t");
            System.out.print(this.formatCurrency(principalPayment) + "\t\t\t");
            System.out.print(this.formatCurrency(interestPayment) + "\t\t\t");
            System.out.println(this.formatCurrency(monthlyPayment) + "\t\t");
        }
    }

    /*
    Calculates the monthly payment based upon the current principal, rate, and term.
     */
    private float calculateMonthlyPayment(){
        return (float)((this.principal*this.rate) / (1-Math.pow(1+this.rate, -this.term)));
    }

    private String formatCurrency(float number){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return  formatter.format(number);
    }

    private boolean isValidTerm(short loanTerm){
        if (loanTerm == 5 || loanTerm == 10 || loanTerm == 15 || loanTerm == 30)
            return true;
        else
            return false;
    }

    private boolean isValidRate(float loanRate){
        if (loanRate > (float)0.01 && loanRate <= (float)50.0)
            return true;
        else
            return false;
    }

    private boolean isValidPrincipalAmount(float loanPrincipal){
        if (loanPrincipal >= (float)50000.0 && loanPrincipal <= (float)1000000)
            return true;
        else
            return false;
    }
}

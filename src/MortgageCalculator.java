import java.util.Scanner;

public class MortgageCalculator {

    final static String EMPTY = "";

    public static void main(String [] args){
        /*
        Collect the required inputs for the loan by prompting the user for the loan requirements.
        Instantiate the Mortgage object with the mortgage loan requirements. Use a try-catch block to
        handle an illegal argument exception that is thrown by the Mortgage constructor.
         */
        // Scanner object to collect user inputs.
        Scanner scanner = new Scanner(System.in);

        // Mortgage amount
        System.out.print("What is the principal amount of the loan (e.g. 100000.00)? ");

        float principal = scanner.nextFloat();

        // Mortgage rate
        System.out.print("What is the annual rate (e.g. 5.5)? ");

        float rate = scanner.nextFloat();

        // Mortgage term
        System.out.print("What is the loan term in years (e.g. 5, 10, 15, or 30)? ");

        short term = scanner.nextShort();

        // Create an object reference to a mortgage.
        Mortgage mortgage = null;

        // Initialize mortgage with a new instance of the Mortgage class. Use try-catch to handle illegal arguments.
        try{
            mortgage = new Mortgage(term, rate, principal);
        }
        catch (IllegalArgumentException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        // Print the menu and allow the user to select an option.
        byte menuSelection = printMenu(scanner);

        // Evaluate the selection.
        while(menuSelection != 0){
            switch (menuSelection){
                case 1:
                    System.out.println(EMPTY);
                    mortgage.printMonthlyPayment();
                    System.out.println(EMPTY);
                    break;
                case 2:
                    System.out.println(EMPTY);
                    mortgage.printTotalPayments();
                    System.out.println(EMPTY);
                    break;
                case 3:
                    System.out.println(EMPTY);
                    mortgage.printPaymentSchedule();
                    System.out.println(EMPTY);
                    break;
                default:
                    System.out.println("You made an invalid selection.");
                    System.out.println(EMPTY);
            }

            menuSelection = printMenu(scanner);
        }

        System.exit(0);
    }

    /*
     * Prints a menu of options that the user can select.
     */
    private static byte printMenu(Scanner scanner){
        System.out.println(EMPTY);
        System.out.println("Select from the following options: ");
        System.out.println("1) Print the monthly payment.");
        System.out.println("2) Print total mortgage payments.");
        System.out.println("3) Print mortgage repayment schedule.");
        System.out.println(EMPTY);
        System.out.println("0) Exit Application");
        System.out.println(EMPTY);
        System.out.print("Enter a number: ");
        return scanner.nextByte();
    }
}

import java.util.Scanner;

public class calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Welcome to Command Line Calculator!");
            System.out.println("You can Perform '+','-','*','/','%' Operations");

            // User Can Enter First Number
            System.out.println("Enter the First Number : ");
            double firstNumber = sc.nextDouble();

            // Consume the newline character left in the buffer
            sc.nextLine();

            // User Can Enter second Number
            System.out.println("Enter the second Number : ");
            double secondNumber = sc.nextDouble();

            // Consume the newline character left in the buffer
            sc.nextLine();

            // User Can Give Operation like +, -, *, /, %;
            System.out.println("Enter an Operation ('+','-','*','/','%') : ");
            char operation = sc.nextLine().charAt(0); 

            double result = 0;

            switch (operation) {
                case '+':
                    result = firstNumber + secondNumber;
                    break;

                case '-':
                    result = firstNumber - secondNumber;
                    break;

                case '*':
                    result = firstNumber * secondNumber;
                    break;

                case '/':
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        System.out.println("Please Enter a Valid Number!");
                        System.exit(1);
                    }
                    break;

                case '%':
                    if (secondNumber != 0) {
                        result = firstNumber % secondNumber;
                    } else {
                        System.out.println("Please Enter a Valid Number!");
                        System.exit(1);
                    }
                    break;

                default:
                    System.out.println("Please Enter a Valid Operation!");
                    System.exit(1);
            }

            System.out.println(firstNumber + " " + operation + " " + secondNumber + " = " + result);
            System.out.println("Answer is : "+result);

        } catch (Exception e) {
            System.out.println("Exception Occurred: " + e.getMessage());
            System.exit(1);
        } finally {
            sc.close();
        }
    }
}
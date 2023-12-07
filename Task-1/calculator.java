import java.util.Scanner;

public class calculator {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Welcome to Command Line Calculator!");
            System.out.println("You can Perform '+', '-', '*', '/', '%' Operations");

            System.out.println("Enter the First Number : ");
            double firstNumber = sc.nextDouble();
            sc.nextLine();

            System.out.println("Enter the second Number : ");
            double secondNumber = sc.nextDouble();
            sc.nextLine();

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

            char decision;
            do {
                System.out.println("If you want to continue calculation Enter : 'y' , Exit : 'n' ");
                decision = sc.nextLine().charAt(0);

                if (decision == 'y') {
                    System.out.println("Enter an Operation ('+','-','*','/','%') : ");
                    char operation2 = sc.nextLine().charAt(0);

                    System.out.println("Enter the Number : ");
                    double newNumber = sc.nextDouble();
                    sc.nextLine();

                    switch (operation2) {
                        case '+':
                            result += newNumber;
                            break;
                        case '-':
                            result -= newNumber;
                            break;
                        case '*':
                            result *= newNumber;
                            break;
                        case '/':
                            if (newNumber != 0) {
                                result /= newNumber;
                            } else {
                                System.out.println("Please Enter a Valid Number!");
                                System.exit(1);
                            }
                            break;
                        case '%':
                            if (newNumber != 0) {
                                result %= newNumber;
                            } else {
                                System.out.println("Please Enter a Valid Number!");
                                System.exit(1);
                            }
                            break;
                        default:
                            System.out.println("Please Enter a Valid Operation!");
                            System.exit(1);
                    }
                    System.out.println(result + " " + operation2 + " " + newNumber + " = " + result);
                }
            } while (decision == 'y');
        } catch (Exception e) {
            System.out.println("Exception Occurred: " + e.getMessage());
            System.exit(1);
        } finally {
            sc.close();
        }
    }
}

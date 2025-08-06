import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Fixed user credentials
        String userId = "user123";
        String userPin = "1234";
        double balance = 1000.0;

        ArrayList<String> transactionHistory = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Login
        System.out.println("Welcome to the ATM");
        System.out.print("Enter User ID: ");
        String enteredId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        if (!enteredId.equals(userId) || !enteredPin.equals(userPin)) {
            System.out.println("Invalid ID or PIN. Access Denied.");
            return;
        }

        int choice;
        do {
            // Menu options
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1. View Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. View Available Balance");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (transactionHistory.isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        System.out.println("\nTransaction History:");
                        for (String t : transactionHistory) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAmount > balance) {
                        System.out.println("Not enough balance.");
                    } else {
                        balance -= withdrawAmount;
                        String time = LocalDateTime.now().format(dtf);
                        transactionHistory.add("[" + time + "] Withdrawn: ₹" + withdrawAmount);
                        System.out.println("Withdraw successful.");
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    balance += depositAmount;
                    String time = LocalDateTime.now().format(dtf);
                    transactionHistory.add("[" + time + "] Deposited: ₹" + depositAmount);
                    System.out.println("Deposit successful.");
                    break;

                case 4:
                    System.out.println("Available Balance: ₹" + balance);
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 5);

        scanner.close();
    }
}

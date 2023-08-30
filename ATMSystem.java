import java.util.Scanner;

class User {
    private String userId;
    private String userPin;
    private double balance;

    public User(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class ATMSystem {
    private static User currentUser;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeUsers();
        login();
    }

    private static void initializeUsers() {
        User user1 = new User("user1", "1234", 1000);
        User user2 = new User("user2", "5678", 1500);
        // Add more users as needed
    }

    private static void login() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter User PIN: ");
        String userPin = scanner.nextLine();

        if (validateUser(userId, userPin)) {
            System.out.println("Login successful!");
            currentUser = getUserById(userId);
            showMainMenu();
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    private static boolean validateUser(String userId, String userPin) {
        User user = getUserById(userId);
        return user != null && user.getUserPin().equals(userPin);
    }

    private static User getUserById(String userId) {
        // Implement logic to fetch user from a database or list
        return null; // Return the user object if found
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewBalance();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private static void viewBalance() {
        System.out.println("Your balance: $" + currentUser.getBalance());
    }

    private static void withdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= currentUser.getBalance()) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            System.out.println("Withdrawal successful. New balance: $" + currentUser.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    private static void deposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            currentUser.setBalance(currentUser.getBalance() + amount);
            System.out.println("Deposit successful. New balance: $" + currentUser.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private static void transfer() {
        System.out.print("Enter recipient's User ID: ");
        String recipientUserId = scanner.nextLine();

        User recipient = getUserById(recipientUserId);
        if (recipient != null) {
            System.out.print("Enter the amount to transfer: $");
            double amount = scanner.nextDouble();
            if (amount > 0 && amount <= currentUser.getBalance()) {
                currentUser.setBalance(currentUser.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Invalid amount or insufficient balance.");
            }
        } else {
            System.out.println("Recipient not found.");
        }
    }
}

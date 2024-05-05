import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank(100.0);

        try {
            bank.load("accounts.txt");
        } catch (Exception e) {
            System.out.println("Unable to load accounts.");
        }

        int choice = 0;
        System.out.println("Welcome to BankApp!");

        while(true) {
            loop:
            while(choice != 7) {
                System.out.println("\n1. Create new account");
                System.out.println("2. Perform operations in an existing account");
                System.out.println("3. Delete an existing account");
                System.out.println("4. Display the average of all account balances");
                System.out.println("5. Display the maximum and minimum account balances");
                System.out.println("6. Display all accounts that have low balance");
                System.out.println("7. Quit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("\n1. Checking account");
                        System.out.println("2. Savings account");
                        System.out.print("Enter your choice: ");
                        int accountType = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter account holder name: ");
                        String name = scanner.next();
                        scanner.nextLine();

                        System.out.print("Enter gender: ");
                        char gender = scanner.next().charAt(0);
                        scanner.nextLine();

                        System.out.print("Enter age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter address: ");
                        String address = scanner.next();
                        scanner.nextLine();

                        System.out.print("Enter phone number: ");
                        String phoneNumber = scanner.next();
                        scanner.nextLine();

                        System.out.print("Enter balance: ");
                        double balance = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Enter date: ");
                        String date = scanner.next();
                        scanner.nextLine();

                        System.out.print("Enter account number: ");
                        String accountNumber = scanner.next();
                        scanner.nextLine();

                        while(bank.findAccount(accountNumber) != null) {
                            System.out.println("Account already exists.");
                            System.out.print("Enter account number: ");
                            accountNumber = scanner.next();
                            scanner.nextLine();
                        }

                        System.out.print("Enter the withdraw limit: ");
                        double withdrawLimit = scanner.nextDouble();

                        Person person = new Person(name, gender, age, address, phoneNumber);

                        if (accountType == 1) {
                            CheckingAccount account =
                                    new CheckingAccount(accountNumber, date, balance, withdrawLimit, 0.0, person, "checking");
                            bank.addAccount(account);
                            System.out.println("Checking account created successfully.");
                        } else if (accountType == 2) {
                            SavingsAccount account =
                                    new SavingsAccount(accountNumber, date, balance, withdrawLimit, 0.0, person, "savings");
                            bank.addAccount(account);
                            System.out.println("Savings account created successfully.");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                        System.out.println("Account number: " + accountNumber);
                        break;
                    case 2:
                        System.out.print("Enter an account number: ");
                        accountNumber = scanner.next();
                        Account existingAccount = bank.findAccount(accountNumber);
                        if (existingAccount == null) {
                            System.out.println("Account not found.");
                            break;
                        } else {
                            int operation = 0;

                            while(true) {
                                if (operation == 4) {
                                    continue loop;
                                }

                                System.out.println("Select operation");
                                System.out.println("1. Deposit");
                                System.out.println("2. Withdraw");
                                System.out.println("3. Get account info");
                                System.out.println("4. Back");
                                operation = scanner.nextInt();
                                scanner.nextLine();
                                double amount;

                                switch (operation) {
                                    case 1:
                                        System.out.print("Enter amount to deposit in checking account: ");
                                        amount = scanner.nextDouble();
                                        existingAccount.depositMoney(amount);
                                        System.out.println("Deposit successful!");
                                        break;
                                    case 2:
                                        System.out.print("Enter amount to withdraw in checking account: ");
                                        amount = scanner.nextDouble();
                                        existingAccount.withdrawMoney(amount);
                                        break;
                                    case 3:
                                        System.out.println("Account Information: \n" + existingAccount);
                                        break;
                                    case 4:
                                        System.out.println("Going back...");
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                }
                            }
                        }
                    case 3:
                        System.out.print("Enter the account number attached to the account to delete: ");
                        accountNumber = scanner.next();
                        bank.deleteAccount(accountNumber);
                        System.out.println("Account deleted successfully.");
                        break;
                    case 4:
                        System.out.println("Average balance of all accounts: " + bank.getAverageBalance());
                        break;
                    case 5:
                        System.out.println("Minimum balance: " + bank.getMinimumBalance());
                        System.out.println("Maximum balance: " + bank.getMaximumBalance());
                        break;
                    case 6:
                        System.out.println("Low balance accounts: " + bank.getLowBalanceAccounts());
                        break;
                    case 7:
                        try {
                            bank.save("accounts.txt");
                        } catch (Exception e) {
                            System.out.println("Unable to save accounts to file.");
                        }

                        System.out.println("Thank you for choosing BankApp!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

            return;
        }
    }
}

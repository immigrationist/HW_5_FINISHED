import java.util.Scanner;

public class Account<Bank> {
    private String accountNumber;
    private String dateCreated;
    private double currentBalance;
    private double withdrawLimit;
    private double withdrawAmount;
    private Person accountHolder;
    private String accountType;

    public Account(String accountNumber, String dateCreated, double currentBalance,
                   double withdrawLimit, double withdrawAmount, Person accountHolder, String accountType) {
        this.accountNumber = accountNumber;
        this.dateCreated = dateCreated;
        this.currentBalance = currentBalance;
        this.withdrawLimit = withdrawLimit;
        this.withdrawAmount = withdrawAmount;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {

        if (!accountNumber.isEmpty())
            this.accountNumber = accountNumber;

    }

    public Person getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Person accountHolder) {
        this.accountHolder = accountHolder;
    }


    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {

        if (!dateCreated.isEmpty())
            this.dateCreated = dateCreated;

    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(double withdrawLimit) {

        if (withdrawLimit >= 0)
            this.withdrawLimit = withdrawLimit;

    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {

        if (currentBalance >= 0)
            this.currentBalance = currentBalance;

    }


    public String toString() {

        return "Bank Account" + "\nAccount Number: " + accountNumber + "\nAccount Holder: "
                + accountHolder + "\nDate Created: " + dateCreated +
                "\nCurrent Balance: " + currentBalance + "\nWithdraw Limit: " +
                withdrawLimit + "\nWithdraw Amount: " + withdrawAmount;

    }

    public String getAccountHolderName() {
        return this.accountHolder.getName();
    }

    public void depositMoney(double amount) {
        if(amount > 0)
         this.currentBalance += amount;
    }

    public boolean withdrawMoney(double amount) {
        if (this.currentBalance - amount >= 0.0 && this.withdrawAmount + amount <= this.withdrawLimit) {
            this.currentBalance -= amount;
            this.withdrawAmount += amount;
            System.out.println("withdraw successful");
            return true;
        } else {
            System.out.println("withdraw failed, withdraw limit reached");
            return false;
        }
    }

    public String convertToText() {
        String text = "";
        text = text + this.accountNumber + " " + this.accountType + " " + this.accountHolder.getName() + " " + this.dateCreated + "\n";
        text = text + this.currentBalance + " " + this.withdrawLimit + " " + this.withdrawAmount + "\n";
        text = text + "End\n";
        return text;
    }

    public void loadFromText(String text) {
        Scanner strScanner = new Scanner(text);
        this.accountNumber = strScanner.next();
        this.accountType = strScanner.next();
        String name = strScanner.next();
        this.accountHolder.setName(name);
        this.dateCreated = strScanner.next();
        strScanner.nextLine();
        this.currentBalance = strScanner.nextDouble();
        this.withdrawLimit = strScanner.nextDouble();
        this.withdrawAmount = strScanner.nextDouble();
    }
}

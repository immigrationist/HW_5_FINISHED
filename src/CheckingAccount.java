//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class CheckingAccount extends Account {
    private int numDepositsSinceLastWithdrawal = 0;

    public CheckingAccount(String accountNumber, String dateCreated, double curBal,
                           double withdrawLimit, double withdrawAmount, Person accountHolder, String accountType) {
        super(accountNumber, dateCreated, curBal, withdrawLimit, withdrawAmount, accountHolder, accountType);
    }

    public void depositMoney(double amount) {
        super.depositMoney(amount);
        ++this.numDepositsSinceLastWithdrawal;
        if (this.numDepositsSinceLastWithdrawal % 10 == 0) {
            super.depositMoney(super.getCurrentBalance() * 0.05);
        }

    }

    public boolean withdrawMoney(double amount) {
        super.withdrawMoney(amount);
        this.numDepositsSinceLastWithdrawal = 0;
        return false;
    }

    public String toString() {
        String accountNumber = this.getAccountNumber();
        return "Checking account\nAccount Number: " + accountNumber +
                "\nAccount Holder: " + this.getAccountHolderName() + "\nDate Created: " + this.getDateCreated() +
                "\nCurrent Balance: " + this.getCurrentBalance() + "\nWithdraw Limit: " + this.getWithdrawLimit()+
                "\nNumber of Deposits since last Withdrawal: " + this.numDepositsSinceLastWithdrawal + "\n";
    }
}

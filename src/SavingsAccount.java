public class SavingsAccount extends Account {
    private int depositSinceLastWithdraw = 0;
    private int withdrawSinceLastDeposit = 0;

    public SavingsAccount(String accountNumber, String dateCreated,
                          double currentBalance, double withdrawLimit,
                          double withdrawAmount, Person accountHolder, String accountType) {
        super(accountNumber, dateCreated, currentBalance,
                withdrawLimit, withdrawAmount, accountHolder, accountType);
    }

    public void depositMoney(double amount) {
        super.depositMoney(amount);
        this.withdrawSinceLastDeposit = 0;
        ++this.depositSinceLastWithdraw;
        if (this.depositSinceLastWithdraw == 10) {
            super.depositMoney(super.getCurrentBalance() * 0.2);
            this.depositSinceLastWithdraw = 0;
        }

    }

    public boolean withdrawMoney(double amount) {
        super.withdrawMoney(amount);
        this.depositSinceLastWithdraw = 0;
        ++this.withdrawSinceLastDeposit;
        if (this.withdrawSinceLastDeposit == 3) {
            super.withdrawMoney(super.getCurrentBalance() * 0.05);
            this.withdrawSinceLastDeposit = 0;
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String accountNumber = this.getAccountNumber();
        return "Savings account\nAccount Number: " + accountNumber +
                "\nAccount Holder: " + this.getAccountHolderName() + "\nDate Created: " + this.getDateCreated() +
                "\nCurrent Balance: " + this.getCurrentBalance() + "\nWithdraw Limit: " + this.getWithdrawLimit() +
                "\nNumber of Deposits since last Withdrawal: " + this.depositSinceLastWithdraw +
                "\nNumber of Withdrawals since last Deposit: " + this.withdrawSinceLastDeposit + "\n";
    }
}

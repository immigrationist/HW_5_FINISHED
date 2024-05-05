import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Bank {
    private ArrayList<Account> accounts = new ArrayList();
    private double balanceThreshold;

    public Bank(double balanceThreshold) {
        this.balanceThreshold = balanceThreshold;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public Account findAccount(String accountNumber) {
        Iterator var3 = this.accounts.iterator();

        while(var3.hasNext()) {
            Account account = (Account)var3.next();
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

    public void deleteAccount(String accountNumber) {
        Account accountToDelete = this.findAccount(accountNumber);
        if (accountToDelete != null) {
            this.accounts.remove(accountToDelete);
        }

    }

    public double getAverageBalance() {
        double totalBalance = 0.0;
        Iterator var4 = this.accounts.iterator();

        while(var4.hasNext()) {
            Account account = (Account)var4.next();
            totalBalance += account.getCurrentBalance();
            System.out.println(totalBalance);
        }

        return totalBalance / (double)this.accounts.size();
    }

    public double getMaximumBalance() {
        double maxBalance = Double.MIN_VALUE;
        Iterator var4 = this.accounts.iterator();

        while(var4.hasNext()) {
            Account account = (Account)var4.next();
            double balance = account.getCurrentBalance();
            if (balance > maxBalance) {
                maxBalance = balance;
            }
        }

        return maxBalance;
    }

    public double getMinimumBalance() {
        double minBalance = Double.MAX_VALUE;
        Iterator var4 = this.accounts.iterator();

        while(var4.hasNext()) {
            Account account = (Account)var4.next();
            double balance = account.getCurrentBalance();
            if (balance < minBalance) {
                minBalance = balance;
            }
        }

        return minBalance;
    }

    public ArrayList<Account> getLowBalanceAccounts() {
        ArrayList<Account> lowBalanceAccounts = new ArrayList();
        Iterator var3 = this.accounts.iterator();

        while(var3.hasNext()) {
            Account account = (Account)var3.next();
            if (account.getCurrentBalance() < this.balanceThreshold) {
                lowBalanceAccounts.add(account);
            }
        }
        return lowBalanceAccounts;
    }

    public void save(String filePath) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        for(int i = 0; i < this.accounts.size(); ++i) {
            Account account = (Account)this.accounts.get(i);
            osw.write(account.convertToText());
        }
        osw.close();
        fos.close();
    }

    public void load(String filePath) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        Scanner fileScanner = new Scanner(fis);
        String accountInfo = "";
        String textLine = "";

        while(fileScanner.hasNextLine()) {
            textLine = fileScanner.nextLine();
            if (textLine.equals("End")) {
                String accountType = accountInfo.split(" ")[1];
                Person person = new Person("", 'm', 20, "123 main st", "123-456-7890");
                if (accountType.equals("checking")) {
                    CheckingAccount account =
                            new CheckingAccount("", "", 0.0, 0.0, 0.0, person, "checking");
                    account.loadFromText(accountInfo);
                    account.getAccountHolder().setName(account.getAccountHolderName());
                    this.accounts.add(account);
                    accountInfo = "";
                } else {
                    SavingsAccount account =
                            new SavingsAccount("", "", 0.0, 0.0, 0.0, person, "savings");
                    account.loadFromText(accountInfo);
                    account.getAccountHolder().setName(account.getAccountHolderName());
                    this.accounts.add(account);
                    accountInfo = "";
                }
            } else {
                accountInfo = accountInfo + textLine + "\n";
            }
        }

    }
}

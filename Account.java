import java.util.ArrayList;

public class Account {
    //strong encapsulation
    private String name;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transaction;

    public Account(String name, User holder, Bank theBank) {
        this.name = name;
        this.holder = holder;
        //obtains an account UUID
        this.uuid = theBank.getNewAccontUUID(); //getNewAccontUUID() gets used from Bank class
        this.transaction = new ArrayList<Transaction>();
    }
    public String getUUID() { //getter method
        return this.uuid;
    }
    public double getBalance() {
        double balance = 0;
        for (Transaction t : this.transaction) {
            balance += t.getAmount(); //adds the transaction to the balance
        }
        return balance;
    }

    public String getSummaryLine() {
        double balance = this.getBalance();
        return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
    }

    public void printTransHistory() {
        System.out.printf("\nTranscation History for accounts %s\n", this.uuid);
        for (int t = this.transaction.size() - 1; t >= 0; t--) {
            System.out.printf(this.transaction.get(t).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo) {
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transaction.add(newTrans);
    }

}

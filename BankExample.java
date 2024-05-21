class BankAccount {
    private int balance = 1000;

    // Déposer de l'argent (méthode synchronisée)
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited " + amount + ", Balance: " + balance);
    }

    // Retirer de l'argent (méthode synchronisée)
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ", Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to withdraw " + amount + ", but only " + balance + " is available.");
        }
    }

    // Obtenir le solde actuel (méthode synchronisée)
    public synchronized int getBalance() {
        return balance;
    }
}

class BankTransaction implements Runnable {
    private BankAccount account;

    public BankTransaction(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            account.deposit(100);
            account.withdraw(50);
        }
    }
}

public class BankExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Thread t1 = new Thread(new BankTransaction(account), "Thread 1");
        Thread t2 = new Thread(new BankTransaction(account), "Thread 2");

        t1.start();
        t2.start();
    }
}

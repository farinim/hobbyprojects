import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SavingAccount {
    String accountNumber;
    double balance;
    ReentrantReadWriteLock rwLock;

    public SavingAccount( String accountNumber, double balance ) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.rwLock = new ReentrantReadWriteLock();
    }

    ReentrantReadWriteLock getRwLock(){
        return rwLock;
    }
    public double balance() {
        rwLock.readLock().lock();
        try {
            return balance;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public boolean debit( double amount ) {
        rwLock.readLock().lock();
        try {
            if (balance >= amount) {
                rwLock.readLock().unlock();
                rwLock.writeLock().lock();
                try {
                    balance -= amount;
                    return true;
                } finally {
                    rwLock.writeLock().unlock();
                }
            } else {
                return false;
            }
        } finally {
            if(rwLock.getReadHoldCount() > 0)
                rwLock.readLock().unlock();
        }
    }

    public void credit( double amount ) {
        rwLock.writeLock().lock();
        try {
            balance += amount;
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}

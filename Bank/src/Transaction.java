import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Transaction {
    long transactionId;
    SavingAccount from,to;
    double amount;
    ReentrantReadWriteLock fromLock;
    ReentrantReadWriteLock toLock;

    public Transaction( long transactionId, SavingAccount from, SavingAccount to, double amount ) {
        this.transactionId =  transactionId;
        this.from = from;
        this.to = to;
        this.amount = amount;
        fromLock = new ReentrantReadWriteLock();
        toLock = new ReentrantReadWriteLock();
    }

    public boolean transfer(){
        fromLock.readLock().lock();
        try {

            if (from.balance() < amount) {
                //insufficient balance :abort
                return false;
            }
        }finally {
                fromLock.readLock().unlock();
        }
        fromLock.writeLock().lock();
        toLock.writeLock().lock();
        try {
            System.out.println(fromLock.getReadLockCount());
            System.out.println(toLock.getReadHoldCount());
            System.out.println(fromLock.getWriteHoldCount());
            System.out.println(toLock.getWriteHoldCount());
            from.debit(amount);
            to.credit(amount);
            return true;
        } finally {
            fromLock.writeLock().unlock();
            toLock.writeLock().unlock();
        }
    }
}

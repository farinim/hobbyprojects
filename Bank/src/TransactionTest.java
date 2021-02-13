import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TransactionTest {
    Transaction transaction;
    SavingAccount A,B;

    @Before
    public void setUp() {
        A = new SavingAccount("AA001",3000);
        B = new SavingAccount("AA001",3000);
    }

    @After
    public void tearDown() {
        A = null;
        B= null;
        transaction = null;
    }

    @Test
    public void transfer() {
        Random rand = new Random();
        transaction = new Transaction(rand.nextLong(), A, B,400);
        boolean response = transaction.transfer();
        if(response) {
            System.out.println("Transaction status : Success");
        }else{
            System.out.println("Transaction status : Failed");
        }
        System.out.println("Balance of A " + A.balance());
        System.out.println("Balance of B " + B.balance());
    }
}
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class TransactionTest {
    Transaction transaction;
    SavingAccount A,B;

    @Before
    public void setUp() {
        A = new SavingAccount("AA001",8000);
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
        ExecutorService executor = Executors.newFixedThreadPool(8);
        ArrayList<Future> futures = new ArrayList<>();
        for(int i=0; i <10 ; i++){
            Future f = executor.submit(() -> {
                    transaction = new Transaction(rand.nextLong(), A, B, 2000 );
                    boolean response = transaction.transfer();
                    if (response) {
                        System.out.println("Transaction status : Success");
                    } else {
                        System.out.println("Transaction status : Failed");
                    }
                });
            futures.add(f);

        }
        futures.forEach( o -> {
            try {
                o.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Balance of A " + A.balance());
        System.out.println("Balance of B " + B.balance());
    }
}
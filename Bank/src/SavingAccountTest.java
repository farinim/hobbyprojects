import static org.junit.Assert.*;

public class SavingAccountTest {

    SavingAccount A,B;
    @org.junit.Before
    public void setUp() {
        A = new SavingAccount("AA001",3000);
        B = new SavingAccount("AA001",3000);

    }

    @org.junit.After
    public void tearDown() {
        A = null;
        B = null;
    }

    @org.junit.Test
    public void balance() {
    }

    @org.junit.Test
    public void debit() {
    }

    @org.junit.Test
    public void credit() {
    }
}
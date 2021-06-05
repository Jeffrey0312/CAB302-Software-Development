package tradingPlatform;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    private Transaction transaction;
    @BeforeEach
    void setUp() {
        transaction=new Transaction(0,"Amy","Anna","CPU",5000,20);
        transaction.setTransactionId(1);
        transaction.setBuyer("Jeffrey");
        transaction.setSeller("Alex");
        transaction.setAsset("software");
        transaction.setAssetAmount(1000);
        transaction.setValue(200);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTransactionId(){
        assertEquals(1, transaction.getTransactionId());
    }

    @Test
    void getBuyer() {
        assertEquals("Jeffrey",transaction.getBuyer());
    }

    @Test
    void getSeller() {
        assertEquals("Alex",transaction.getSeller());
    }


    @Test
    void getAsset() {
        assertEquals("software",transaction.getAsset());
    }


    @Test
    void getAssetAmount() {
        assertEquals(1000,transaction.getAssetAmount());
    }


    @Test
    void getValue() {
        assertEquals(200,transaction.getValue());
    }


}
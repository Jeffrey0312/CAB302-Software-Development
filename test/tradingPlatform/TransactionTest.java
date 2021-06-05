package tradingPlatform;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    private Transaction transaction;
    @BeforeEach
    void setUp() {
        transaction=new Transaction("Amy","Anna","CPU",5000,20);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getBuyer() {
        assertEquals("Amy",transaction.getBuyer());
    }

    @Test
    void getSeller() {
        assertEquals("Anna",transaction.getSeller());
    }


    @Test
    void getAsset() {
        assertEquals("CPU",transaction.getAsset());
    }


    @Test
    void getAssetAmount() {
        assertEquals(5000,transaction.getAssetAmount());
    }


    @Test
    void getValue() {
        assertEquals(20,transaction.getValue());
    }


}
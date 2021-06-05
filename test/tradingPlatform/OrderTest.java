package tradingPlatform;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order(true, "Telstra", "CPU", 100, 10);
        order.setOrderId(1);
        order.setIsBuy(false);
        order.setOrganisation("Apple");
        order.setAsset("C");
        order.setAssetAmount(10);
        order.setValue(100);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getOrderId() {
        assertEquals(1, order.getOrderId());
    }

    @Test
    void getIsBuy() {
        assertEquals(false, order.getIsBuy());
    }


    @Test
    void getOrganisation() {
        assertEquals("Apple", order.getOrganisation());
    }

    @Test
    void getAsset() {
        assertEquals("C", order.getAsset());
    }

    @Test
    void getAssetAmount() {
        assertEquals(10, order.getAssetAmount());
    }


    @Test
    void getValue() {
        assertEquals(100,order.getValue());
    }
}
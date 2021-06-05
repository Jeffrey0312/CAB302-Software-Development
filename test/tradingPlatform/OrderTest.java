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
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getOrderId() {
        assertEquals(0, order.getOrderId());
    }

    @Test
    void setOrderId() {
    }

    @Test
    void getIsBuy() {
        assertEquals(true, order.getIsBuy());
    }

    @Test
    void setIsBuy() {
    }

    @Test
    void getOrganisation() {
        assertEquals("Telstra", order.getOrganisation());
    }

    @Test
    void setOrganisation() {
    }

    @Test
    void getAsset() {
        assertEquals("CPU", order.getAsset());
    }

    @Test
    void setAsset() {
    }

    @Test
    void getAssetAmount() {
        assertEquals(100, order.getAssetAmount());
    }

    @Test
    void setAssetAmount() {
    }

    @Test
    void getValue() {
        assertEquals(10,order.getValue());
    }

    @Test
    void setValue() {
    }
}
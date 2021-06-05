package tradingPlatform;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OrganisationalUnitTest {
 private OrganisationalUnit organisation;
    @BeforeEach
    void setUp() {
        HashMap<String,Integer> assets = new HashMap<>();
        assets.put("CPU",500);
        organisation=new OrganisationalUnit("Telstra",3000,assets);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals("Telstra",organisation.getName() );
    }

    @Test
    void setName() {
        organisation.setName("Optus");
        assertEquals("Optus",organisation.getName());

    }

    @Test
    void getCredits() {
        assertEquals(3000,organisation.getCredits());
    }

    @Test
    void setCredits() {
        organisation.setCredits(5000);
        assertEquals(5000,organisation.getCredits());
    }

    @Test
    void getAssets() {
        HashMap<String,Integer> assets = new HashMap<>();
        assets.put("CPU",500);
        assertEquals(assets,organisation.getAssets());
    }

    @Test
    void setAssets() {
        HashMap<String,Integer> assets = new HashMap<>();
        assets.put("CPU",300);
        organisation.setAssets(assets);
        assertEquals(assets,organisation.getAssets());

    }

    @Test
    void getAssetAmount() {


    }

    @Test
    void setAsset() {
    }

    @Test
    void buyAssets() {
    }

    @Test
    void sellAssets() {
    }

    @Test
    void cancelOrder() {
    }
}
package tradingPlatform;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TradingPlatformDataTest {
    TradingPlatformData data;
    OrganisationalUnit organisationalUnit;
    @BeforeEach
    public void setUpDataList() {

        data = new TradingPlatformData(data.data);
//        data.addOrganisation();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void addOrganisation() {
//      data.addOrganisation("Telstra");

    }

    @Test
    void setOrganisationCredits() {

    }

    @Test
    void deleteOrganisation() {
    }

    @Test
    void addUser() {

    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUser() {
        
    }

    @Test
    void persist() {
    }

    @Test
    void getOrganisation() {
        assertEquals("Telstra", data.getOrganisation(null));
    }

    @Test
    void getUserModel() {
    }

    @Test
    void getOrganisationModel() {
    }

    @Test
    void getSize() {
    }
}
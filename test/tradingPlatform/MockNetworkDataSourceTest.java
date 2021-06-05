package tradingPlatform;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;


class MockNetworkDataSourceTest {
    private TradingPlatformDataSource db;
    @BeforeEach
    void setUp() {
        db = new NetworkDataSource();

    }

    @AfterEach
    void tearDown() {
    }

    /**
     *test  if getOrganistaion from database (TRaddingPlatformDataSource)
     */
    @Test
    public void TestGetOrganisation() {
        OrganisationalUnit organisations = db.getOrganisation("Telstra");
        System.out.println(organisations);

    }

    /**
     * test the getOrganisationList exception in network data source.
     */


    @Test
    public void getOrganisationsList() {
        Set<String> organisations= db.getOrganisationsList();
        organisations.add("Telstra");
        organisations.add("Optus");
        System.out.println(organisations);

    }

    @Test
    public void setOrganisationCredits() {
        db.setOrganisationCredits("Telstra",5000);
        System.out.println(db);

    }

    @Test
    public void addAsset() {
        db.addAsset("Hardware");
        System.out.println(db);
    }

    @Test
    public void setOrganisationAssetAmount() {
        db.setOrganisationAssetAmount("Telstra","network data",4000);
        System.out.println(db);
    }

    @Test
    public void deleteAsset() {
        db.deleteAsset("network data");
        System.out.println(db);

    }

    @Test
    public void getUser() {
        User user= db.getUser("Amy");
        System.out.println(user);
    }


    @Test
    public void setUserOrganisation() {
        db.setUserOrganisation("Amy","Telstra");
        System.out.println(db);
    }

    @Test
    public void setUserPassword() {
        db.setUserPassword("Amy","123");
        System.out.println(db);

    }

    @Test
    public void deleteUser() {
        db. deleteUser("Amy");
        System.out.println(db);
    }

    @Test
    public void getUsersList() {
        Set<String> users= db.getUsersList();
        users.add("Alex");
        users.add("Jeff");
        System.out.println(users);

    }

    @Test
    public void login() {
        User user=db.login("staff","staff");
        System.out.println(user);
    }


    @Test
    public void deleteOrder() {
        db.deleteOrder(1);
        System.out.println(db);
    }

    @Test
    public void updateOrderAssetAmount() {
        db.updateOrderAssetAmount(1,300);
        System.out.println(db);
    }


//    @Test
//    public void addTransaction(){
//     Transaction transaction=new Transaction();
//     transaction.setBuyer("Alex");
//     transaction.setSeller("Jeff");
//     transaction.setTransactionId(10);
//     transaction.setAsset("CPU");
//     transaction.setValue(100);
//     transaction.setAssetAmount(200);
//
//     db.addTransaction(transaction);
//    }

    @Test
    public void deleteTransaction() {
        db.deleteTransaction(1);
        System.out.println(db);
    }




}
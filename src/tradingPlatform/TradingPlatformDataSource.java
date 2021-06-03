package tradingPlatform;

import java.util.List;
import java.util.Set;

/**
 * Provides functionality needed by any data source for the Trading Platform
 * application.
 */
public interface TradingPlatformDataSource {

    /**
     * Extracts all info related to the named organisation
     *
     * @param name name of the organisation
     */
    OrganisationalUnit getOrganisation(String name);

    /**
     * adds a new organisation to the database
     *
     * @param name name of organisation to add
     */
    void addOrganisation(String name);

    /**
     * deletes the organisation with the given name from the database
     *
     * @param name name of the organisation to delete
     */
    void deleteOrganisation(String name);

    /**
     * gets all organisation
     */
    Set<String> getOrganisationsList();

    /**
     * changes the amount of credits the provided organisation has to the provided number of credits
     *
     * @param name the name of the organisation that is having its credits changed
     * @param credits the new value for credits
     */
    void setOrganisationCredits(String name, int credits);

    /**
     * adds a new asset to the database
     * @param asset the new asset being added
     */
    void addAsset(String asset);

    /**
     * changes the amount of assets an organisation has
     *
     * @param organisation name of the organisation that the change will happen to
     * @param asset name of the asset the change will happen to
     * @param amount the new amount that the amount of assets will be changed to
     */
    void setOrganisationAssetAmount(String organisation, String asset, int amount);

    /**
     * deletes the given asset from the database
     *
     * @param name name of the asset to be deleted
     */
    void deleteAsset(String name);

    /**
     * Extracts all info related to the named organisation
     *
     * @param name name of the organisation
     */
    User getUser(String name);

    /**
     * adds a new user to the database
     *
     * @param u name of user to add
     */
    void addUser(User u);

    /**
     * deletes the user with the given name from the database
     *
     * @param name name of the user to delete
     */
    void deleteUser(String name);

    /**
     * gets all users
     */
    Set<String> getUsersList();

    /**
     * sets the organisation of the user
     * @param username the username of the user being changed
     * @param organisation the new organisation
     */
    void setUserOrganisation(String username, String organisation);

    /**
     * sets the password of the user
     * @param username the username of the user being changed
     * @param password the new password
     */
    void setUserPassword(String username, String password);

    /**
     * used to attempt to login to the system
     *
     * @param username username of the user attempting to login
     * @param password password of the user attempting to login
     * @return returns an instance of the user class with the users info if inputs are correct other wise returns null
     */
    User login(String username, String password);

    void addOrder(Order order);

    void deleteOrder(int orderId);

    void updateOrderAssetAmount(int orderId, int assetAmount);

    Set<Order> getOrderList();

    void addTransaction(Transaction transaction);

    void deleteTransaction(int transactionId);

    Set<Transaction> getTransactionsList();
    /**
     * Gets the number of user in the user list.
     * @return size of user list.
     */
    int getUserSize();

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisted.
     */
    void close();

}

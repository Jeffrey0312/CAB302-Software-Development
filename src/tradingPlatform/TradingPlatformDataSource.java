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
     * @return information related to the organisational unit
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
     * @return set of all organisaiton names
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
     * changes the amount of an asset an organisation has
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
     * Extracts all info related to the named user
     *
     * @param username username of the user
     * @return an instance of user class with the information relating to the named user
     */
    User getUser(String username);

    /**
     * adds a new user to the database
     *
     * @param user the user to add
     */
    void addUser(User user);

    /**
     * deletes the user with the given name from the database
     *
     * @param username name of the user to delete
     */
    void deleteUser(String username);

    /**
     * gets all users
     * @return a set of all usernames
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

    /**
     * adds a new order to the database
     * @param order the new order to be added
     */
    void addOrder(Order order);

    /**
     * deletes a user from the database
     * @param orderId the orderId of the order that is to be deleted
     */
    void deleteOrder(int orderId);

    /**
     * update the amount of asset in an order
     * @param orderId the orderId of the order that is having the asset amount changed
     * @param assetAmount the new amount of assets
     */
    void updateOrderAssetAmount(int orderId, int assetAmount);

    /**
     * gets a set of all orders
     * @return returns a set of all orders
     */
    Set<Order> getOrderList();

    /**
     * adds a new transaction to the transaction table
     * @param transaction the new transaction that will be added
     */
    void addTransaction(Transaction transaction);

    /**
     * delete a transaction from the transaction table
     * @param transactionId the transactionId of the transaction being deleted
     */
    void deleteTransaction(int transactionId);

    /**
     * gets a set of all transactions
     * @return returns a set of all transactions
     */
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

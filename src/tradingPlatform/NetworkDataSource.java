package tradingPlatform;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class NetworkDataSource implements TradingPlatformDataSource{
    private static final String HOSTNAME = "127.0.0.1";
    private static final int PORT = 20000;

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public NetworkDataSource() {
        try {
            // Persist a single connection through the whole lifetime of the application.
            // We will re-use this same connection/socket, rather than repeatedly opening
            // and closing connections.
            socket = new Socket(HOSTNAME, PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            // If the server connection fails, we're going to throw exceptions
            // whenever the application actually tries to query anything.
            // But it wasn't written to handle this, so make sure your
            // server is running beforehand!
            System.out.println("Failed to connect to server");
        }
    }

    /**
     * Extracts all info related to the named organisation
     *
     * @param organisation name of the organisation
     * @return information related to the organisational unit
     */
    @Override
    public OrganisationalUnit getOrganisation(String organisation) {
        try {
            // tell the server to expect a person's name, and send us back their details
            outputStream.writeObject(Command.GET_ORGANISATION);
            outputStream.writeObject(organisation);

            // flush because if we don't, the request might not get sent yet, and we're waiting for a response
            outputStream.flush();

            // read the person's details back from the server
            return (OrganisationalUnit)inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * adds a new organisation to the database
     *
     * @param name name of organisation to add
     */
    @Override
    public void addOrganisation(String name) {
        if (name == null)
            throw new IllegalArgumentException("Organisation name cannot be null");

        try {
            // tell the server to expect a person's details
            outputStream.writeObject(Command.ADD_ORGANISATION);

            // send the actual data
            outputStream.writeObject(name);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes the organisation with the given name from the database
     *
     * @param organisation name of the organisation to delete
     */
    @Override
    public void deleteOrganisation(String organisation) {
        try {
            outputStream.writeObject(Command.DELETE_ORGANISATION);
            outputStream.writeObject(organisation);
            outputStream.flush();
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets all organisation
     * @return set of all organisaiton names
     */
    @Override
    public Set<String> getOrganisationsList() {
        try {
            outputStream.writeObject(Command.GET_ORGANISATIONS_LIST);
            outputStream.flush();
            return (Set<String>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new TreeSet<>();
    }

    /**
     * changes the amount of credits the provided organisation has to the provided number of credits
     *
     * @param name    the name of the organisation that is having its credits changed
     * @param credits the new value for credits
     */
    @Override
    public void setOrganisationCredits(String name, int credits) {
        if (name == null){
            throw new IllegalArgumentException("The Organisation name cannot be null");
        }
        if (credits < 0){
            throw new IllegalArgumentException("The number of Credits cannot be negative");
        }
        try{
            outputStream.writeObject(Command.SET_ORGANISATION_CREDITS);
            outputStream.writeObject(name);
            outputStream.writeObject(credits);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds a new asset to the database
     * @param asset the new asset being added
     */
    @Override
    public void addAsset(String asset) {
        if(asset == null){
            throw new IllegalArgumentException("The Asset name cannot be null");
        }
        try{
            outputStream.writeObject(Command.ADD_ASSET);
            outputStream.writeObject(asset);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * changes the amount of  an asset an organisation has
     * @param organisation name of the organisation that the change will happen to
     * @param asset        name of the asset the change will happen to
     * @param amount       the new amount that the amount of assets will be changed to
     */
    @Override
    public void setOrganisationAssetAmount(String organisation, String asset, int amount) {
        if (organisation == null){
            throw new IllegalArgumentException("The Organisation name cannot be null");
        }
        if (asset == null){
            throw new IllegalArgumentException("The Asset name cannot be null");
        }
        if (amount < 0){
            throw new IllegalArgumentException("The amount of an Asset cannot be negative");
        }
        try{
            outputStream.writeObject(Command.SET_ORGANISATION_ASSET_AMOUNT);
            outputStream.writeObject(organisation);
            outputStream.writeObject(asset);
            outputStream.writeObject(amount);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes the given asset from the database
     * @param name name of the asset that is to be deleted
     */
    @Override
    public void deleteAsset(String name) {
        if (name == null){
            throw new IllegalArgumentException("The Asset name cannot be null");
        }
        try{
            outputStream.writeObject(Command.DELETE_ASSET);
            outputStream.writeObject(name);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts all info related to the named user
     *
     * @param username name of the organisation
     * @return return the user with the input username
     */
    @Override
    public User getUser(String username) {
        try {
            // tell the server to expect a person's name, and send us back their details
            outputStream.writeObject(Command.GET_USER);
            outputStream.writeObject(username);

            // flush because if we don't, the request might not get sent yet, and we're waiting for a response
            outputStream.flush();

            // read the person's details back from the server
            return (User)inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * adds a new user to the database
     *
     * @param user the user to add
     */
    @Override
    public void addUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("The User cannot be null");
        if (user.getUsername() == null)
            throw new IllegalArgumentException("The User username cannot be null");
        if (user.getPassword() == null)
            throw new IllegalArgumentException("The User password cannot be null");
        if (user.getFirstname() == null)
            throw new IllegalArgumentException("The User first cannot be null");
        if (user.getLastname() == null)
            throw new IllegalArgumentException("The User lastname cannot be null");

        try {
            // tell the server to expect a person's details
            outputStream.writeObject(Command.ADD_USER);

            // send the actual data
            outputStream.writeObject(user);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the organisation of the user
     * @param username the username of the user being changed
     * @param organisation the new organisation
     */
    @Override
    public void setUserOrganisation(String username, String organisation) {
        if (username == null){
            throw new IllegalArgumentException("The username cannot be null");
        }
        if (organisation== null){
            throw new IllegalArgumentException("The organisation name cannot be null");
        }
        try{
            outputStream.writeObject(Command.SET_USER_ORGANISATION);
            outputStream.writeObject(username);
            outputStream.writeObject(organisation);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the password of the user
     * @param username the username of the user being changed
     * @param password the new password
     */
    @Override
    public void setUserPassword(String username, String password) {
        if (username == null){
            throw new IllegalArgumentException("The username cannot be null");
        }
        if (password == null){
            throw new IllegalArgumentException("The organisation name cannot be null");
        }
        try{
            outputStream.writeObject(Command.SET_USER_PASSWORD);
            outputStream.writeObject(username);
            outputStream.writeObject(Hash.SHA512(password));
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes the user with the given name from the database
     *
     * @param username name of the user to delete
     */
    @Override
    public void deleteUser(String username) {
        try {
            outputStream.writeObject(Command.DELETE_USER);
            outputStream.writeObject(username);
            outputStream.flush();
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets all users
     * @return a set of all usernames
     */
    @Override
    public Set<String> getUsersList() {
        try {
            outputStream.writeObject(Command.GET_USERS_LIST);
            outputStream.flush();
            return (Set<String>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    /**
     * used to attempt to login to the system
     *
     * @param username username of the user attempting to login
     * @param password password of the user attempting to login
     * @return returns an instance of the user class with the users info if inputs are correct other wise returns null
     */
    @Override
    public User login(String username, String password) {
        if (username == null){
            throw new IllegalArgumentException("The Username cannot be null");
        }
        if (password == null){
            throw new IllegalArgumentException("The password name cannot be null");
        }
        try{
            outputStream.writeObject(Command.LOGIN);
            outputStream.writeObject(username);
            outputStream.writeObject(password);
            outputStream.flush();
            return (User) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * adds a new order to the database
     * @param order the new order to be added
     */
    @Override
    public void addOrder(Order order) {
        if (order.getAsset() == null){
            throw new IllegalArgumentException("The Asset cannot be null");
        }
        if (order.getOrganisation() == null){
            throw new IllegalArgumentException("The organisation cannot be null");
        }
        if (order.getAssetAmount() < 0){
            throw new IllegalArgumentException("The Asset amount cannot be negative");
        }
        if (order.getValue() < 0){
            throw new IllegalArgumentException("The Value cannot be negative");
        }
        try{
            outputStream.writeObject(Command.ADD_ORDER);
            outputStream.writeObject(order);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes a user from the database
     * @param orderId the orderId of the order that is to be deleted
     */
    @Override
    public void deleteOrder(int orderId) {
        if (orderId < 0){
            throw new IllegalArgumentException("The order Id cannot be negative");
        }
        try {
            outputStream.writeObject(Command.DELETE_ORDER);
            outputStream.writeObject(orderId);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * update the amount of asset in an order
     * @param orderId the orderId of the order that is having the asset amount changed
     * @param assetAmount the new amount of assets
     */
    @Override
    public void updateOrderAssetAmount(int orderId, int assetAmount) {
        if (orderId < 0){
            throw new IllegalArgumentException("The order Id cannot be negative");
        }
        if (assetAmount < 0){
            throw new IllegalArgumentException("The asset amount cannot be negative");
        }
        try {
            outputStream.writeObject(Command.UPDATE_ORDER_ASSET_AMOUNT);
            outputStream.writeObject(orderId);
            outputStream.writeObject(assetAmount);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets a set of all orders
     * @return returns a set of all orders
     */
    @Override
    public Set<Order> getOrderList() {
        try {
            outputStream.writeObject(Command.GET_ORDERS_LIST);
            outputStream.flush();
            return (Set<Order>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    /**
     * adds a new transaction to the transaction table
     * @param transaction the new transaction that will be added
     */
    @Override
    public void addTransaction(Transaction transaction) {
        if (transaction.getBuyer() == null){
            throw new IllegalArgumentException("The Buyer cannot be null");
        }
        if (transaction.getSeller() == null){
            throw new IllegalArgumentException("The Seller cannot be null");
        }
        if (transaction.getAsset() == null){
            throw new IllegalArgumentException("The Asset cannot be null");
        }
        if (transaction.getAssetAmount() < 0){
            throw new IllegalArgumentException("The Asset amount cannot be negative");
        }
        if (transaction.getValue() < 0){
            throw new IllegalArgumentException("The Value cannot be negative");
        }
        try{
            outputStream.writeObject(Command.ADD_TRANSACTION);
            outputStream.writeObject(transaction);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * delete a transaction from the transaction table
     * @param transactionId the transactionId of the transaction being deleted
     */
    @Override
    public void deleteTransaction(int transactionId) {
        if (transactionId < 0){
            throw new IllegalArgumentException("The transaction Id cannot be negative");
        }
        try {
            outputStream.writeObject(Command.DELETE_TRANSACTION);
            outputStream.writeObject(transactionId);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets a set of all transactions
     * @return returns a set of all transactions
     */
    @Override
    public Set<Transaction> getTransactionsList() {
        try {
            outputStream.writeObject(Command.GET_TRANSACTIONS_LIST);
            outputStream.flush();
            return (Set<Transaction>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    /**
     * Gets the number of user in the user list.
     * @return size of user list.
     */
    @Override
    public int getUserSize() {
        try {
            outputStream.writeObject(Command.GET_USERS_SIZE);
            outputStream.flush();
            // read the organisation's details back from the server
            return inputStream.readInt();
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisted.
     */
    @Override
    public void close() {
    }
}

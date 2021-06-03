package tradingPlatform;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class NetworkDataSource implements TradingPlatformDataSource{
    private static final String HOSTNAME = "127.0.0.1";
    private static final int PORT = 10000;

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

    @Override
    public void addAsset(String asset) {

    }

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

    @Override
    public User getUser(String user) {
        try {
            // tell the server to expect a person's name, and send us back their details
            outputStream.writeObject(Command.GET_USER);
            outputStream.writeObject(user);

            // flush because if we don't, the request might not get sent yet, and we're waiting for a response
            outputStream.flush();

            // read the person's details back from the server
            return (User)inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(User u) {
        if (u == null)
            throw new IllegalArgumentException("The User cannot be null");
        if (u.getUsername() == null)
            throw new IllegalArgumentException("The User username cannot be null");
        if (u.getPassword() == null)
            throw new IllegalArgumentException("The User password cannot be null");
        if (u.getFirstname() == null)
            throw new IllegalArgumentException("The User first cannot be null");
        if (u.getLastname() == null)
            throw new IllegalArgumentException("The User lastname cannot be null");

        try {
            // tell the server to expect a person's details
            outputStream.writeObject(Command.ADD_USER);

            // send the actual data
            outputStream.writeObject(u);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public void deleteUser(String user) {
        try {
            outputStream.writeObject(Command.DELETE_USER);
            outputStream.writeObject(user);
            outputStream.flush();
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public void addOrder(Order order) {

    }

    @Override
    public void deleteOrder(int orderId) {

    }

    @Override
    public void updateOrderAssetAmount(int orderId, int assetAmount) {

    }

    @Override
    public Set<Order> getOrderList() {
        return null;
    }

    @Override
    public void addTransaction(Transaction transaction) {

    }

    @Override
    public void deleteTransaction(int transactionId) {

    }

    @Override
    public Set<Transaction> getTransactionsList() {
        return null;
    }

    @Override
    public int getUserSize() {
        try {
            outputStream.writeObject(Command.GET_SIZE);
            outputStream.flush();
            // read the organisation's details back from the server
            return inputStream.readInt();
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void close() {
    }
}

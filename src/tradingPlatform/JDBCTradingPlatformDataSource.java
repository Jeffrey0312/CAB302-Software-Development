package tradingPlatform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Class for retrieving data from the XML file holding the Trading Platform data
 */
public class JDBCTradingPlatformDataSource implements TradingPlatformDataSource{

    public static final String CREATE_TABLE_ORGANISATIONS =
            "CREATE TABLE IF NOT EXISTS organisations ("
                    + "organisation VARCHAR(32) NOT NULL,"
                    + "credits INTEGER DEFAULT 0,"
                    + "PRIMARY KEY (organisation)"
                    + "CONSTRAINT CHK_credit_amount CHECK (credits >= 0)"
                    + ");";

    private static final String INSERT_ORGANISATION = "INSERT INTO organisations (organisation, credits) VALUES (?, ?);";
    private static final String GET_ORGANISATIONS_LIST = "SELECT organisation FROM organisations";
    private static final String GET_ORGANISATION = "SELECT * FROM organisations WHERE organisation=?";
    private static final String DELETE_ORGANISATION = "DELETE FROM organisations WHERE organisation=?";
    private static final String UPDATE_ORGANISATION_CREDITS = "UPDATE organisations SET credits = ? WHERE organisation = ?";

    private PreparedStatement addOrganisation;
    private PreparedStatement getOrganisationsList;
    private PreparedStatement getOrganisation;
    private PreparedStatement deleteOrganisation;
    private PreparedStatement setOrganisationCredits;

    public static final String CREATE_TABLE_ASSETS =
            "CREATE TABLE IF NOT EXISTS assets ("
                    + "organisation VARCHAR(32) NOT NULL,"
                    + "asset VARCHAR(32) NOT NULL,"
                    + "asset_amount INTEGER DEFAULT 0,"
                    + "PRIMARY KEY (organisation, asset),"
                    + "CONSTRAINT CHK_asset_amount CHECK (asset_amount >= 0),"
                    + "CONSTRAINT FK_organisation FOREIGN KEY (organisation) "
                    + "REFERENCES organisations(organisation)"
                    + "ON DELETE CASCADE"
                    + ");";

    private static final String INSERT_ORGANISATION_ASSET = "INSERT INTO assets (organisation, asset) VALUES (?, ?)";
    private static final String GET_ASSETS = "SELECT DISTINCT asset FROM assets";
    private static final String GET_ORGANISATION_ASSETS = "SELECT asset, asset_amount FROM assets WHERE organisation =?";
    private static final String UPDATE_ORGANISATION_ASSET_AMOUNT = "UPDATE assets SET asset_amount = ? WHERE organisation = ? AND asset = ?";
    private static final String DELETE_ASSET = "DELETE FROM assets WHERE asset = ?";

    private PreparedStatement addOrganisationAsset;
    private PreparedStatement getAssets;
    private PreparedStatement getOrganisationAssetList;
    private PreparedStatement setOrganisationAssetAmount;
    private PreparedStatement deleteAsset;

    public static final String CREATE_TABLE_USERS =
            "CREATE TABLE IF NOT EXISTS users ("
                    + "username VARCHAR(32) NOT NULL,"
                    + "password VARCHAR(32) NOT NULL,"
                    + "firstname VARCHAR(32) NOT NULL,"
                    + "lastname VARCHAR(32) NOT NULL,"
                    + "salt INTEGER NOT NULL /*!40101 AUTO_INCREMENT */,"
                    + "organisation VARCHAR(32) DEFAULT NULL,"
                    + "ituser BOOL NOT NULL,"
                    + "PRIMARY KEY (username),"
                    + "CONSTRAINT CHK_organisation_ituser CHECK ((ituser = 1 AND organisation IS NULL) OR (ituser = 0 AND organisation IS NOT NULL)),"
                    + "CONSTRAINT FK_organisation FOREIGN KEY (organisation) "
                    + "REFERENCES organisations(organisation)"
                    + "ON DELETE CASCADE"
                    + ");";

    private static final String GET_USER = "SELECT * FROM users WHERE username = ?";
    private static final String INSERT_USER = "INSERT INTO users (username, password, firstname, lastname, salt, organisation, ituser) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE username = ?";
    private static final String UPDATE_USER_ORGANISATION = "UPDATE users SET organisation = ? WHERE username = ?";
    private static final String UPDATE_USER_PASSWORD = "UPDATE users SET password = ? WHERE username = ?";
    private static final String GET_USERS_LIST = "SELECT username FROM users";
    private static final String GET_MAX_SALT = "SELECT MAX(salt) FROM users";
    private static final String USER_ROW_COUNT = "SELECT COUNT(*) FROM users";

    private PreparedStatement getUser;
    private PreparedStatement addUser;
    private PreparedStatement deleteUser;
    private PreparedStatement setUserOrganisation;
    private PreparedStatement setUserPassword;
    private PreparedStatement getUsersList;
    private PreparedStatement getMaxSalt;
    private PreparedStatement userRowCount;

    public static final String CREATE_TABLE_ORDERS =
            "CREATE TABLE IF NOT EXISTS orders ("
                    + "order_id INTEGER NOT NULL /*!40101 AUTO_INCREMENT */,"
                    + "isbuy BOOL NOT NULL,"
                    + "organisation VARCHAR(32) NOT NULL,"
                    + "asset VARCHAR(32) NOT NULL,"
                    + "asset_amount INTEGER NOT NULL,"
                    + "value INTEGER NOT NULL,"
                    + "PRIMARY KEY (order_id),"
                    + "CONSTRAINT CHK_asset_amount CHECK (asset_amount > 0),"
                    + "CONSTRAINT CHK_asset_amount CHECK (value > 0),"
                    + "CONSTRAINT FK_organisation_assets FOREIGN KEY (organisation, asset)"
                    + "REFERENCES assets(organisation, asset) "
                    + "ON DELETE CASCADE"
                    + ");";

    private static final String ADD_ORDER = "INSERT INTO orders (order_id, isbuy, organisation, asset, asset_amount, value) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE order_id = ?";
    private static final String UPDATE_ORDER_ASSET_AMOUNT = "UPDATE orders SET asset_amount = ? WHERE order_id = ?";
    private static final String GET_ORDERS_LIST = "SELECT * FROM orders";
    private static final String GET_MAX_ORDER_ID = "SELECT MAX(order_id) FROM orders";

    private PreparedStatement addOrder;
    private PreparedStatement deleteOrder;
    private PreparedStatement updateOrderAssetAmount;
    private PreparedStatement getOrdersList;
    private PreparedStatement getMaxOrderId;

    public static final String CREATE_TABLE_TRANSACTIONS =
            "CREATE TABLE IF NOT EXISTS transactions ("
                    + "transaction_id INTEGER NOT NULL /*!40101 AUTO_INCREMENT */,"
                    + "transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "buyer VARCHAR(32) NOT NULL,"
                    + "seller VARCHAR(32) NOT NULL,"
                    + "asset VARCHAR(32) NOT NULL,"
                    + "asset_amount INTEGER NOT NULL,"
                    + "value INTEGER NOT NULL,"
                    + "CONSTRAINT CHK_asset_amount CHECK (asset_amount > 0),"
                    + "CONSTRAINT CHK_asset_amount CHECK (value > 0),"
                    + "CONSTRAINT FK_organisation_assets FOREIGN KEY (buyer, asset) "
                    + "REFERENCES assets(organisation, asset) "
                    + "ON DELETE CASCADE,"
                    + "CONSTRAINT FK_organisation_assets FOREIGN KEY (seller, asset) "
                    + "REFERENCES assets(organisation, asset) "
                    + "ON DELETE CASCADE"
                    + ");";

    private static final String ADD_TRANSACTION = "INSERT INTO transactions (transaction_id, buyer, seller, asset, asset_amount, value) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_TRANSACTION = "DELETE FROM transactions WHERE transaction_id = ?";
    private static final String GET_TRANSACTIONS_LIST = "SELECT * FROM transactions ORDER BY transaction_time DESC";
    private static final String GET_MAX_TRANSACTION_ID = "SELECT MAX(transaction_id) FROM transactions";

    private PreparedStatement addTransaction;
    private PreparedStatement deleteTransaction;
    private PreparedStatement getTransactionsList;
    private PreparedStatement getMaxTransactionId;

    private Connection connection;

    public JDBCTradingPlatformDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE_ORGANISATIONS);
            st.execute(CREATE_TABLE_ASSETS);
            st.execute(CREATE_TABLE_USERS);
            st.execute(CREATE_TABLE_ORDERS);
            st.execute(CREATE_TABLE_TRANSACTIONS);

            addOrganisation = connection.prepareStatement(INSERT_ORGANISATION);
            getOrganisationsList = connection.prepareStatement(GET_ORGANISATIONS_LIST);
            getOrganisation = connection.prepareStatement(GET_ORGANISATION);
            deleteOrganisation = connection.prepareStatement(DELETE_ORGANISATION);
            setOrganisationCredits = connection.prepareStatement(UPDATE_ORGANISATION_CREDITS);

            addOrganisationAsset = connection.prepareStatement(INSERT_ORGANISATION_ASSET);
            getAssets = connection.prepareStatement(GET_ASSETS);
            getOrganisationAssetList = connection.prepareStatement(GET_ORGANISATION_ASSETS);
            setOrganisationAssetAmount = connection.prepareStatement(UPDATE_ORGANISATION_ASSET_AMOUNT);
            deleteAsset = connection.prepareStatement(DELETE_ASSET);

            getUser = connection.prepareStatement(GET_USER);
            addUser = connection.prepareStatement(INSERT_USER);
            deleteUser = connection.prepareStatement(DELETE_USER);
            setUserOrganisation = connection.prepareStatement(UPDATE_USER_ORGANISATION);
            setUserPassword = connection.prepareStatement(UPDATE_USER_PASSWORD);
            getUsersList = connection.prepareStatement(GET_USERS_LIST);
            getMaxSalt = connection.prepareStatement(GET_MAX_SALT);
            userRowCount = connection.prepareStatement(USER_ROW_COUNT);

            addOrder = connection.prepareStatement(ADD_ORDER);
            deleteOrder = connection.prepareStatement(DELETE_ORDER);
            updateOrderAssetAmount = connection.prepareStatement(UPDATE_ORDER_ASSET_AMOUNT);
            getOrdersList = connection.prepareStatement(GET_ORDERS_LIST);
            getMaxOrderId = connection.prepareStatement(GET_MAX_ORDER_ID);

            addTransaction = connection.prepareStatement(ADD_TRANSACTION);
            deleteTransaction = connection.prepareStatement(DELETE_TRANSACTION);
            getTransactionsList = connection.prepareStatement(GET_TRANSACTIONS_LIST);
            getMaxTransactionId = connection.prepareStatement(GET_MAX_TRANSACTION_ID);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getUserSize() {
        ResultSet rs = null;
        int rows = 0;
        try {
            rs = userRowCount.executeQuery();
            rs.next();
            rows = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rows;
    }

    /**
     * @param name name of the organisation
     * @return information related to the organisational unit
     */
    @Override
    public OrganisationalUnit getOrganisation(String name) {
        OrganisationalUnit org = new OrganisationalUnit();
        ResultSet rsOrg;
        ResultSet rsAssets;
        try{
            getOrganisation.setString(1, name);
            rsOrg = getOrganisation.executeQuery();
            rsOrg.next();
            org.setName(rsOrg.getString("organisation"));
            org.setCredits(rsOrg.getInt("credits"));
            getOrganisationAssetList.setString(1,name);
            rsAssets = getOrganisationAssetList.executeQuery();
            HashMap<String,Integer> Assets = new HashMap<>();
            while (rsAssets.next()){
                Assets.put(rsAssets.getString("asset"), rsAssets.getInt("asset_amount"));
            }
            org.setAssets(Assets);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return org;
    }

    /**
     * @param name name of organisation to add
     */
    @Override
    public void addOrganisation(String name) {
        ResultSet rs;
        try{
            addOrganisation.setString(1,name);
            addOrganisation.setString(2,"0");
            addOrganisation.execute();
            rs = getAssets.executeQuery();
            while (rs.next()) {
                addOrganisationAsset.setString(1, name);
                addOrganisationAsset.setString(2, rs.getString("asset"));
                addOrganisationAsset.setString(3, "0");
                addOrganisationAsset.execute();
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * @param name name of the organisation to delete
     */
    @Override
    public void deleteOrganisation(String name) {
        try{
            deleteOrganisation.setString(1,name);
            deleteOrganisation.execute();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Set<String> getOrganisationsList() {
        Set<String> orgs = new TreeSet<>();
        ResultSet rs;
        try{
            rs = getOrganisationsList.executeQuery();
            while (rs.next()){
                orgs.add(rs.getString("organisation"));
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return orgs;
    }

    /**
     * @param name    the name of the organisation that is having its credits changed
     * @param credits the new value for credits
     */
    @Override
    public void setOrganisationCredits(String name, int credits) {
        try{
            setOrganisationCredits.setString(1,name);
            setOrganisationCredits.setString(2,String.valueOf(credits));
            setOrganisationCredits.execute();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void addAsset(String asset){
        ResultSet rs;
        try{
            rs = getOrganisationsList.executeQuery();
            while (rs.next()) {
                addOrganisationAsset.setString(1,rs.getString("organisation"));
                addOrganisationAsset.setString(2,asset);
                addOrganisationAsset.execute();
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * @param organisation name of the organisation that the change will happen to
     * @param asset        name of the asset the change will happen to
     * @param amount       the new amount that the amount of assets will be changed to
     */
    @Override
    public void setOrganisationAssetAmount(String organisation, String asset, int amount) {
        try{
            setOrganisationAssetAmount.setString(1,String.valueOf(amount));
            setOrganisationAssetAmount.setString(2,organisation);
            setOrganisationAssetAmount.setString(3,asset);
            setOrganisationAssetAmount.execute();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * @param name name of the asset that is to be deleted
     */
    @Override
    public void deleteAsset(String name) {
        try{
            deleteAsset.setString(1,name);
            deleteAsset.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param name name of the organisation
     * @return return the user with the input username
     */
    @Override
    public User getUser(String name) {
        User user = new User();
        ResultSet rsUser;
        try{
            getUser.setString(1, name);
            rsUser = getUser.executeQuery();
            rsUser.next();
            user.setUsername(rsUser.getString("username"));
            user.setFirstname(rsUser.getString("firstname"));
            user.setLastname(rsUser.getString("lastname"));
            user.setPassword(rsUser.getString("password"));
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        ResultSet rs;
        int salt;
        try{
            if(user instanceof ClientUser){
                if (((ClientUser) user).getOrganisation().getName() != null){
                    addUser.setString(6,((ClientUser) user).getOrganisation().getName());
                } else {
                    addUser.setString(6,"NULL");
                }
                addUser.setString(7,"0");
            } else if (user instanceof ITUser){
                addUser.setString(6,"NULL");
                addUser.setString(7,"1");
            } else {
                throw new IllegalArgumentException();
            }
            rs = getMaxSalt.executeQuery();
            rs.next();
            salt = rs.getInt(1);
            salt = salt+1;
            addUser.setString(1,user.getUsername());
            String password = Hash.SHA512(user.getPassword() + salt);
            addUser.setString(2, password);
            addUser.setString(3, user.getFirstname());
            addUser.setString(4, user.getLastname());
            addUser.setString(5,String.valueOf(salt));
            addUser.execute();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String username) {
        try{
            deleteUser.setString(1,username);
            deleteUser.execute();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void setUserOrganisation(String username, String organisation) {
        try{
            setUserOrganisation.setString(1,organisation);
            setOrganisationAssetAmount.setString(2,username);
            setOrganisationAssetAmount.execute();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void setUserPassword(String username, String password) {
        try{
            getUser.setString(1,username);
            ResultSet rs = getUser.executeQuery();
            rs.next();
            password = Hash.SHA512(password + rs.getString("salt"));
            setUserPassword.setString(1,password);
            setUserPassword.setString(2,username);
            setUserPassword.execute();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Set<String> getUsersList() {
        Set<String> users = new TreeSet<>();
        ResultSet rs;
        try{
            rs = getUsersList.executeQuery();
            while (rs.next()){
                users.add(rs.getString("username"));
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public User login(String username, String password) {
        ResultSet rsUser;
        try{
            getUser.setString(1,username);
            rsUser = getUser.executeQuery();
            rsUser.next();
            if(Hash.SHA512(password + rsUser.getString("Salt")).equals(rsUser.getString("password"))){
                if(rsUser.getString("ituser").equals("1")){
                    return new ITUser(username, password, rsUser.getString("firstname"),rsUser.getString("lastname"));
                }else{
                    OrganisationalUnit org;
                    if (rsUser.getString("organisation").equals("NULL")){
                        org = null;
                    } else{
                        org = new OrganisationalUnit();
                        ResultSet rsOrg;
                        ResultSet rsAssets;
                        getOrganisation.setString(1, rsUser.getString("organisation"));
                        rsOrg = getOrganisation.executeQuery();
                        rsOrg.next();
                        org.setName(rsOrg.getString("organisation"));
                        org.setCredits(rsOrg.getInt("credits"));
                        getOrganisationAssetList.setString(1,rsUser.getString("organisation"));
                        rsAssets = getOrganisationAssetList.executeQuery();
                        HashMap<String,Integer> Assets = new HashMap<>();
                        while (rsAssets.next()){
                            Assets.put(rsAssets.getString("asset"), rsAssets.getInt("asset_amount"));
                        }
                        org.setAssets(Assets);
                    }
                    return new ClientUser(username,password, rsUser.getString("firstname"),rsUser.getString("lastname"),org);
                }
            }else{
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void addOrder(Order order) {
        ResultSet rs;
        try{
            rs = getMaxOrderId.executeQuery();
            rs.next();
            int orderId = rs.getInt(1) + 1;
            addOrder.setString(1,String.valueOf(orderId));
            if(order.getIsBuy()){
                addOrder.setString(2,"1");
            } else {
                addOrder.setString(2,"0");
            }
            addOrder.setString(3,order.getOrganisation());
            addOrder.setString(4,order.getAsset());
            addOrder.setString(5,String.valueOf(order.getAssetAmount()));
            addOrder.setString(6,String.valueOf(order.getValue()));
            addOrder.execute();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try{
            deleteOrder.setString(1,String.valueOf(orderId));
            deleteOrder.execute();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateOrderAssetAmount(int orderId, int assetAmount) {
        try {
            updateOrderAssetAmount.setString(1, String.valueOf(assetAmount));
            updateOrderAssetAmount.setString(2, String.valueOf(orderId));
            updateOrderAssetAmount.execute();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Set<Order> getOrderList() {
        Set<Order> orders = new TreeSet<>();
        ResultSet rs;
        try{
            rs = getOrdersList.executeQuery();
            while (rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setIsBuy(rs.getBoolean("isbuy"));
                order.setOrganisation(rs.getString("organisation"));
                order.setAsset(rs.getString("asset"));
                order.setAssetAmount(rs.getInt("asset_amount"));
                order.setValue(rs.getInt("value"));
                orders.add(order);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return orders;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        ResultSet rs;
        try{
            rs = getMaxTransactionId.executeQuery();
            rs.next();
            int transactionId = rs.getInt(1) + 1;
            addTransaction.setString(1,String.valueOf(transactionId));
            addTransaction.setString(2,transaction.getBuyer());
            addTransaction.setString(3,transaction.getSeller());
            addTransaction.setString(4,transaction.getAsset());
            addTransaction.setString(5,String.valueOf(transaction.getAssetAmount()));
            addTransaction.setString(6,String.valueOf(transaction.getValue()));
            addTransaction.execute();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteTransaction(int transactionId) {
        try {
            deleteTransaction.setString(1,String.valueOf(transactionId));
            deleteTransaction.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Set<Transaction> getTransactionsList() {
        Set<Transaction> transactions = new TreeSet<>();
        ResultSet rs;
        try{
            rs = getTransactionsList.executeQuery();
            while (rs.next()){
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setDateTime(rs.getString("transaction_time"));
                transaction.setBuyer(rs.getString("buyer"));
                transaction.setSeller(rs.getString("seller"));
                transaction.setAsset(rs.getString("asset"));
                transaction.setAssetAmount(rs.getInt("asset_amount"));
                transaction.setValue(rs.getInt("value"));
                transactions.add(transaction);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

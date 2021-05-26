package tradingPlatform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

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
    private static final String GET_ORGANISATIONS_List = "SELECT organisation FROM organisations";
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

    private static final String INSERT_ORGANISATION_ASSET = "INSERT INTO assets (organisation, asset, asset_amount) VALUES (?, ?, ?);";
    private static final String GET_ASSETS = "SELECT DISTINCT asset FROM assets";
    private static final String GET_ORGANISATION_ASSETS = "SELECT asset, asset_amount FROM assets WHERE organisation =?";
    private static final String UPDATE_ORGANISATION_ASSET_AMOUNT = "UPDATE assets SET asset_amount = ? WHERE organisation = ? AND asset = ?";

    private PreparedStatement addOrganisationAsset;
    private PreparedStatement getAssets;
    private PreparedStatement getOrganisationAssetList;
    private PreparedStatement setOrganisationAssetAmount;


    public static final String CREATE_TABLE_USERS =
            "CREATE TABLE IF NOT EXISTS users ("
                    + "username VARCHAR(32) NOT NULL,"
                    + "password VARCHAR(32) NOT NULL,"
                    + "salt INTEGER NOT NULL /*!40101 AUTO_INCREMENT */,"
                    + "organisation VARCHAR(32) DEFAULT NULL,"
                    + "ituser BOOL NOT NULL,"
                    + "PRIMARY KEY (username),"
                    + "CONSTRAINT CHK_organisation_ituser CHECK ((ituser = 1 AND organisation IS NULL) OR (ituser = 0 AND organisation IS NOT NULL)),"
                    + "CONSTRAINT FK_organisation FOREIGN KEY (organisation) "
                    + "REFERENCES organisations(organisation)"
                    + "ON DELETE CASCADE"
                    + ");";

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
            getOrganisationsList = connection.prepareStatement(GET_ORGANISATIONS_List);
            getOrganisation = connection.prepareStatement(GET_ORGANISATION);
            deleteOrganisation = connection.prepareStatement(DELETE_ORGANISATION);
            setOrganisationCredits = connection.prepareStatement(UPDATE_ORGANISATION_CREDITS);

            addOrganisationAsset = connection.prepareStatement(INSERT_ORGANISATION_ASSET);
            getAssets = connection.prepareStatement(GET_ASSETS);
            getOrganisationAssetList = connection.prepareStatement(GET_ORGANISATION_ASSETS);
            setOrganisationAssetAmount = connection.prepareStatement(UPDATE_ORGANISATION_ASSET_AMOUNT);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public OrganisationalUnit getOrganisation(String name) {
        OrganisationalUnit org = new OrganisationalUnit();
        ResultSet rsOrg;
        ResultSet rsAssets;
        try{
            getOrganisation.setString(1, name);
            rsOrg = getOrganisation.executeQuery();
            org.setName(rsOrg.getString("organisation"));
            org.setCredits(Integer.parseInt(rsOrg.getString("credits")));
            getOrganisationAssetList.setString(1,name);
            rsAssets = getOrganisationAssetList.executeQuery();
            HashMap<String,Integer> Assets = new HashMap<>();
            while (rsAssets.next()){
                Assets.put(rsAssets.getString("asset"),
                           Integer.parseInt(rsAssets.getString("asset_amount")));
            }
            org.setAssets(Assets);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return org;
    }

    @Override
    public void addOrganisation(String name) {
        ResultSet rs;
        try{
            addOrganisation.setString(1,name);
            addOrganisation.setString(2,"0");
            addOrganisation.execute();
            rs = getAssets.executeQuery();
            while (rs.next()){
                addOrganisationAsset.setString(1,name);
                addOrganisationAsset.setString(2,rs.getString("asset"));
                addOrganisationAsset.setString(3,"0");
                addOrganisationAsset.execute();
            }

        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

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
}

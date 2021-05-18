package tradingPlatform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class for retrieving data from the XML file holding the Trading Platform data
 */
public class JDBCTradingPlatformDataSource implements TradingPlatformDataSource{

    public static final String CREATE_TABLE_ORGANISATIONS =
            "CREATE TABLE IF NOT EXISTS organisations ("
                    + "organisation VARCHAR(30) NOT NULL,"
                    + "credits INTEGER NOT NULL,"
                    + "PRIMARY KEY (organisation)"
                    + ");";

    private static final String INSERT_ORGANISATION = "INSERT INTO organisations (organisation, credits) VALUES (?, ?);";
    private static final String GET_ORGANISATIONS = "SELECT organisation FROM organisations";
    private static final String GET_ORGANISATION_INFO = "SELECT * FROM organisations WHERE organisation=?";
    private static final String DELETE_ORGANISATION = "DELETE FROM organisations WHERE organisation=?";

    private PreparedStatement AddOrganisation;
    /*
    private PreparedStatement GetOrganisationsList;
    private PreparedStatement GetOrganisation;
    private PreparedStatement DeleteOrganisation
    */

    public static final String CREATE_TABLE_ASSETS =
            "CREATE TABLE IF NOT EXISTS assets ("
                    + "organisation VARCHAR(30) NOT NULL,"
                    + "asset VARCHAR(30) NOT NULL,"
                    + "asset_amount INTEGER NOT NULL,"
                    + "PRIMARY KEY (organisation, asset),"
                    + "CONSTRAINT CHK_asset_amount CHECK (asset_amount >= 0),"
                    + "CONSTRAINT FK_organisation FOREIGN KEY (organisation) "
                    + "REFERENCES organisations(organisation)"
                    + "ON DELETE CASCADE"
                    + ");";

    //private static final String INSERT_ORGANISATION_ASSET = "INSERT INTO assets (organisation, asset, credits) VALUES (?, ?, ?);";
    //private static final String GET_ASSETS = "SELECT DISTINCT asset FROM assets";
    //private static final String GET_ORGANISATION_ASSETS = "SELECT asset, asset_amount FROM assets WHERE organisation =?";


    public static final String CREATE_TABLE_USERS =
            "CREATE TABLE IF NOT EXISTS users ("
                    + "username VARCHAR(30) NOT NULL UNIQUE,"
                    + "password VARCHAR(30) NOT NULL,"
                    + "salt INTEGER NOT NULL /*!40101 AUTO_INCREMENT */,"
                    + "organisation VARCHAR(30),"
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
                    + "organisation VARCHAR(30) NOT NULL,"
                    + "asset VARCHAR(30) NOT NULL,"
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
                    + "buyer VARCHAR(30) NOT NULL,"
                    + "seller VARCHAR(30) NOT NULL,"
                    + "asset VARCHAR(30) NOT NULL,"
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
           // AddOrganisation = connection.prepareStatement(INSERT_ORGANISATION);
           // GetOrganisationsList = connection.prepareStatement(GET_ORGANISATIONS);
           // GetOrganisation = connection.prepareStatement(GET_ORGANISATION_INFO);
           // DeleteOrganisation = connection.prepareStatement(DELETE_ORGANISATION);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

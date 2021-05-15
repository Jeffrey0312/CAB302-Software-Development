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
                    + "Credits INTEGER NOT NULL,"
                    + "PRIMARY KEY (organisation)"
                    + ");";

    public static final String CREATE_TABLE_ASSETS =
            "CREATE TABLE IF NOT EXISTS assets"
                    + "organisation VARCHAR(30) NOT NULL,"
                    + "asset VARCHAR(30) NOT NULL,"
                    + "asset_amount INTEGER NOT NULL,"
                    + "PRIMARY KEY (organisation, asset),"
                    + "CONSTRAINT CHK_asset_amount CHECK (asset_amount >= 0)"
                    + "CONSTRAINT FK_organisation FOREIGN KEY (organisation) "
                    + "REFERENCES organisations(organisation)"
                    + "ON DELETE CASCADE"
                    + ");";

    public static final String CREATE_TABLE_USERS =
            "CREATE TABLE IF NOT EXISTS users ("
                    + "username VARCHAR(30) NOT NULL UNIQUE,"
                    + "password VARCHAR(30) NOT NULL,"
                    + "salt INTEGER NOT NULL AUTO_INCREMENT,"
                    + "organisation VARCHAR(30),"
                    + "ituser BOOL NOT NULL,"
                    + "PRIMARY KEY (username),"
                    + "CONSTRAINT CHK_organisation_ituser CHECK ((ituser = 1 AND organisation IS NULL) OR (ituser = 0 AND organisation IS NOT NULL),"
                    + "CONSTRAINT FK_organisation FOREIGN KEY (organisation) "
                    + "REFERENCES organisations(organisation)"
                    + "ON DELETE CASCADE"
                    + ");";

    public static final String CREATE_TABLE_ORDERS =
            "CREATE TABLE IF NOT EXISTS orders ("
                    + "order_id INTEGER NOT NULL AUTO_INCREMENT,"
                    + "isbuy BOOL NOT NULL,"
                    + "organisation VARCHAR(30) NOT NULL,"
                    + "asset VARCHAR(30) NOT NULL,"
                    + "asset_amount INTEGER NOT NULL,"
                    + "value INTEGER NOT NULL,"
                    + "PRIMARY KEY (order_id)"
                    + "CONSTRAINT CHK_asset_amount CHECK (asset_amount > 0)"
                    + "CONSTRAINT CHK_asset_amount CHECK (value > 0)"
                    + "CONSTRAINT FK_organisation_assets FOREIGN KEY (organisation, asset) "
                    + "REFERENCES assets(organisation, asset)"
                    + "ON DELETE CASCADE"
                    + ");";

    public static final String CREATE_TABLE_TRANSACTIONS =
            "CREATE TABLE IF NOT EXISTS transactions ("
                    + "transaction_id INTEGER NOT NULL AUTO_INCREMENT,"
                    + "transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "buyer VARCHAR(30) NOT NULL,"
                    + "seller VARCHAR(30) NOT NULL,"
                    + "asset VARCHAR(30) NOT NULL,"
                    + "asset_amount INTEGER NOT NULL,"
                    + "value INTEGER NOT NULL,"
                    + "CONSTRAINT CHK_asset_amount CHECK (asset_amount > 0)"
                    + "CONSTRAINT CHK_asset_amount CHECK (value > 0)"
                    + "CONSTRAINT FK_organisation_assets FOREIGN KEY (buyer, asset) "
                    + "REFERENCES assets(organisation, asset)"
                    + "ON DELETE CASCADE"
                    + "CONSTRAINT FK_organisation_assets FOREIGN KEY (seller, asset) "
                    + "REFERENCES assets(organisation, asset)"
                    + "ON DELETE CASCADE"
                    + ");";
}

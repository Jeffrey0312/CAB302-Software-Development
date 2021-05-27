package tradingPlatform;

import java.sql.SQLException;
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
     * @param o name of organisation to add
     */
    void addOrganisation(OrganisationalUnit o);

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
     * changes the amount of assets an organisation has
     *
     * @param organisation name of the organisation that the change will happen to
     * @param asset name of the asset the change will happen to
     * @param amount the new amount that the amount of assets will be changed to
     */
    void setOrganisationAssetAmount(String organisation, String asset, int amount);

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisited.
     */
    void close();

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
    Set<String> getUserList();

    /**
     * Gets the number of user in the user list.
     * @return size of user list.
     */
    int getSize();



}

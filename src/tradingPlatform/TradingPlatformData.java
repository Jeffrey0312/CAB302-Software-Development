package tradingPlatform;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 * This version uses an AddressBookDataSource and its methods to retrieve data
 */
public class TradingPlatformData {

    DefaultListModel userListModel;
    DefaultListModel organisationListModel;
    DefaultListModel orderListModel;
    DefaultListModel transactionListModel;

    TradingPlatformDataSource data;

    /**
     * Constructor initializes the list model that holds names as Strings and
     * attempts to read any data saved from previous invocations of the
     * application.
     *
     */
    public TradingPlatformData(TradingPlatformDataSource dataSource) {
        userListModel = new DefaultListModel();
        organisationListModel = new DefaultListModel();
        orderListModel = new DefaultListModel();
        transactionListModel = new DefaultListModel();
        data = dataSource;

        // add the retrieved data to the list model
        for (String user : data.getUsersList()) {
            userListModel.addElement(user);
        }
        for (String organisation : data.getOrganisationsList()) {
            organisationListModel.addElement(organisation);
        }
    }

    /**
     * Adds an OrganisationalUnit to the data list.
     *
     * @param orgName The name of the organisation to add to the data list.
     */
    public void addOrganisation(OrganisationalUnit orgName) {

        // check to see if the organisation is already in the book
        // if not add to the organisation list and the list model
        if (!organisationListModel.contains(orgName)) {
            organisationListModel.addElement(orgName);
            data.addOrganisation(orgName.getName());
        }
    }

    public void setOrganisationCredits(OrganisationalUnit orgName, int credits) {
        data.setOrganisationCredits(orgName.getName(), credits);
    }

    /**
     * Based on the name of the organisation in the data book, delete the organisation.
     *
     * @param key
     */
    public void deleteOrganisation(Object key) {
        // remove from both list and map
        organisationListModel.removeElement(key);
        data.deleteOrganisation((String) key);
    }

    /**
     * Adds a user to the user list.
     * @param u A User to add to the user list.
     */
    public void addUser(User u) {
        // check to see if the person is already in the book
        // if not add to the address book and the list model
        if (!userListModel.contains(u.getUsername())) {
            userListModel.addElement(u.getUsername());
            data.addUser(u);
        }
    }

    /**
     * Based on the name of the person in the address book, delete the person.
     *
     * @param key
     */
    public void deleteUser(Object key) {
        // remove from both list and map
        userListModel.removeElement(key);
        data.deleteUser((String) key);
    }

    /**
     * Retrieves Person details from the model.
     *
     * @param key the name to retrieve.
     * @return the User object related to the username.
     */
    public User getUser(Object key) {
        return data.getUser((String) key);
    }

    /**
     * Saves the data in the address book using a persistence
     * mechanism.
     */
    public void persist() {
        data.close();
    }

    /**
     * Retrieves Person details from the model.
     *
     * @param key the name to retrieve.
     * @return the Person object related to the name.
     */
    public OrganisationalUnit getOrganisation(Object key) {
        return data.getOrganisation((String) key);
    }

    /**
     * Accessor for the list model.
     * @return the listModel to display.
     */
    public ListModel getUserModel() {
        return userListModel;
    }

    /**
     * Accessor for the list model.
     * @return the listModel to display.
     */
    public ListModel getOrganisationModel() {
        return userListModel;
    }

    /**
     * @return the number of names in the Address Book.
     */
    public int getSize() {
        return data.getUserSize();
    }
}

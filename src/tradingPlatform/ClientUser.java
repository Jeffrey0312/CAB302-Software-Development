package tradingPlatform;

public class ClientUser extends User {

    private OrganisationalUnit organisation;

    public ClientUser(String userName, String firstName, String lastName, String password, OrganisationalUnit organisation) {
        super(userName, firstName, lastName, password);
        this.organisation = organisation;
    }

    /**
     * returns the ClientUser's OrganisationalUnit
     * @return returns the OrganisationalUnit of the ClientUser
     */
    public OrganisationalUnit getOrganisation() {
        return organisation;
    }
}

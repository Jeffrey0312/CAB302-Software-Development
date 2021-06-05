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

    /**
     * sets the organisation of the user
     * @param organisation the new organisation
     */
    public void setOrganisation(OrganisationalUnit organisation){this.organisation = organisation;}
}

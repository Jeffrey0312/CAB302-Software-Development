package tradingPlatform;


public class ITUser extends User{

    public ITUser() {
    }
    public ITUser(String userName, String firstname, String lastname, String password) {
        super(userName,firstname,lastname,password);

    }
    /** Creates a new organisation
     * @param organisation The organisation that is being added
     * */
    public void addOrg(String organisation){

    }

    /** Deletes an organisation
     * @param organisation The organisation that is being deleted
     * */
    public void deleteOrg(String organisation) {

    }

    /** Adds a user to en existing Organisation
     * @param organisation The organisation that the user is being added to
     * @param user The user that is being added to an organisation
     * */
    public void addUserToOrg(String user, String organisation) {

    }

    /** Edits the credits for an organisation
     * @param organisation The organisation that is having its credits edited
     * @param amount Sets the credit amount for the organisation
     */
    public void editCredits(String organisation, int amount) {

    }

    /** Edits the assets
     * @param organisation The organisation that is having its asset altered
     * @param asset The asset that is having its amount altered
     * @param amount The amount that the asset is being altered to
     * */
    public void editAssets (String organisation, String asset, int amount) {

    }

    /** Add an asset
     * @param asset The asset being added
     * */
    public void addAsset(String asset) {

    }

    /** Deletes and asset from the list
     * @param asset The asset being deleted
     * */
    public void deleteAssets(String asset) {

    }

    /** Deletes a user
     * @param userName The user being deleted from the system
     * */
    public void deleteUser(String userName) {

    }



}

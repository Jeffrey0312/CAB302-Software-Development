package tradingPlatform;

public class OrganisationalUnit implements TradingPlatform{

    private String organisation;
    private int credits;
    private String[] assets;
    private int[] assets_amount;

    /**
     * no argument constructor
     */
    public OrganisationalUnit(){

    }
    /**
     * Constructor to set the values of
     * @param organisation
     * @param credits
     * @param assets
     * @param assets_amount
     * */
    public OrganisationalUnit(String organisation, int credits, String[] assets, int[] assets_amount){
        this.organisation = organisation;
        this.credits = credits;
        this.assets = assets;
        this.assets_amount = assets_amount;
    }

    /**
     * returns the organisations name
     * @return organisation
     */
    public String getOrganisation(){return this.organisation;
    }

    /**
     * sets the organisations name to the input
     * @param organisation
     */
    public void setOrganisation(String organisation){this.organisation = organisation;
    }

    /**
     * returns the organisations credits
     * @return credits
     */
    public int getCredits(){return this.credits;
    }

    /**
     * sets the organisations credits to the input
     * @param credits
     */
    public void setCredits(int credits){this.credits = credits;
    }

    /**
     * returns the assets the organisation is associated with
     * @return assets
     */
    public String[] getAssets(){return this.assets;
    }

    /**
     * sets the what assets the organisation is associated with
     * @param assets
     */
    public void setAssets(String[] assets){this.assets = assets;
    }

    /**
     * returns the asset at the given index
     * @param index
     * @return
     */
    public String getAsset(int index){return this.assets[index];
    }

    /**
     * sets the asset at the given index equal to the new asset
     * @param asset
     * @param index
     */
    public void setAsset(String asset, int index){this.assets[index] = asset;
    }

    /**
     * returns the amount of each asset the organisation has
     * @return
     */
    public int[] getAssets_amount(){return this.assets_amount;
    }

    /**
     * sets the amount of assets the organisation has
     * @param assets_amount
     */
    public void setAssets_amount(int[] assets_amount){this.assets_amount = assets_amount;
    }

    /**
     *
     * @param index
     * @return
     */
    public int getAsset_amount(int index){return this.assets_amount[index];
    }

    /**
     *
     * @param asset_amount
     * @param index
     */
    public void setAsset_amount(int asset_amount, int index){this.assets_amount[index] = asset_amount;
    }

    /** Buys an asset
     * @param asset The asset that is being bought
     * */
    public void buyAssets(String asset, int credits) {

    }

    /** Sells and asset
     * @param asset The asset that is being sold
     * */
    public void sellAssets(String asset, int credits) {

    }

    /** Cancels a current order
     * @param orderId The ID of the order
     * */
    public void cancelOrder(int orderId) {

    }

}

package tradingPlatform;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class OrganisationalUnit implements Serializable {

    private String organisation;
    private int credits;
    private HashMap<String, Integer> assets;

    /**
     * no argument constructor
     */
    public OrganisationalUnit(){

    }

    /**
     * Constructor to set the values of
     * @param organisation name of the organisation
     * @param credits amount of credits the organisation has
     * */
    public OrganisationalUnit(String organisation, int credits){
        this.organisation = organisation;
        this.credits = credits;
    }
    /**
     * Constructor to set the values of
     * @param organisation name of the organisation
     * @param credits amount of credits the organisation has
     * @param assets the amounts of each asset the organisation has
     * */
    public OrganisationalUnit(String organisation, int credits, HashMap<String, Integer> assets){
        this.organisation = organisation;
        this.credits = credits;
        this.assets = assets;
    }

    /**
     * returns the organisations name
     * @return organisation
     */
    public String getName(){return this.organisation;
    }

    /**
     * sets the organisations name to the input
     * @param organisation new name of the organisation
     */
    public void setName(String organisation){this.organisation = organisation;
    }

    /**
     * returns the organisations credits
     * @return integer amount of assets
     */
    public int getCredits(){return this.credits;
    }

    /**
     * sets the organisations credits to the input
     * @param credits amount
     */
    public void setCredits(int credits){this.credits = credits;
    }

    /**
     * returns the assets the organisation's assets
     * @return Hashmap of Assets
     */
    public HashMap<String,Integer> getAssets(){return this.assets;
    }

    /**
     * sets the what assets the organisation is associated with
     * @param assets new Hashmap of Assets
     */
    public void setAssets(HashMap<String, Integer> assets){this.assets = assets;
    }

    /**
     * returns the amount of the given asset
     * @param asset name of the asset
     * @return the amount of the that asset the organisation has
     */
    public int getAssetAmount(String asset){return this.assets.get(asset);
    }

    /**
     * sets the amount of the asset to the new amount
     * @param asset asset to be changed
     * @param amount amount to be set to
     */
    public void setAsset(String asset, int amount){this.assets.replace(asset,amount);
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

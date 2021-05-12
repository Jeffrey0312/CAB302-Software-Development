package tradingPlatform;

public class OrganisationalUnit implements TradingPlatform{

    private String[] assets;
    private int credits;


    /** Get a list of assets the organisation currently owns */
    public String[] getAssets() {
        return this.assets;
    }

    /** Check the organisations credits */
    public int getCredits() {
        return this.credits;
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

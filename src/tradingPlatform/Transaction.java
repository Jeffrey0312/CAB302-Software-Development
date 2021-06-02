package tradingPlatform;

public class Transaction {

    private String asset;
    private Integer assetAmount;
    private Integer value;

    /**
     * No args constructor.
     */
    public Transaction() {
    }

    public Transaction(String asset,Integer assetAmount,Integer value){
        this.asset = asset;
        this.assetAmount = assetAmount;
        this.value = value;
    }

    /**
     * @return the asset
     */
    public String getAsset() {
        return asset;
    }

    /**
     * the asset to the set
     */
    public void setAsset(String asset) {
        this.asset = asset;
    }

    /**
     * @return the assetAmount
     */
    public Integer getAssetAmount() {
        return assetAmount;
    }


    /**
     * the assetAmount to the set
     */
    public void setAssetAmount(Integer assetAmount) {
        this.assetAmount = assetAmount;
    }

    /**
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * the value to the set
     */
    public void setValue(Integer value) {
        this.value = value;
    }



    /** Shows the Buy and Sell history of a particular asset
     * @param asset The asset that is having its history checked
     * */
    public void transactionHistory(String asset){

    }

    /** Shows the price of an asset over a specific time period
     * @param asset The asset that is having its graph history
     * */
    public void transactionGraph(String asset){

    }


}

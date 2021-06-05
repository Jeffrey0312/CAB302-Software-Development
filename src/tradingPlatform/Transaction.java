package tradingPlatform;

public class Transaction {

    private int transactionId;
    private String dateTime;
    private String buyer;
    private String seller;
    private String asset;
    private Integer assetAmount;
    private Integer value;

    /**
     * No args constructor.
     */
    public Transaction() {
    }

    /**
     * Transaction constructor
     * @param buyer the name of the buying organisation
     * @param seller the name of the selling organisation
     * @param asset the asset being traded
     * @param assetAmount the amount of the asset being traded
     * @param value the value of per 1 amount of asset
     */
    public Transaction(int transactionId ,String buyer, String seller, String asset,Integer assetAmount,Integer value){
        this.buyer = buyer;
        this.seller = seller;
        this.asset = asset;
        this.assetAmount = assetAmount;
        this.value = value;
    }

    /**
     * @return returns transactionId
     */
    public int getTransactionId(){
        return transactionId;
    }

    /**
     * sets transactionId
     * @param transactionId new transactionId value
     */
    public void setTransactionId(int transactionId){
        this.transactionId = transactionId;
    }

    /**
     *
     * @return returns dateTime
     */
    public String getDateTime(){
        return dateTime;
    }

    /**
     * sets dateTime value
     * @param dateTime the new value for dateTime
     */
    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }

    /**
     *
     * @return returns the buyer
     */
    public String getBuyer(){
        return buyer;
    }

    /**
     * sets the buyer
     * @param buyer the new value for buyer
     */
    public void setBuyer(String buyer){
        this.buyer = buyer;
    }

    /**
     *
     * @return returns seller
     */
    public String getSeller(){
        return seller;
    }

    /**
     * sets the seller variable
     * @param seller new value for seller
     */
    public void setSeller(String seller){
        this.seller = seller;
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

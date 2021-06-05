package tradingPlatform;

public class Order {
    private int orderId;
    private boolean isBuy;
    private String organisation;
    private String asset;
    private int assetAmount;
    private int value;

    /**
     * no argument constructor
     */
    public Order(){}

    /**
     * Constructor to se the values of the order
     * @param isBuy
     * @param organisation
     * @param asset
     * @param assetAmount
     * @param value
     */
    public Order(boolean isBuy, String organisation, String asset, int assetAmount, int value){
        this.isBuy = isBuy;
        this.organisation = organisation;
        this.asset = asset;
        this.assetAmount = assetAmount;
        this.value = value;
    }

    /**
     *  return the order id of the order
     * @return returns the order id
     */
    public int getOrderId(){return orderId;}

    /**
     * sets the order id of the order
     * @param orderId the new orderId of the order
     */
    public void setOrderId(int orderId){this.orderId = orderId;}

    /**
     * returns whether or not the order is a buy order
     * @return returns boolean of isBuy
     */
    public boolean getIsBuy() {return isBuy;}

    /**
     * sets whether the organisation is a buy order or not
     * @param isBuy sets the isBuy boolean to this
     */
    public void setIsBuy(boolean isBuy){this.isBuy = isBuy;}

    /**
     * returns the organisation who is associated to the order
     * @return the orders organisation
     */
    public String getOrganisation(){return organisation;}

    /**
     * sets the organisation of the order
     * @param organisation the new organisation
     */
    public void setOrganisation(String organisation){this.organisation = organisation;}

    /**
     * returns the asset of the order
     * @return returns the asset
     */
    public String getAsset(){return asset;}

    /**
     * sets the asset of the order
     * @param asset the new asset
     */
    public void setAsset(String asset){this.asset = asset;}

    /**
     * returns the amount of the asset in the order
     * @return returns assetAmount
     */
    public int getAssetAmount(){return assetAmount;}

    /**
     * sets the amount of the asset of the order
     * @param assetAmount the new assetAmount
     */
    public void setAssetAmount(int assetAmount){this.assetAmount = assetAmount;}

    /**
     * returns the value for the asset
     * @return returns the value
     */
    public int getValue(){return  value;}

    /**
     * sets the value for the asset of the order
     * @param value the new value
     */
    public void setValue(int value){this.value = value;}
}

package tradingPlatform;

public class Order {
    private int orderId;
    private boolean isBuy;
    private String organisation;
    private String asset;
    private int assetAmount;
    private int value;

    public Order(){}

    public Order(boolean isBuy, String organisation, String asset, int assetAmount, int value){
        this.isBuy = isBuy;
        this.organisation = organisation;
        this.asset = asset;
        this.assetAmount = assetAmount;
        this.value = value;
    }

    public int getOrderId(){return orderId;}

    public void setOrderId(int orderId){this.orderId = orderId;}

    public boolean getIsBuy() {return isBuy;}

    public void setIsBuy(boolean isBuy){this.isBuy = isBuy;}

    public String getOrganisation(){return organisation;}

    public void setOrganisation(String organisation){this.organisation = organisation;}

    public String getAsset(){return asset;}

    public void setAsset(String asset){this.asset = asset;}

    public int getAssetAmount(){return assetAmount;}

    public void setAssetAmount(int assetAmount){this.assetAmount = assetAmount;}

    public int getValue(){return  value;}

    public void setValue(int value){this.value = value;}
}

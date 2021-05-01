package tradingPlatform;

public abstract class User implements TradingPlatform{

    private final String userName;
    private final String password;
    //private final String organisation;

    public User (String userName, String password) {
        this.userName = userName;
        this.password = password;
        //this.organisation = organisation;
    }


}

package tradingPlatform;

/**
 * These are the commands which will be sent across the network connection.
 */
public enum Command {
    GET_ORGANISATION,
    ADD_ORGANISATION,
    DELETE_ORGANISATION,
    GET_ORGANISATIONS_LIST,
    SET_ORGANISATION_CREDITS,
    SET_ORGANISATION_ASSET_AMOUNT,
    GET_SIZE,
    GET_USER,
    ADD_USER,
    DELETE_USER,
    DELETE_ASSET,
    GET_USERS_LIST,
    LOGIN,
    SET_USER_ORGANISATION,
    SET_USER_PASSWORD
}
package tradingPlatform;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This code was based on the code found at https://www.geeksforgeeks.org/sha-512-hash-in-java/
 */

public class Hash {
    public static String SHA512(String input) throws Exception {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            byte[] messagedigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1,messagedigest);

            StringBuilder hashtext = new StringBuilder(no.toString());

            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Hashing Failed");
        }
    }
}

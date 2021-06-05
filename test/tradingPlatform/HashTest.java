package tradingPlatform;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HashTest {
    /**
     * cite from https://gist.github.com/batmat/923937
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void SHA512() throws NoSuchAlgorithmException {
        // petite liste générée avec la commande echo -n "texte" | sha512sum sous Linux
        Map<String, String> exemples = new HashMap<String, String>();
        exemples
                .put(
                        "le mot de passe",
                        "b2dacb1654eb1fb49f605f5fb97d06d973ac1a499934e3abdec113" +
                                "f8aecc1fd4280f56cfb679cc0ae4d9153f0b8eb68ac683cdaca3a8bf6b4c436bec4ced7cde");
        exemples
                .put(
                        "bonjour",
                        "3041edbcdd46190c0acc504ed195f8a90129efcab173a7b9ac4646b92d04cc80005" +
                                "acaa3554f4b1df839eacadc2491cb623bf3aa6f9eb44f6ea8ca005821d25d");

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        for (Map.Entry<String, String> entry : exemples.entrySet())
        {
            byte[] digest = md.digest(entry.getKey().getBytes());

            assertEquals(entry.getValue(), String.format("%0128x", new BigInteger(1, digest)));
        }
    }
    }

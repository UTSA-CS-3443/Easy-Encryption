package application.model;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

    public String encrypt(String data, SecretKey ourKey) throws Exception {
            try {

                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, ourKey);
                return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
            } catch (Exception e) {
                System.out.println("Error while encrypting: " + e.toString());
            }
            return null;
        }

    public String decrypt(String encryptedData, SecretKey ourKey) throws Exception {
                try {

                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE, ourKey);
                    return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
                   } catch (Exception e) {
                    System.out.println("Error while decrypting: " + e.toString());
                }
                return null;
            }


    public static SecretKey convertStringToSecretKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return originalKey;
    }

}

package com.ritesh.passwordmanager.encryption;
import java.nio.charset.StandardCharsets;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

	// AES Secret Key
	private static final String SECRET_KEY = "PasswordManagerKey12345678901234";
   
	private AESUtil() {
	}
    // Encrypt passwordMethod
   public static String encrypt(String password) {

       try {

           SecretKeySpec secretKey = new SecretKeySpec(
                   SECRET_KEY.getBytes(StandardCharsets.UTF_8),
                   "AES"
           );

           Cipher cipher = Cipher.getInstance("AES");

           cipher.init(Cipher.ENCRYPT_MODE, secretKey);

           byte[] encryptedBytes = cipher.doFinal(
                   password.getBytes(StandardCharsets.UTF_8));

           return Base64.getEncoder().encodeToString(encryptedBytes);

       } catch (Exception e) {

           e.printStackTrace();
           return null;

       }

   }
    // Decrypt password Method
    
    public static String decrypt(String encryptedPassword) {

        try {

        	SecretKeySpec key = new SecretKeySpec(
        	        SECRET_KEY.getBytes(StandardCharsets.UTF_8),
        	        "AES"
        	);
            

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decoded = Base64.getDecoder().decode(encryptedPassword);

            return new String(
                    cipher.doFinal(decoded),
                    StandardCharsets.UTF_8
            );
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
    

}
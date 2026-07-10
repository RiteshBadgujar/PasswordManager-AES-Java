package com.ritesh.passwordmanager.encryption;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    // 16-character secret key (AES-128)
  //  private static final String SECRET_KEY = "PasswordKey123456";
    private static final String SECRET_KEY = "1234567890123456";

    // Encrypt Method
    public static String encrypt(String password) {

        try {

            SecretKeySpec key = new SecretKeySpec(
                    SECRET_KEY.getBytes(),
                    "AES"
            );

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encrypted = cipher.doFinal(password.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // Decrypt Method
    
    public static String decrypt(String encryptedPassword) {

        try {

            SecretKeySpec key = new SecretKeySpec(
                    SECRET_KEY.getBytes(),
                    "AES"
            );

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decoded = Base64.getDecoder().decode(encryptedPassword);

            return new String(cipher.doFinal(decoded));

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

}
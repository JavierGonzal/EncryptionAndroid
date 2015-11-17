package com.thedeveloperworldisyous.encryptionfile.utils;

import android.util.Log;

import com.thedeveloperworldisyous.encryptionfile.R;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by javiergonzalezcabezas on 17/11/15.
 */
public class SymmetricAlgorithmAES {

    public static final String sTAG = "SymmetricAlgorithmAES";

    /**
     * Seted up secret key spec for 128-bit AES encryption and decryption
     * @return
     */
    public static SecretKeySpec setUpSecrectKey() {
        SecretKeySpec secretKeySpec = null;
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128, sr);
            secretKeySpec = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (Exception e) {
            Log.e(sTAG, String.valueOf(R.string.symmetric_algorithm_aes_secret_key_error));
        }
        return secretKeySpec;
    }

    /**
     * Encoded the original data with AES
     * @param secretKeySpec
     * @param theTestText
     * @return
     */
    public static byte[] encryption(SecretKeySpec secretKeySpec, String  theTestText) {
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            encodedBytes = c.doFinal(theTestText.getBytes());
        } catch (Exception e) {
            Log.e(sTAG, String.valueOf(R.string.symmetric_algorithm_aes_encryption));
        }

        return encodedBytes;
    }

    /**
     * Decoded the encoded data with AES
     * @param secretKeySpec
     * @param encodedBytes
     * @return
     */
    public static byte[] decryption(SecretKeySpec secretKeySpec, byte[]  encodedBytes) {
        byte[] decodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, secretKeySpec);
            decodedBytes = c.doFinal(encodedBytes);
        } catch (Exception e) {
            Log.e(sTAG, String.valueOf(R.string.symmetric_algorithm_aes_decryption));
        }

        return decodedBytes;
    }
}

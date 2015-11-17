package com.thedeveloperworldisyous.encryptionfile.utils;

import android.util.Log;

import com.thedeveloperworldisyous.encryptionfile.R;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

/**
 * Created by javiergonzalezcabezas on 17/11/15.
 */
public class AsymmetricAlgorithmRSA {

    public static final String sTAG = "AsymmetricAlgorithmRSA";

    /**
     * Generated key pair for 1024-bit RSA encryption and decryption
     * @return
     */
    public static KeyPair generateKey() {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            keyPair = kpg.genKeyPair();

        } catch (Exception e) {
            Log.e(sTAG, String.valueOf(R.string.asymmetric_algorithm_rsa_key));
        }
        return keyPair;
    }

    /**
     * Encoded the original data with RSA private key
     * @param privateKey
     * @param encryptionText
     * @return
     */
    public static byte[] encryption(Key privateKey, String encryptionText) {
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE, privateKey);
            encodedBytes = c.doFinal(encryptionText.getBytes());
        } catch (Exception e) {
            Log.e(sTAG, String.valueOf(R.string.asymmetric_algorithm_rsa_enryption_error));
        }
        return encodedBytes;
    }

    /**
     * Decoded the encoded data with RSA public key
     * @param publicKey
     * @param encodedBytes
     * @return
     */
    public static byte[] decryption(Key publicKey, byte[] encodedBytes) {
        byte[] decodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.DECRYPT_MODE, publicKey);
            decodedBytes = c.doFinal(encodedBytes);
        } catch (Exception e) {
            Log.e(sTAG, String.valueOf(R.string.asymmetric_algorithm_rsa_deryption_error));
        }
        return decodedBytes;
    }
}

package com.fuzzy;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by John on 5/28/2016.
 */
public class HMACSignature {

    private static final String METHOD = "HmacSHA256";
    private static final String CHARSET = "UTF-8";

    private final String message;

    HMACSignature(String path, String parameters, String nonce) {
        this.message = nonce + APIKeys.AUTH_KEY + path + parameters;
    }

    /**
     * Creates a HMACSHA1 key.
     *
     * @return the created HMAC signature.
     */
    @Override
    public String toString() {
        try {
            Mac sha256_HMAC = Mac.getInstance(METHOD);
            SecretKeySpec secret_key = new SecretKeySpec(APIKeys.SECRET_KEY.getBytes(CHARSET), METHOD);
            sha256_HMAC.init(secret_key);
            return Hex.encodeHexString(sha256_HMAC.doFinal(message.getBytes(CHARSET))).toUpperCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }

}

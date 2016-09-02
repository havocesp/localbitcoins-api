package com.fuzzy;

/**
 * Created by John on 5/29/2016.
 */
public class KeyDatabase {

    private String secretKey;
    private String authKey;

    private KeyDatabase() {
        this.secretKey = "";
        this.authKey = "";
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getAuthKey() {
        return authKey;
    }

    private static KeyDatabase instance;

    public static KeyDatabase getInstance() {
        return instance != null ? instance : (instance = new KeyDatabase());
    }

}

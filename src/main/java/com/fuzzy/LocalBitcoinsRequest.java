package com.fuzzy;

import com.fuzzy.parameter.ParameterCollection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by John on 5/28/2016.
 */
public class LocalBitcoinsRequest {

    public static final String BASE_URL = "https://localbitcoins.com";
    public static final Gson GSON = new GsonBuilder().create();

    private final String path;
    private final String nonce;
    private final String signature;

    public LocalBitcoinsRequest(String path, ParameterCollection parameters) {
        this.nonce = String.valueOf(System.currentTimeMillis());
        this.path = path.startsWith("/api") ? path : ("/api" + path);
        this.signature = new HMACSignature(this.path, parameters.toString(), nonce).toString();
    }

    public HttpResponse getContent() {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(constructUrl());
            get.addHeader("Apiauth-Key", KeyDatabase.getInstance().getAuthKey());
            get.addHeader("Apiauth-Nonce", nonce);
            get.addHeader("Apiauth-Signature", signature);
            return client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String pullData() {
        StringBuilder builder = new StringBuilder();
        HttpResponse response = getContent();
        if (response != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }

    public String constructUrl() {
        return BASE_URL + path;
    }

    public static final String WALLET = "/wallet/";
    public static final String WALLET_BALANCE = "/wallet-balance/";
    public static final String DASHBOARD = "/dashboard/";
    public static final String RELEASED = DASHBOARD + "released/";
    public static final String CANCELED = DASHBOARD + "canceled/";
    public static final String CLOSED = DASHBOARD + "closed/";

}

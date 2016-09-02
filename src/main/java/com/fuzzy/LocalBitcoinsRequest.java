package com.fuzzy;

import com.fuzzy.parameter.ParameterCollection;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by John on 5/28/2016.
 */
public class LocalBitcoinsRequest {

    public static final String BASE_URL = "https://localbitcoins.com";

    private final String path;
    private final String nonce;
    private String signature;
    private final HttpType type;

    private ParameterCollection parameters;

    public LocalBitcoinsRequest(String path, ParameterCollection parameters, HttpType type) {
        this.nonce = String.valueOf(System.currentTimeMillis());
        this.path = path.startsWith("/api") ? path : ("/api" + path);
        this.parameters = parameters;
        this.type = type;
    }

    public enum HttpType {
        GET, POST, DELETE, PUT;
    }

    private HttpResponse getContent() {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpRequestBase base;
            String parametersString = "";
            switch (type) {
                case GET:
                default:
                    base = new HttpGet(constructUrl());
                    break;
                case POST:
                    base = new HttpPost(constructUrl());
                    ((HttpPost) base).setEntity(new UrlEncodedFormEntity(parameters.getParameters(), "UTF-8"));
                    BufferedReader inputStream = new BufferedReader(new InputStreamReader(((HttpPost) base).getEntity().getContent()));
                    parametersString = inputStream.readLine();
                    break;
            }
            base.addHeader("Content-Type", "application/x-www-form-urlencoded");
            base.addHeader("Apiauth-Key", KeyDatabase.getInstance().getAuthKey());
            base.addHeader("Apiauth-Nonce", nonce);
            this.signature = new HMACSignature(this.path, parametersString, nonce).toString();
            base.addHeader("Apiauth-Signature", signature);
            return client.execute(base);
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

    private String constructUrl() {
        return BASE_URL + path;
    }

    public static final String WALLET = "/wallet/";
    public static final String WALLET_BALANCE = "/wallet-balance/";
    public static final String DASHBOARD = "/dashboard/";
    public static final String RELEASED = DASHBOARD + "released/";
    public static final String CANCELED = DASHBOARD + "canceled/";
    public static final String CLOSED = DASHBOARD + "closed/";

}

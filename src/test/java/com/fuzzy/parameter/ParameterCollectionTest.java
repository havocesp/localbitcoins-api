package com.fuzzy.parameter;

import com.fuzzy.LocalBitcoinsRequest;
import com.fuzzy.ParameterCollection;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by fuzzyavacado on 9/2/16.
 */
public class ParameterCollectionTest {

    @Test
    public void run() {
        ParameterCollection parameterCollection = new ParameterCollection(new ArrayList<>());
        //parameterCollection.addAll(new BasicNameValuePair("contacts", "jeff"));
        LocalBitcoinsRequest request = new LocalBitcoinsRequest("/api/dashboard/", parameterCollection, LocalBitcoinsRequest.HttpType.GET);
        System.out.println(request.pullData());
    }
}
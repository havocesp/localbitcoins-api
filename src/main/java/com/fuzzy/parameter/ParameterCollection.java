package com.fuzzy.parameter;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

/**
 * Created by fuzzyavacado on 9/2/16.
 */
public class ParameterCollection {

    private List<BasicNameValuePair> parameters;

    public ParameterCollection(List<BasicNameValuePair> parameters) {
        this.parameters = parameters;
    }

    public void add(BasicNameValuePair requestParameter) {
        this.parameters.add(requestParameter);
    }

    public void addAll(BasicNameValuePair... requestParameters) {
        for (BasicNameValuePair p : requestParameters) {
            add(p);
        }
    }

    public List<BasicNameValuePair> getParameters() {
        return parameters;
    }

    public void setParameters(List<BasicNameValuePair> parameters) {
        this.parameters = parameters;
    }
}

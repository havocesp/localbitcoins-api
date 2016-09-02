package com.fuzzy.parameter;

import java.util.List;

/**
 * Created by fuzzyavacado on 9/2/16.
 */
public class ParameterCollection {

    private List<RequestParameter> parameters;

    public ParameterCollection(List<RequestParameter> parameters) {
        this.parameters = parameters;
    }

    public void add(RequestParameter requestParameter) {
        this.parameters.add(requestParameter);
    }

    public void addAll(RequestParameter... requestParameters) {
        for (RequestParameter p : requestParameters) {
            add(p);
        }
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        for (RequestParameter p : parameters) {
            builder.append("\"").append(p.getKey()).append("\"").append(":");
            if (p.getValue() instanceof String) {
                builder.append("\"").append(p.getValue()).append("\"");
            } else {
                builder.append(p.getValue());
            }
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");
        return builder.toString();
    }

}

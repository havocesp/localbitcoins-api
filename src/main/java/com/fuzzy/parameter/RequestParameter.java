package com.fuzzy.parameter;

/**
 * Created by fuzzyavacado on 9/2/16.
 */
public class RequestParameter<T, V> {

    private T key;
    private V value;

    public RequestParameter(T key, V value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

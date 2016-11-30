package com.keensense.vrconvo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panch on 30.11.2016.
 */

public class Response<T> {

    @SerializedName("message")
    private String message;

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }

    @SerializedName("data")
    private T data;

    public T getData() { return this.data; }

    public void setData(T data) { this.data = data; }
}

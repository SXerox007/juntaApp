package com.skeleton.model.Profile;

import com.google.gson.annotations.SerializedName;

/**
 * Developer: Sumit Thakur
 * Dated: 15-05-2017.
 */
public class Response {

    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;

    /**
     * @return get status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode status of the code
     */
    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return get message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return get data from user
     */
    public Data getData() {
        return data;
    }

    /**
     * set data
     *
     * @param data data
     */
    public void setData(final Data data) {
        this.data = data;
    }
}

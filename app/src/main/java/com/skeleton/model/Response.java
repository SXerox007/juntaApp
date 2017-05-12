
package com.skeleton.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * {@link Response}
 */
public class Response implements Serializable, Parcelable {

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(final Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(final int size) {
            return new Response[size];
        }
    };
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * @param in in
     */
    protected Response(final Parcel in) {
        message = in.readString();
    }

    /**
     * status code of user
     *
     * @return : statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * Setter method
     *
     * @param statusCode : statusCode
     */
    public void setStatusCode(final Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Message
     *
     * @return : message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method
     *
     * @param message : message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * data
     *
     * @return : data
     */
    public Data getData() {
        return data;
    }

    /**
     * Setter method
     *
     * @param data : data
     */
    public void setData(final Data data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(message);
    }
}
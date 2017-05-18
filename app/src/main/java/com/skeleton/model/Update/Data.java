package com.skeleton.model.Update;

import com.google.gson.annotations.SerializedName;

/**
 * Developer: Sumit Thakur
 * Dated: 17-05-2017.
 */
public class Data {
    @SerializedName("accessToken")
    private String accessToken;
    @SerializedName("userDetails")
    private UserDetails userDetails;

    /**
     * @return get Access token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken access token
     */
    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return user details
     */
    public UserDetails getUserDetails() {
        return userDetails;
    }

    /**
     * @param userDetails user details
     */
    public void setUserDetails(final UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}

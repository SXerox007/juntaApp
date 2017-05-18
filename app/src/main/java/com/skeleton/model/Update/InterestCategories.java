package com.skeleton.model.Update;

import com.google.gson.annotations.SerializedName;

/**
 * Developer: Sumit Thakur
 * Dated: 17-05-2017.
 */
public class InterestCategories {
    @SerializedName("_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("picURL")
    private PicURL picURL;

    /**
     * @return return id
     */
    public String get_id() {
        return id;
    }

    /**
     * @param id1 set id
     */
    public void set_id(final String id1) {
        this.id = id1;
    }

    /**
     * @return get name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name set name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return get Pic URL
     */
    public PicURL getPicURL() {
        return picURL;
    }

    /**
     * @param picURL pic URL
     */
    public void setPicURL(final PicURL picURL) {
        this.picURL = picURL;
    }
}

package com.skeleton.model.Update;

import com.google.gson.annotations.SerializedName;

/**
 * Developer: Sumit Thakur
 * Dated: 17-05-2017.
 */
public class PicURL {
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("original")
    private String original;

    /**
     * @return thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail set thumb nail
     */
    public void setThumbnail(final String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return get original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @param original set original
     */
    public void setOriginal(final String original) {
        this.original = original;
    }
}

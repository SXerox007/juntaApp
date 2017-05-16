package com.skeleton.model.Profile2;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Developer: Sumit Thakur
 * Dated: 16-05-2017.
 */
public class Data {
    @SerializedName("categories")
    private List<Categories> categories;

    /**
     * @return categories
     */
    public List<Categories> getCategories() {
        return categories;
    }

    /**
     * @param categories list of categories
     */
    public void setCategories(final List<Categories> categories) {
        this.categories = categories;
    }
}

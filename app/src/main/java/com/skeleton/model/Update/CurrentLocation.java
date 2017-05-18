package com.skeleton.model.Update;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Developer: Sumit Thakur
 * Dated: 17-05-2017.
 */
public class CurrentLocation {
    @SerializedName("coordinates")
    private List<String> coordinates;
    @SerializedName("type")
    private String type;

    /**
     * @return return coordinates
     */
    public List<String> getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates set coordinates
     */
    public void setCoordinates(final List<String> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return get Type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type type
     */
    public void setType(final String type) {
        this.type = type;
    }
}

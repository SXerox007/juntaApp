package com.skeleton.model.Profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Developer: Sumit Thakur
 * Dated: 15-05-2017.
 */
public class Data {
    @SerializedName("Orientation")
    private List<String> orientation;
    @SerializedName("RelationshipHistory")
    private List<String> relationshipHistory;
    @SerializedName("Ethnicity")
    private List<String> ethnicity;
    @SerializedName("Religion")
    private List<String> religion;
    @SerializedName("BodyType")
    private List<String> bodyType;
    @SerializedName("Smoking")
    private List<String> smoking;
    @SerializedName("Drinking")
    private List<String> drinking;
    @SerializedName("Height")
    private List<String> height;

    /**
     * @return orientation
     */
    public List<String> getOrientation() {
        return orientation;
    }

    /**
     * @param orientation orientation
     */
    public void setOrientation(final List<String> orientation) {
        this.orientation = orientation;
    }

    /**
     * @return relationship history
     */
    public List<String> getRelationshipHistory() {
        return relationshipHistory;
    }

    /**
     * @param relationshipHistory relation history
     */
    public void setRelationshipHistory(final List<String> relationshipHistory) {
        this.relationshipHistory = relationshipHistory;
    }

    /**
     * @return ethnicity
     */
    public List<String> getEthnicity() {
        return ethnicity;
    }

    /**
     * @param ethnicity ethnicity
     */
    public void setEthnicity(final List<String> ethnicity) {
        this.ethnicity = ethnicity;
    }

    /**
     * @return religion of  hte user
     */
    public List<String> getReligion() {
        return religion;
    }

    /**
     * @param religion religiion of the user
     */
    public void setReligion(final List<String> religion) {
        this.religion = religion;
    }

    /**
     * @return get body tyle
     */
    public List<String> getBodyType() {
        return bodyType;
    }

    /**
     * @param bodyType bodytype set
     */
    public void setBodyType(final List<String> bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * @return smoking ot not get
     */
    public List<String> getSmoking() {
        return smoking;
    }

    /**
     * @param smoking smoking or not
     */
    public void setSmoking(final List<String> smoking) {
        this.smoking = smoking;
    }

    /**
     * @return drinking of user or not
     */
    public List<String> getDrinking() {
        return drinking;
    }

    /**
     * @param drinking drinking of user
     */
    public void setDrinking(final List<String> drinking) {
        this.drinking = drinking;
    }

    /**
     * @return height of the  user
     */
    public List<String> getHeight() {
        return height;
    }

    /**
     * @param height height of the user
     */
    public void setHeight(final List<String> height) {
        this.height = height;
    }
}

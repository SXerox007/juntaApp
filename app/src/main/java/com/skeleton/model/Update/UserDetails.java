package com.skeleton.model.Update;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Developer: Sumit Thakur
 * Dated: 17-05-2017.
 */
public class UserDetails {
    @SerializedName("_id")
    private String id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("dob")
    private String dob;
    @SerializedName("email")
    private String email;
    @SerializedName("phoneNo")
    private String phoneNo;
    @SerializedName("countryCode")
    private String countryCode;
    @SerializedName("orientation")
    private String orientation;
    @SerializedName("userImages")
    private List<UserImages> userImages;
    @SerializedName("admin_deactivateAccount")
    private boolean adminDeactivateAccount;
    @SerializedName("timeOffset")
    private int timeOffset;
    @SerializedName("gender")
    private String gender;
    @SerializedName("aboutMe")
    private String aboutMe;
    @SerializedName("step2CompleteOrSkip")
    private boolean step2CompleteOrSkip;
    @SerializedName("step1CompleteOrSkip")
    private boolean step1CompleteOrSkip;
    @SerializedName("profilePicURL")
    private ProfilePicURL profilePicURL;
    @SerializedName("defaultAddressId")
    private String defaultAddressId;
    @SerializedName("currentLocation")
    private CurrentLocation currentLocation;
    @SerializedName("phoneVerified")
    private boolean phoneVerified;
    @SerializedName("emailVerified")
    private boolean emailVerified;
    @SerializedName("drinking")
    private String drinking;
    @SerializedName("smoking")
    private String smoking;
    @SerializedName("bodyType")
    private String bodyType;
    @SerializedName("height")
    private String height;
    @SerializedName("religion")
    private String religion;
    @SerializedName("ethnicity")
    private String ethnicity;
    @SerializedName("relationshipHistory")
    private String relationshipHistory;
    @SerializedName("notificationEnable")
    private boolean notificationEnable;
    @SerializedName("directDateRequestEnable")
    private boolean directDateRequestEnable;
    @SerializedName("privacy")
    private boolean privacy;
    @SerializedName("isDisable")
    private boolean isDisable;
    @SerializedName("language")
    private String language;
    @SerializedName("firstTimeLogin")
    private boolean firstTimeLogin;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("newNumber")
    private String newNumber;
    @SerializedName("interestCategories")
    private List<InterestCategories> interestCategories;

    /**
     * @return id
     */
    public String get_id() {
        return id;
    }

    /**
     * @param id1 id
     */
    public void set_id(final String id1) {
        this.id = id1;
    }

    /**
     * @return get created
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt set created
     */
    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return get Updated at
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt set update at
     */
    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return get dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob set dob
     */
    public void setDob(final String dob) {
        this.dob = dob;
    }

    /**
     * @return get Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email set Email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return get Phone number
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo set Phone Number
     */
    public void setPhoneNo(final String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return get country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode country code
     */
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return orientation
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * @param orientation set orientation
     */
    public void setOrientation(final String orientation) {
        this.orientation = orientation;
    }

    /**
     * @return User images
     */
    public List<UserImages> getUserImages() {
        return userImages;
    }

    /**
     * @param userImages set Images
     */
    public void setUserImages(final List<UserImages> userImages) {
        this.userImages = userImages;
    }

    /**
     * @return get Admin deactivate Account
     */
    public boolean getAdmin_deactivateAccount() {
        return adminDeactivateAccount;
    }


    /**
     * @param adminDeactivateAccount1 set Admin deactivate account
     */
    public void setAdmin_deactivateAccount(final boolean adminDeactivateAccount1) {
        this.adminDeactivateAccount = adminDeactivateAccount1;
    }

    /**
     * @return get time off set
     */
    public int getTimeOffset() {
        return timeOffset;
    }

    /**
     * @param timeOffset set time off set
     */
    public void setTimeOffset(final int timeOffset) {
        this.timeOffset = timeOffset;
    }

    /**
     * @return get gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender set gender
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }

    /**
     * @return about me
     */
    public String getAboutMe() {
        return aboutMe;
    }

    /**
     * @param aboutMe set about me
     */
    public void setAboutMe(final String aboutMe) {
        this.aboutMe = aboutMe;
    }

    /**
     * @return get step2 or skip
     */
    public boolean getStep2CompleteOrSkip() {
        return step2CompleteOrSkip;
    }

    /**
     * @param step2CompleteOrSkip set step2 complete or skip
     */
    public void setStep2CompleteOrSkip(final boolean step2CompleteOrSkip) {
        this.step2CompleteOrSkip = step2CompleteOrSkip;
    }

    /**
     * @return get step1 complete ir skip
     */
    public boolean getStep1CompleteOrSkip() {
        return step1CompleteOrSkip;
    }

    /**
     * @param step1CompleteOrSkip set step1 complete or skip
     */
    public void setStep1CompleteOrSkip(final boolean step1CompleteOrSkip) {
        this.step1CompleteOrSkip = step1CompleteOrSkip;
    }

    /**
     * @return profile Pic URL
     */
    public ProfilePicURL getProfilePicURL() {
        return profilePicURL;
    }

    /**
     * @param profilePicURL set Profile Pic URL
     */
    public void setProfilePicURL(final ProfilePicURL profilePicURL) {
        this.profilePicURL = profilePicURL;
    }

    /**
     * @return default Address ID
     */
    public String getDefaultAddressId() {
        return defaultAddressId;
    }

    /**
     * @param defaultAddressId set default id
     */
    public void setDefaultAddressId(final String defaultAddressId) {
        this.defaultAddressId = defaultAddressId;
    }

    /**
     * @return current location
     */
    public CurrentLocation getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @param currentLocation set current location
     */
    public void setCurrentLocation(final CurrentLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * @return get Phone verified
     */
    public boolean getPhoneVerified() {
        return phoneVerified;
    }

    /**
     * @param phoneVerified phone verified
     */
    public void setPhoneVerified(final boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    /**
     * @return get email verified
     */
    public boolean getEmailVerified() {
        return emailVerified;
    }

    /**
     * @param emailVerified email verifed
     */
    public void setEmailVerified(final boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    /**
     * @return drinking
     */
    public String getDrinking() {
        return drinking;
    }

    /**
     * @param drinking drinking
     */
    public void setDrinking(final String drinking) {
        this.drinking = drinking;
    }

    /**
     * @return get smoking
     */
    public String getSmoking() {
        return smoking;
    }

    /**
     * @param smoking set user smoking
     */
    public void setSmoking(final String smoking) {
        this.smoking = smoking;
    }

    /**
     * @return get user body type
     */
    public String getBodyType() {
        return bodyType;
    }

    /**
     * @param bodyType set user body type
     */
    public void setBodyType(final String bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * @return get height
     */
    public String getHeight() {
        return height;
    }

    /**
     * @param height set user height
     */
    public void setHeight(final String height) {
        this.height = height;
    }

    /**
     * @return get religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion set religion
     */
    public void setReligion(final String religion) {
        this.religion = religion;
    }

    /**
     * @return get Ethnicity
     */
    public String getEthnicity() {
        return ethnicity;
    }

    /**
     * @param ethnicity set ethnicity
     */
    public void setEthnicity(final String ethnicity) {
        this.ethnicity = ethnicity;
    }

    /**
     * @return get Relationship history
     */
    public String getRelationshipHistory() {
        return relationshipHistory;
    }

    /**
     * @param relationshipHistory set Relationship history
     */
    public void setRelationshipHistory(final String relationshipHistory) {
        this.relationshipHistory = relationshipHistory;
    }

    /**
     * @return get user notification Enable
     */
    public boolean getNotificationEnable() {
        return notificationEnable;
    }

    /**
     * @param notificationEnable set notify enable
     */
    public void setNotificationEnable(final boolean notificationEnable) {
        this.notificationEnable = notificationEnable;
    }

    /**
     * @return get Direct Date Request Enable
     */
    public boolean getDirectDateRequestEnable() {
        return directDateRequestEnable;
    }

    /**
     * @param directDateRequestEnable set user direct date Request enable
     */
    public void setDirectDateRequestEnable(final boolean directDateRequestEnable) {
        this.directDateRequestEnable = directDateRequestEnable;
    }

    /**
     * @return get Privacy
     */
    public boolean getPrivacy() {
        return privacy;
    }

    /**
     * @param privacy set Privacy
     */
    public void setPrivacy(final boolean privacy) {
        this.privacy = privacy;
    }

    /**
     * @return user isDisable
     */
    public boolean getIsDisable() {
        return isDisable;
    }

    /**
     * @param isDisable set is disble
     */
    public void setIsDisable(final boolean isDisable) {
        this.isDisable = isDisable;
    }

    /**
     * @return get Language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language language
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

    /**
     * @return get First time login
     */
    public boolean getFirstTimeLogin() {
        return firstTimeLogin;
    }

    /**
     * @param firstTimeLogin set first time login
     */
    public void setFirstTimeLogin(final boolean firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }

    /**
     * @return get Last name of user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName set last name of user
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return get first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName set first name
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return get new number
     */
    public String getNewNumber() {
        return newNumber;
    }

    /**
     * @param newNumber new number
     */
    public void setNewNumber(final String newNumber) {
        this.newNumber = newNumber;
    }

    /**
     * @return list get
     */
    public List<InterestCategories> getInterestCategories() {
        return interestCategories;
    }

    /**
     * @param interestCategories list set
     */
    public void setInterestCategories(final List<InterestCategories> interestCategories) {
        this.interestCategories = interestCategories;
    }
}

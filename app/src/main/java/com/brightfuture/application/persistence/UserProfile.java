package com.brightfuture.application.persistence;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orm.SugarRecord;

/** Created by dbarczak on 19/06/2015. */
public class UserProfile extends SugarRecord<UserProfile> {

    @NonNull private String email;

    @Nullable private String firstName;

    @Nullable private String lastName;

    @Nullable private String addressOne;

    @Nullable private String addressTwo;

    @Nullable private String postCode;

    /** Empty public constructor required by Sugar ORM */
    public UserProfile (){}

    public static UserProfile createEmptyForAccount(String email){
        UserProfile userProfile = new UserProfile();
        userProfile.email = email;
        return userProfile;
    }

    public static UserProfile createNew(String email, String firstName, String lastName, String addressOne, String addressTwo, String postCode){
        UserProfile userProfile = new UserProfile();
        userProfile.email = email;
        userProfile.firstName = firstName;
        userProfile.lastName = lastName;
        userProfile.addressOne = addressOne;
        userProfile.addressTwo = addressTwo;
        userProfile.postCode = postCode;
        return userProfile;
    }

    @NonNull
    public String getOwnerEmail() {
        return email;
    }

    @Nullable
    public String getFirstName() {
        return firstName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    @Nullable
    public String getAddressOne() {
        return addressOne;
    }

    @Nullable
    public String getAddressTwo() {
        return addressTwo;
    }

    @Nullable
    public String getPostCode() {
        return postCode;
    }

    public void setFirstName(@Nullable String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    public void setAddressOne(@Nullable String addressOne) {
        this.addressOne = addressOne;
    }

    public void setAddressTwo(@Nullable String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public void setPostCode(@Nullable String postCode) {
        this.postCode = postCode;
    }
}

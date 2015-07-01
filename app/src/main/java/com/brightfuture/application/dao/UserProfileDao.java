package com.brightfuture.application.dao;

import com.brightfuture.application.persistence.UserProfile;

/** Created by dbarczak on 22/06/2015. */
public class UserProfileDao extends Preferences {

    // Prevents instantiation of the class
    private UserProfileDao () {}

    public static UserProfile getProfileForAccount(String email){
        UserProfile userProfile = (UserProfile.find(UserProfile.class, "email = ?", email)).get(0);
        return userProfile;
    }
}

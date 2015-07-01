package com.brightfuture.application.dao;

import com.brightfuture.application.persistence.RegisteredAccount;
import java.util.List;

/** Created by dbarczak on 18/06/2015. */
public class RegisteredAccountDao extends Preferences {

    /* Prevents instantiation of the class */
    private RegisteredAccountDao () {}

    /* Search database for user with email address */
    public static boolean checkEmailIfExists(String email) {
        boolean ok = false;
        List<RegisteredAccount> accounts = RegisteredAccount.find(RegisteredAccount.class, "email = ?", email);
        /* if list isn't empty */
        if(!accounts.isEmpty()){
            ok = true;
        }
        return ok;
    }
    /* Search for user in database with email and password match */
    public static boolean checkUserDetails(String email, String password) {
        boolean ok = false;
        List<RegisteredAccount> accounts = RegisteredAccount.find(RegisteredAccount.class, "email = ?", email);
        /* if list isn't empty */
        if(!accounts.isEmpty()){
            /* retrieve first result */
            if(accounts.get(0).getPassword().equals(password)){
                ok = true;
            }
        }
        return ok;
    }

    public static String getEmailFromPreferences(){
        return sharedPreferences.getString(RegisteredAccountDao.Email, "system@system.com");
    }
}

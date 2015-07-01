package com.brightfuture.application.persistence;

import android.support.annotation.NonNull;

import com.orm.SugarRecord;

/** Created by dbarczak on 17/06/2015. */

public class RegisteredAccount extends SugarRecord<RegisteredAccount> {

    @NonNull private String email;

    @NonNull private String password;

    /** Empty public constructor required by Sugar ORM */
    public RegisteredAccount (){}

    public static RegisteredAccount createNew(String email, String password){
        RegisteredAccount account = new RegisteredAccount();
        account.email = email;
        account.password = password;
        return account;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

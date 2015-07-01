package com.brightfuture.application.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.application.R;
import com.brightfuture.application.dao.RegisteredAccountDao;
import com.brightfuture.application.persistence.UserProfile;
import com.brightfuture.application.util.ValidationConstants;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

public class RegisterOptionalActivity extends AppCompatActivity {

    @NotEmpty(messageId = R.string.validation_empty_first_name)
    @RegExp(value = ValidationConstants.FULL_NAME_PATTERN, messageId = R.string.validation_valid_first_name)
    private EditText textFirstName;

    @NotEmpty(messageId = R.string.validation_valid_last_name)
    @RegExp(value = ValidationConstants.FULL_NAME_PATTERN, messageId = R.string.validation_valid_last_name)
    private EditText textLastName;

    @NotEmpty(messageId = R.string.validation_empty_address_one)
    private EditText textAddressOne;

    @NotEmpty(messageId = R.string.validation_empty_address_two)
    private EditText textAddressTwo;

    @NotEmpty(messageId = R.string.validation_empty_postcode)
    @RegExp(value = ValidationConstants.POST_CODE, messageId = R.string.validation_valid_postcode)
    private EditText textPostCode;

    private String Msg = "RegisterOptionalActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_optional);
        RegisteredAccountDao.sharedPreferences = getSharedPreferences(RegisteredAccountDao.MY_PREFERENCES, Context.MODE_PRIVATE);

        textFirstName = (EditText) findViewById(R.id.firstNameText);
        textLastName = (EditText) findViewById(R.id.lastNameText);
        textAddressOne = (EditText) findViewById(R.id.addressLine1);
        textAddressTwo = (EditText) findViewById((R.id.addressLine2));
        textPostCode = (EditText) findViewById(R.id.postcode);
    }

    public void createEmptyUserProfile(View view){
        /* If there is an email value in preferences */
        if (RegisteredAccountDao.getEmailFromPreferences().equals("system@system.com")){
            throw new IllegalStateException("Email value doesn't exists in preferences");
        }
        /* Create empty profile for account */
        UserProfile userProfile = UserProfile.createEmptyForAccount(RegisteredAccountDao.getEmailFromPreferences());
        userProfile.save();
        Log.d(Msg, "Empty profile created for " + RegisteredAccountDao.getEmailFromPreferences());

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void createNewUserProfile(View view){
        /* If there is an email value in preferences */
        if (RegisteredAccountDao.getEmailFromPreferences().equals("system@system.com")){
            throw new IllegalStateException("Email value doesn't exists in preferences");
        }
        /* If fields are validated */
        if(validateForm()){
            /* Create new profile for account */
            UserProfile userProfile = UserProfile.createNew(RegisteredAccountDao.getEmailFromPreferences(),
                                        textFirstName.getText().toString(),
                                        textLastName.getText().toString(),
                                        textAddressOne.getText().toString(),
                                        textAddressTwo.getText().toString(),
                                        textPostCode.getText().toString());
            userProfile.save();
            Log.d(Msg, "Profile created for account " + userProfile.getOwnerEmail());

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public boolean validateForm(){
        /* Invoke validation check on annotated fields */
        return FormValidator.validate(this, new SimpleErrorPopupCallback(this));
    }

    public void onBackPressed() {}
}

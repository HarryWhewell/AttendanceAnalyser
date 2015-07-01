package com.brightfuture.application.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.R;
import com.brightfuture.application.dao.RegisteredAccountDao;
import com.brightfuture.application.dao.UserProfileDao;
import com.brightfuture.application.persistence.UserProfile;

public class ProfileActivity extends AppCompatActivity {

    private String Msg = "ProfileActivity";

    private UserProfile retrievedProfile;
    private EditText textFirstName;
    private EditText textLastName;
    private EditText textAddressOne;
    private EditText textAddressTwo;
    private EditText textPostCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        RegisteredAccountDao.sharedPreferences = getSharedPreferences(RegisteredAccountDao.MY_PREFERENCES, Context.MODE_PRIVATE);
        displayHeaderMessage();
        /* Load user profile from database */
        retrievedProfile = UserProfileDao.getProfileForAccount(RegisteredAccountDao.getEmailFromPreferences());
        populateProfileFieldsFromDatabase(retrievedProfile);
        Log.d(Msg, "Profile populated from database");
    }

    public void updateProfile(View view){
        retrievedProfile.setFirstName(textFirstName.getText().toString());
        retrievedProfile.setLastName(textLastName.getText().toString());
        retrievedProfile.setAddressOne(textLastName.getText().toString());
        retrievedProfile.setAddressTwo(textAddressTwo.getText().toString());
        retrievedProfile.setPostCode(textPostCode.getText().toString());
        retrievedProfile.save();
        Toast.makeText(getApplicationContext(), "Profile has been updated", Toast.LENGTH_SHORT).show();
    }

    public void displayHeaderMessage(){
        TextView welcomeMessageView = (TextView) findViewById(R.id.profileText);
        welcomeMessageView.setText("Profile for " +RegisteredAccountDao.getEmailFromPreferences());
    }

    public void populateProfileFieldsFromDatabase(UserProfile userProfile){
        textFirstName = (EditText) findViewById(R.id.firstNameText);
        textFirstName.setText(userProfile.getFirstName());

        textLastName = (EditText) findViewById(R.id.lastNameText);
        textLastName.setText(userProfile.getLastName());

        textAddressOne = (EditText) findViewById(R.id.addressLine1);
        textAddressOne.setText(userProfile.getAddressOne());

        textAddressTwo = (EditText) findViewById(R.id.addressLine2);
        textAddressTwo.setText(userProfile.getAddressTwo());

        textPostCode = (EditText) findViewById(R.id.postcode);
        textPostCode.setText(userProfile.getPostCode());
    }
}

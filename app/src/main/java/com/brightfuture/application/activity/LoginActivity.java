package com.brightfuture.application.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.application.R;
import com.brightfuture.application.dao.RegisteredAccountDao;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

public class LoginActivity extends Activity {

    @NotEmpty(messageId = R.string.validation_empty_email)
    private EditText emailText;

    @NotEmpty(messageId = R.string.validation_empty_password)
    private EditText passwordText;

    private String Msg = "LoginActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        RegisteredAccountDao.sharedPreferences = getSharedPreferences(RegisteredAccountDao.MY_PREFERENCES, Context.MODE_PRIVATE);

        emailText = (EditText) findViewById(R.id.emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);
    }

    public void logInUser(View view){
        /* If fields are validated */
        if(validateForm()){
            /* If details exist in database */
            if (!RegisteredAccountDao.checkUserDetails(emailText.getText().toString(), passwordText.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Account doesn't exists", Toast.LENGTH_SHORT).show();
            } else {
                /* Save user email in shared preferences */
                SharedPreferences.Editor editor = RegisteredAccountDao.sharedPreferences.edit();
                editor.putString(RegisteredAccountDao.Email, emailText.getText().toString());
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                Log.d(Msg, "Successfully logged in");
            }
        }
    }

    public void goToRegister(View view){
        emailText.setText("");
        passwordText.setText("");
        Intent intent = new Intent(this, RegisterRequiredActivity.class);
        startActivity(intent);
        Log.d(Msg, "Moving to Registration Page");
    }

    public boolean validateForm(){
        /* Invoke validation check on annotated fields */
        return FormValidator.validate(this, new SimpleErrorPopupCallback(this));
    }

    public void onBackPressed() {}
}

package com.brightfuture.application.activity;

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
import com.brightfuture.application.persistence.RegisteredAccount;
import com.brightfuture.application.util.ValidationConstants;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

public class RegisterRequiredActivity extends AppCompatActivity {

    @NotEmpty(messageId = R.string.validation_empty_email)
    @RegExp(value = ValidationConstants.EMAIL_PATTERN, messageId = R.string.validation_valid_email)
    private EditText textEmail;
    @NotEmpty(messageId = R.string.validation_empty_password)
    @RegExp(value = ValidationConstants.PASSWORD_PATTERN, messageId = R.string.validation_valid_password)
    private EditText textPassword;

    private EditText textConfirmPassword;
    /* Log cannot hold long messages, that's why its outside */
    private String Msg = "RegisteredRequiredActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_required);
        RegisteredAccountDao.sharedPreferences = getSharedPreferences(RegisteredAccountDao.MY_PREFERENCES, Context.MODE_PRIVATE);

        textEmail = (EditText) findViewById(R.id.emailText);
        textPassword = (EditText) findViewById(R.id.passwordText);
        textConfirmPassword = (EditText) findViewById(R.id.passwordConfirmText);
    }

    public void createAccountAndNext(View view){
        String email = textEmail.getText().toString();
        String password = textPassword.getText().toString();
        String confirmPassword = textConfirmPassword.getText().toString();
        /* If fields are validated */
        if(validateForm()){
            /* If email already exists */
            if(RegisteredAccountDao.checkEmailIfExists(email)){
                Toast.makeText(getApplicationContext(), "Email already exists, please use different one", Toast.LENGTH_SHORT).show();
            }
            /* If password fields doesn't match */
            else if(!password.equals(confirmPassword)){
                Toast.makeText(getApplicationContext(), "Password fields must match", Toast.LENGTH_SHORT).show();
            }
            else{
                /* Register new user account */
                RegisteredAccount account = RegisteredAccount.createNew(email, password);
                account.save();
                Log.d(Msg, "New account created " +account.getEmail());

                /* Save user email in shared preferences */
                SharedPreferences.Editor editor = RegisteredAccountDao.sharedPreferences.edit();
                editor.putString(RegisteredAccountDao.Email, email);
                editor.apply();

                Intent intent = new Intent(this, RegisterOptionalActivity.class);
                startActivity(intent);
            }
        }
    }

    public boolean validateForm(){
        /* Invoke validation check on annotated fields */
        return FormValidator.validate(this, new SimpleErrorPopupCallback(this));
    }
}

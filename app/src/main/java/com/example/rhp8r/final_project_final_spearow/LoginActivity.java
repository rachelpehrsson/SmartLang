package com.example.rhp8r.final_project_final_spearow;

/**
 * Created by Shivani on 11/30/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;

public class LoginActivity extends Activity {

    // Email, password edittext
    EditText txtUsername, txtPassword;

    AlertDialog alert;

    // Session Manager Class
    SessionManager session;
    Button btnLogin;
    Button btnsignup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Manager
        session = new SessionManager(getApplicationContext());
        alert = new AlertDialog.Builder(LoginActivity.this).create();
        // Email, Password input text
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        // Login button
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnsignup = (Button) findViewById(R.id.btnsignup);

        //default user
        session.createLoginSession("shiv", "shiv");

        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                if(username.trim().length() > 0 && password.trim().length() > 0){
                    if(session.loadFromSharedPreferences().containsKey(username)&& session.loadFromSharedPreferences().containsValue(password)) {//if(username.equals("shiv") && password.equals("shiv")){
                        //session.createLoginSession("Shivani", "shivyn21@gmail.com");
                        session.createLoginSession(username, password);
                        Intent i = new Intent(getApplicationContext(), LanguagesActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        alert.setTitle("Login failed");
                        alert.setMessage("Username/Password is incorrect");
                        alert.show();
                    }
                }else{
                    alert.setTitle("Login failed");
                    alert.setMessage("Please enter username and password");
                    alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alert.show();
                }
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                if(username.trim().length() > 0 && password.trim().length() > 0){
                    //if(username.equals("shiv") && password.equals("shiv")){
                        session.saveToSharedPreferences(username,password);
                        session.createLoginSession(username, "");
                        Intent i = new Intent(getApplicationContext(), LanguagesActivity.class);
                        startActivity(i);
                        finish();
                    //}

                    //}
                }else{
                    alert.setTitle("Login failed");
                    alert.setMessage("Please enter username and password");
                    alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alert.show();
                }
            }
        });
    }
}
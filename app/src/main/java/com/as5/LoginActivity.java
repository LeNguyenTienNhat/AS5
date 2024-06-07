package com.as5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "my_prefs";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_LOGIN_STATUS = "login_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sf = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        boolean loginStatus = sf.getBoolean(KEY_LOGIN_STATUS, false);
        if (loginStatus) {
            LoginActivity.this.navigateToHomepage();
        }
        Button signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(LoginActivity.this::onSignIn);
    }
    public void onSignIn(View v) {
        EditText usernameInputField = findViewById(R.id.user);
        EditText passwordInputField = findViewById(R.id.password);
        String username = usernameInputField.getText().toString();
        String password = passwordInputField.getText().toString();
        if (checkLogin(username,password)) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_USERNAME,username);
            editor.putString(KEY_PASSWORD,password);
            editor.putBoolean(KEY_LOGIN_STATUS,true);
            editor.apply();
            LoginActivity.this.navigateToHomepage();
        }
    }
    private boolean checkLogin(String username, String password) {
        return username.equals("admin") && password.equals("123456");
    }
    private void navigateToHomepage() {
        Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
        LoginActivity.this.finish();
    }
}
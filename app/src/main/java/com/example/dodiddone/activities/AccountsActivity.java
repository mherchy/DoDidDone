package com.example.dodiddone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;


import com.example.dodiddone.R;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class AccountsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
    }

    public void onClickAuth(View view) {
        String mail = ((EditText)findViewById(R.id.auth_mail)).getText().toString();
        String pwd = ((EditText)findViewById(R.id.auth_pwd)).getText().toString();
        request(mail, pwd);
    }


    private void request(final String email, final String password) {

        new Thread(new Runnable(){
            @Override
            public void run() {
                URL url = null;
                HttpURLConnection urlConnection = null;
                InputStreamReader reader = null;
                try {
                    url = new URL("http://10.0.2.2:8008/login");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    reader = new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8);
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    String token = json.get("token").getAsString();
                    createAccount(email, password, token);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            }
        }).start();

    }

    public void createAccount(String email, String password, String authToken) {
        Account account = new Account(email, "com.example.dodiddone");

        AccountManager am = AccountManager.get(this);
        am.addAccountExplicitly(account, password, null);
        am.setAuthToken(account, "full_access", authToken);

        finish();
    }




}

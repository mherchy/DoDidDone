package com.example.dodiddone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.dodiddone.R;

public class AccountsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
    }

    public void onClickAuth(View view) {
        String mail = ((EditText)findViewById(R.id.auth_mail)).getText().toString();
        String pwd = ((EditText)findViewById(R.id.auth_pwd)).getText().toString();
        String authToken = "0123456789";
        createAccount(mail,pwd,authToken);
    }

    public void createAccount(String email, String password, String authToken) {
        Account account = new Account(email, "com.example.dodiddone");

        AccountManager am = AccountManager.get(this);
        am.addAccountExplicitly(account, password, null);
        am.setAuthToken(account, "full_access", authToken);

        finish();
    }
}

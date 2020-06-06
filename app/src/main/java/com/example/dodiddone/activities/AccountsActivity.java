package com.example.dodiddone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dodiddone.R;
import com.example.dodiddone.services.RequestManager;

import org.json.JSONObject;

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

        request(email, password);

        finish();
    }

    private void request(String email, String password) {
        String url = "http://10.0.2.2:8008/login";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Login request", response.toString());
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Access the RequestQueue through your singleton class.
        RequestManager.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
}

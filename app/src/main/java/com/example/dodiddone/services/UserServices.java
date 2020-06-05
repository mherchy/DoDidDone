package com.example.dodiddone.services;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class UserServices {

    private Context mContext;

    public UserServices(Context mContext) {
        this.mContext = mContext;
    }


    public boolean isUserConnected() {
        return getUserAccount() != null;
    }


    public Account getUserAccount() {
        AccountManager accountManager = AccountManager.get(mContext);
        Account[] accounts = accountManager.getAccountsByType("com.example.dodiddone");
        if(accounts.length > 0) {
            return accounts[0];
        }
        return null;
    }

}

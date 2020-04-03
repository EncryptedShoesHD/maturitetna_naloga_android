package com.example.newapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }
    public void setUsername(String username) {
        prefs.edit().putString("username", username).apply();
    }

    public String getUsername() {
        String username = prefs.getString("username","");
        return username;
    }

    public void setPassword(String password) {
        prefs.edit().putString("password", password).apply();
    }

    public String getPassword() {
        String password = prefs.getString("password","");
        return password;
    }
}

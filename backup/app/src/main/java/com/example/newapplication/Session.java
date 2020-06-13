package com.example.newapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;

public class Session {

    private SharedPreferences prefs;
    Context context;
    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
        context = cntx;
    }
    public void setUsername(String username) {
        prefs.edit().putString("username", username).apply();
    }

    public String getUsername() {
        String username = prefs.getString("username","");
        return username;
    }
    public void setUserId(String userID) {
        prefs.edit().putString("userID", userID).apply();
    }

    public String getUserId() {
        String userID = prefs.getString("userID","");
        return userID;
    }
    public void setName(String name) {
        prefs.edit().putString("name", name).apply();
    }

    public String getName() {
        String name = prefs.getString("name","");
        return name;
    }
    public void setSurname(String surname) {
        prefs.edit().putString("surname", surname).apply();
    }

    public String getSurname() {
        String surname = prefs.getString("surname","");
        return surname;
    }

    public void setEmail(String email) {
        prefs.edit().putString("email", email).apply();
    }

    public String getEmail() {
        String email = prefs.getString("email","");
        return email;
    }
    public void setGrades(String grade) {
        prefs.edit().putString("grade", grade).apply();
    }

    public String getGrades() {
        String grade = prefs.getString("grade","");
        return grade;
    }

    protected void onDestroy() {
        context.getSharedPreferences("YOUR_PREFS", 0).edit().clear().apply();
    }
}

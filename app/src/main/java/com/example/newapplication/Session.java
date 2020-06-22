package com.example.newapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;

public class Session {

    SharedPreferences prefs;
    Context context;
    public static String PREF_NAME = "prefs";
    public static int PREF_MODE = 0;
    SharedPreferences.Editor editor;

    public Session(Context cntx) {
        this.context = cntx;
        prefs = context.getSharedPreferences(PREF_NAME, PREF_MODE);
        editor = prefs.edit();
    }
    public void setUsername(String username) {
        editor.putString("username", username);
        editor.commit();
    }

    public String getUsername() {
        String username = prefs.getString("username",null);
        return username;
    }
    public void setUserId(String userID) {
        editor.putString("userID", userID);
        editor.commit();
    }

    public String getUserId() {
        String userID = prefs.getString("userID",null);
        return userID;
    }
    public void setName(String name) {
        editor.putString("name", name);
        editor.commit();
    }

    public String getName() {
        String name = prefs.getString("name",null);
        return name;
    }
    public void setSurname(String surname) {
        editor.putString("surname", surname);
        editor.commit();
    }

    public String getSurname() {
        String surname = prefs.getString("surname",null);
        return surname;
    }

    public void setEmail(String email) {
        editor.putString("email", email);
        editor.commit();
    }

    public String getEmail() {
        String email = prefs.getString("email",null);
        return email;
    }
    public void setGrades(String grade) {
        editor.putString("grade", grade);
        editor.commit();
    }

    public String getGrades() {
        String grade = prefs.getString("grade",null);
        return grade;
    }

    public void destroySession() {
        editor.clear();
        editor.commit();
        prefs.edit().clear();
        prefs.edit().commit();
    }



}

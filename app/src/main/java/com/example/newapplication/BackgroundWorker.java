package com.example.newapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog.Builder builder;
    Session session;


    BackgroundWorker (Context ctx){
        context = ctx;
        session = new Session(ctx);
    }

    @Override
    protected String doInBackground(String... voids) {
        /*
        String type = voids[0];
        String username = voids[1];
        String password = voids[2];
        */

        //String login_url = "http://redovalnica.ga/android/login.php";
        String login_url = "http://192.168.64.115/A+_web/android/login.php";
        //String login_url = "http://redovalnica.ga/android/login.php";
        //String getUserData_url = "http://192.168.64.115/A+_web/android/getUserData.php";

        //String login_url = "http://redovalnica.ga/member/login.php";

        if(voids[0].equals("login")){
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(voids[1], "UTF-8")+"&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(voids[2], "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) result += line;
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                String check="Login successful";
                if(result.trim().equals(check)){
                    session.setUsername(voids[1]);
                    return result;
                }else return result;
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        /*else if(voids[0].equals("getUserData")){
            try {
                URL url = new URL(getUserData_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(session.getUsername(), "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) result += line;
                JSONArray jsonArray = new JSONArray(result);
                JSONObject json = jsonArray.getJSONObject(1);
                session.setUserId(json.getString("UserID"));
                session.setName(json.getString("Name"));
                session.setSurname(json.getString("Surname"));
                session.setEmail(json.getString("Email")) ;
                session.setUsername(json.getString("Username"));
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                session.setUsername(voids[1]);
                return result;
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/return null;
    }

    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(context)
        .setTitle("Login status")
        .setMessage("Something went wrong");

    }

    @Override

    protected void onPostExecute(String results) {
        builder.setMessage(results)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent myIntent = new Intent(context, FirstPageActivity.class);
                context.startActivity(myIntent);
            }
        })
        .show();
    }



        @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

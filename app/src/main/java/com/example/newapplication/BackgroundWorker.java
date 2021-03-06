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
    String type;


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

        String login_url = "http://redovalnica.ga/android/login.php";
        //String login_url = "http://192.168.64.120/A+_web/android/login.php";
        String register_url = "http://redovalnica.ga/android/register.php";

        //String login_url = "http://redovalnica.ga/member/login.php";

        if(voids[0].equals("login")){
            try {
                type = "login";
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
        else if(voids[0].equals("register")){
            try {
                type = "register";
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(voids[1], "UTF-8")+"&"+URLEncoder.encode("surname", "UTF-8")+"="+URLEncoder.encode(voids[2], "UTF-8") +"&"+URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(voids[3], "UTF-8") +"&"+URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(voids[4], "UTF-8") +"&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(voids[5], "UTF-8");
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
                return result;
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }return null;
    }

    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(context)
        .setTitle("Register status")
        .setMessage("Something went wrong");

    }

    @Override

    protected void onPostExecute(String results) {
            builder.setMessage(results);
            if(type == "login") {
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myIntent = new Intent(context, FirstPageActivity.class);
                        context.startActivity(myIntent);
                    }
                });
            }
            builder.show();

    }



        @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

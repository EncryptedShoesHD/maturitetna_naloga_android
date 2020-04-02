package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RedovalnicaFragment extends Fragment {
    String login_url = "http://redovalnica.ga/android/grades.php";
    String[] gradeTable = {"SubjectID","Grade","Type","DateReceived"};
    /*String[] subjectID = {"SLO","MAT","ŠVZ"};
    Integer[] grade = {1,2,3};
    String[] type = {"ustno","pisno","izdelek"};
    String[] date_s = {"2011-01-18 00:00:00.0","2011-01-18 00:00:00.0","2011-01-18 00:00:00.0"};
    Date[] DateReceived = {};
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    String array = "{'SubjectID':0,'Grade':0,'Type':'no data','DateReceived':'2020-04-02 15:51:00'}";*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_redovalnica, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listViewRedovalnica);

        ArrayAdapter <String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_selectable_list_item,
                gradeTable
                );
        listView.setAdapter(listViewAdapter);

        return view;
    }
    public static void parseProfilesJson(String the_json){
        ArrayList<JSONObject> arrays = new ArrayList<JSONObject>();
        try {
            JSONObject myjson = new JSONObject(the_json);
            JSONArray the_json_array = myjson.getJSONArray("profiles");
            int size = the_json_array.length();
            for (int i = 0; i < size; i++) {
                JSONObject another_json_object = the_json_array.getJSONObject(i);
                arrays.add(another_json_object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            JSONObject[] jsons = new JSONObject[arrays.size()];
            arrays.toArray(jsons);
        }
    }
}

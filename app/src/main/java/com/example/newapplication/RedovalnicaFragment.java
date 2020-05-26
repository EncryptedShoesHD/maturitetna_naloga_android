package com.example.newapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static java.lang.Integer.parseInt;

public class RedovalnicaFragment extends Fragment {
    /*String login_url = "http://redovalnica.ga/android/grades.php";
    String[] gradeTable = {"SubjectID","Grade","Type","DateReceived"};
    /*String[] subjectID = {"SLO","MAT","ŠVZ"};
    Integer[] grade = {1,2,3};
    String[] type = {"ustno","pisno","izdelek"};
    String[] date_s = {"2011-01-18 00:00:00.0","2011-01-18 00:00:00.0","2011-01-18 00:00:00.0"};
    Date[] DateReceived = {};
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    String array = "{'SubjectID':0,'Grade':0,'Type':'no data','DateReceived':'2020-04-02 15:51:00'}";*/

    View view;
    Session session;
    JSONArray jsonArray;
    JSONObject jsonObject;
    String shortCode;
    Integer grade;
    TextView shortCode_label,grade_label;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_redovalnica_1, container, false);
        super.onCreate(savedInstanceState);
        session = new Session(getContext());
        GetArray getArray = new GetArray(getActivity());
        String type = "grades";
        getArray.execute(type);
        TableLayout tableLayout = (TableLayout)view.findViewById(R.id.tableLayout);


        TableRow tr_head = new TableRow(getContext());
        tr_head.setId(View.generateViewId());
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        try {
            jsonArray = new JSONArray(session.getGrades());
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                shortCode = jsonObject.getString("Shortcode");
                grade = jsonObject.getInt("Grade");


                shortCode_label = new TextView(getActivity());
                shortCode_label.setId(View.generateViewId());
                shortCode_label.setText(""+shortCode);
                shortCode_label.setTextColor(Color.WHITE);
                shortCode_label.setPadding(5, 5, 5, 5);
                tr_head.addView(shortCode_label);

                grade_label = new TextView(getActivity());
                grade_label.setId(View.generateViewId());
                grade_label.setText(""+grade);
                grade_label.setTextColor(Color.WHITE);
                grade_label.setPadding(5, 5, 5, 5);
                tr_head.addView(grade_label);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tableLayout.addView(tr_head,new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        return view;
    }
    public static void parseProfilesJson(String the_json){
       /* ArrayList<JSONObject> arrays = new ArrayList<JSONObject>();
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
        }*/
    }
}

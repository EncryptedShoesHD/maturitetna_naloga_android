package com.example.newapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.max;
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

    Session session;
    JSONArray jsonArray;
    JSONObject jsonObject;
    String shortCode;
    Integer grade;
    TextView shortCode_label,grade_label;
    Button button;
    TableLayout tableLayout;
    TableRow tr_head;
    Boolean commited;
    TableRow row;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_redovalnica_1, container, false);
        super.onCreate(savedInstanceState);
        session = new Session(getContext());
        GetArray getArray = new GetArray(getActivity());
        final String[] type = {"grades"};
        getArray.execute(type[0]);
        tableLayout = (TableLayout)view.findViewById(R.id.tableLayout);
        button = (Button)view.findViewById(R.id.button_dodaj_oceno);

        try {
            jsonArray = new JSONArray(session.getGrades());
            List<String> predmeti = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                if(!predmeti.contains(jsonArray.getJSONObject(i).getString("Shortcode"))) {
                   predmeti.add(jsonArray.getJSONObject(i).getString("Shortcode"));
                }
            }
            jsonObject = jsonArray.getJSONObject(0);

            /*for (int i = 0; i < jsonArray.length(); i++){
                String predmet = predmeti.get(i);
                for (int j = 0; j < jsonArray.length(); j++){
                    ocene.add(jsonArray.getJSONObject(i).getString("Grade"));
                }
            }*/
            for (int i = 0; i < predmeti.size(); i++) {
                tr_head = new TableRow(getContext());
                tr_head.setId(View.generateViewId());
                tr_head.setBackgroundColor(Color.GRAY);
                tr_head.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                shortCode = predmeti.get(i);
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
                if(tr_head.getParent() != null) {
                    ((ViewGroup)tr_head.getParent()).removeView(tr_head);
                }
                tr_head.addView(grade_label);
                tableLayout.addView(tr_head,new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commited = true;
                    EditText shortCodeView = new EditText(getContext());
                    final EditText gradeView = new EditText(getContext());
                    Button buttonDodaj = new Button(getContext());
                    gradeView.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "5")});

                     row = new TableRow(getContext());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(lp);
                    shortCode = "SLO";
                    grade = 3;

                    shortCodeView.setText(shortCode);
                    shortCodeView.setMaxLines(1);
                    shortCodeView.setAllCaps(true);
                    shortCodeView.setId(View.generateViewId());

                    gradeView.setInputType(grade);
                    gradeView.setMaxLines(1);
                    gradeView.setId(View.generateViewId());

                    buttonDodaj.setText("ADD");
                    buttonDodaj.setId(View.generateViewId());
                    buttonDodaj.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            commited = false;
                            String type = "pushGrades";
                            GetArray getArray = new GetArray(getActivity());
                            getArray.execute(type,gradeView.getText().toString());
                            Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
                            row = (TableRow) view.findViewById(View.generateViewId());
                        }
                    });
                    if(commited) {
                        row.addView(shortCodeView);
                        row.addView(gradeView);
                        row.addView(buttonDodaj);
                        tableLayout.addView(row, 0);
                    }
            }
        });
        return view;
    }

    /*public static void parseProfilesJson(String the_json){
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
    }*/
}

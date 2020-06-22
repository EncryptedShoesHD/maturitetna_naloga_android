package com.example.newapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UrnikFragment extends Fragment {

    Session session;
    JSONArray jsonArray;
    JSONObject jsonObject;
    String shortCode;
    Integer grade;
    TextView shortCode_label, grade_label;
    Button button;
    TableLayout tableLayout;
    TableRow tr_head;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_redovalnica_1, container, false);
        super.onCreate(savedInstanceState);
        session = new Session(getContext());

        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);
        button = (Button) view.findViewById(R.id.button_dodaj_oceno);


        try {
            jsonArray = new JSONArray(session.getGrades());
            List<String> predmeti = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                if (!predmeti.contains(jsonArray.getJSONObject(i).getString("Shortcode"))) {
                    predmeti.add(jsonArray.getJSONObject(i).getString("Shortcode"));
                }
            }

            /*for (int i = 0; i < jsonArray.length(); i++){
                String predmet = predmeti.get(i);
                for (int j = 0; j < jsonArray.length(); j++){
                    ocene.add(jsonArray.getJSONObject(i).getString("Grade"));
                }
            }*/
            for (int i = 0; i < predmeti.size(); i++) {
                //type = "requestGrade";
                //getArray.execute(type, predmeti.get(i));
                tr_head = new TableRow(getContext());
                tr_head.setId(View.generateViewId());
                tr_head.setBackgroundColor(Color.GRAY);
                tr_head.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                jsonObject = jsonArray.getJSONObject(i);
                shortCode = jsonObject.getString("Shortcode");
                grade = jsonObject.getInt("Grade");


                shortCode_label = new TextView(getActivity());
                shortCode_label.setId(View.generateViewId());
                shortCode_label.setText("" + shortCode);
                shortCode_label.setTextColor(Color.WHITE);
                shortCode_label.setPadding(5, 5, 5, 5);
                tr_head.addView(shortCode_label);

                grade_label = new TextView(getActivity());
                grade_label.setId(View.generateViewId());
                grade_label.setText("" + grade);
                grade_label.setTextColor(Color.WHITE);
                grade_label.setPadding(5, 5, 5, 5);
                if (tr_head.getParent() != null) {
                    ((ViewGroup) tr_head.getParent()).removeView(tr_head);
                }
                tr_head.addView(grade_label);
                tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText shortCodeView = new EditText(getContext());
                EditText gradeView = new EditText(getContext());
                gradeView.setFilters(new InputFilter[]{new InputFilterMinMax("1", "5")});

                TableRow row = new TableRow(getContext());
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                shortCode = "SLO";
                grade = 3;

                shortCodeView.setText(shortCode);
                shortCodeView.setMaxLines(1);
                shortCodeView.setAllCaps(true);

                gradeView.setInputType(grade);
                gradeView.setMaxLines(1);

                row.addView(shortCodeView);
                row.addView(gradeView);
                tableLayout.addView(row, 0);
            }
        });
        return view;
    }
}

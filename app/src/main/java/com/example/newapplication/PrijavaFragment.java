package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PrijavaFragment extends Fragment {
    @Nullable


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_prijava, container, false);

        super.onCreate(savedInstanceState);
        TextView ustvariNovRacun;
        Button button;

            ustvariNovRacun = view.findViewById(R.id.textViewUstvariNovRacun);
            button = view.findViewById(R.id.buttonLogin);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Main2Activity.class );
                    startActivity(intent);
                }
            });
                 return view;


        };

}

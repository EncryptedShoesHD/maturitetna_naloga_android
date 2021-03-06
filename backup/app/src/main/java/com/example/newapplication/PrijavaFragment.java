package com.example.newapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PrijavaFragment extends Fragment {
    @Nullable
    Button button;
    EditText usernameEditText, passwordEitText;
    TextView textviewNovRacun;
    Session session;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_prijava, container, false);

        super.onCreate(savedInstanceState);
        TextView ustvariNovRacun;
        ustvariNovRacun = view.findViewById(R.id.textViewUstvariNovRacun);
        usernameEditText = (EditText) view.findViewById(R.id.editTextUsername1);
        passwordEitText = (EditText) view.findViewById(R.id.editTextPassword1);
        textviewNovRacun =(TextView) view.findViewById(R.id.textViewUstvariNovRacun);
        button = view.findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEitText.getText().toString();
                String type = "login";
                BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                backgroundWorker.execute(type, username, password);
            }

        });


        textviewNovRacun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new RegistracijaFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_top_conatiner, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }

        });
        return view;


        };
}

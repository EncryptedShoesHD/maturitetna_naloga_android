package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    TextView editTextImeinPriimek,editTextEpostniNaslov,editTextUporabniskoiIme;
    Button buttonOdjava,buttonIzbrisi;
    private Session session;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        session = new Session(getContext());
        /*String type = "getUserData";
        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
        backgroundWorker.execute(type);*/
        GetArray getArray = new GetArray(getActivity());
        String type = "userData";
        getArray.execute(type);
        editTextImeinPriimek = (TextView)view.findViewById(R.id.editTextImeInPriimek);
        editTextImeinPriimek.setText(session.getName());
        editTextEpostniNaslov = (TextView)view.findViewById(R.id.editTextEpostniNaslov);
        editTextEpostniNaslov.setText(session.getEmail());
        editTextUporabniskoiIme = (TextView)view.findViewById(R.id.editTextUporabniskoiIme);
        editTextUporabniskoiIme.setText(session.getUsername());
        buttonOdjava = (Button)view.findViewById(R.id.buttonOdjava);
        buttonOdjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.onDestroy();
                Intent intent = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intent);
            }
        });
        buttonIzbrisi = (Button)view.findViewById(R.id.buttonIzbrisi);
        buttonIzbrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Delete your account on our official website", Toast.LENGTH_LONG).show();
            }
        });

        //((FirstPageActivity) getActivity()).getResult();
        return view;
    }
}

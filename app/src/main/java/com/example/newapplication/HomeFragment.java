package com.example.newapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    EditText usernameEditText,editTextEpostniNaslov,editTextUporabniskoiIme;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        usernameEditText = (EditText)view.findViewById(R.id.editTextImeInPriimek);
        editTextEpostniNaslov = (EditText)view.findViewById(R.id.editTextImeInPriimek);
        editTextUporabniskoiIme = (EditText)view.findViewById(R.id.editTextImeInPriimek);

        //((FirstPageActivity) getActivity()).getResult();
        return view;
    }
}

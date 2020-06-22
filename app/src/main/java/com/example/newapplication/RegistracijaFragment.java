package com.example.newapplication;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class RegistracijaFragment extends Fragment {
    Button buttonregister;
    EditText nameEditText, surnameEditText, usernameEditText, emailEditText, passwordEditText, passwordConfEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_registracija, container, false);

        super.onCreate(savedInstanceState);
        nameEditText = (EditText) view.findViewById(R.id.editTextIme);
        surnameEditText = (EditText) view.findViewById(R.id.editTextPriimek);
        usernameEditText = (EditText) view.findViewById(R.id.editTextUporabniskoIme2);
        emailEditText = (EditText) view.findViewById(R.id.editTextEmail2);
        passwordEditText = (EditText) view.findViewById(R.id.editTextGeslo);
        passwordConfEditText = (EditText) view.findViewById(R.id.editTextGesloAgain);
        buttonregister = (Button) view.findViewById(R.id.buttonRegisterMe);
        passwordEditText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    passwordEditText.setInputType( InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    passwordEditText.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                }
                return true;
            }
        });
        passwordConfEditText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(passwordConfEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    passwordConfEditText.setInputType( InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    passwordConfEditText.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                }
                return true;
            }
        });
        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidation()){
                    String name = nameEditText.getText().toString().trim();
                    String surname = surnameEditText.getText().toString().trim();
                    String username = usernameEditText.getText().toString().trim();
                    String email = emailEditText.getText().toString().trim();
                    String password = passwordEditText.getText().toString().trim();
                    String type = "register";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                    backgroundWorker.execute(type, name,surname,  username, email,  password);
                }
            }
        });
        return view;
    }

    public boolean checkValidation() {
        String name = nameEditText.getText().toString().trim();
        String surname = surnameEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String passwordConf = passwordConfEditText.getText().toString().trim();


        if (name.length() <= 0) {
            nameEditText.requestFocus();
            nameEditText.setError("Enter name");
            return false;
        } else if (surname.length() <= 0) {
            surnameEditText.requestFocus();
            surnameEditText.setError("Enter surname");
            return false;

        } else if (username.length() <= 0) {
            usernameEditText.requestFocus();
            usernameEditText.setError("Enter username");
            return false;

        }else if (username.length() < 5) {
            usernameEditText.requestFocus();
            usernameEditText.setError("Username should be longer");
            return false;

        }else if (email.length() <= 0) {
            emailEditText.requestFocus();
            emailEditText.setError("Enter email");
            return false;

        } else if (password.length() <= 0) {
            passwordEditText.requestFocus();
            passwordEditText.setError("Enter password");
            return false;

        } else if (password.length() < 5) {
        passwordEditText.requestFocus();
        passwordEditText.setError("Password should be longer");
        return false;

        } else if (passwordConf.compareTo(password) != 0) {
            passwordConfEditText.requestFocus();
            passwordConfEditText.setError("Passwords don't match");
            return false;

        } else {
            return true;
        }
    }
}
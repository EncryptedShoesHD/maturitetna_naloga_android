package com.example.newapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Prijava");

        BottomNavigationView topNav = findViewById(R.id.top_navigation);
        topNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_top_conatiner,new PrijavaFragment()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.nav_Prijava:
                    selectedFragment = new PrijavaFragment();
                    break;
                case R.id.nav_Onas:
                    selectedFragment = new OnasFragment();
                    break;
                case R.id.nav_Kontakt:
                    selectedFragment = new KontaktFragment();
                    break;
                case R.id.nav_Registracija:
                    selectedFragment = new RegistracijaFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_top_conatiner,selectedFragment).commit();

            return true;
        };
    };

}

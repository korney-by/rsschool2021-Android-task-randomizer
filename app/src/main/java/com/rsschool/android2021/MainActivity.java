package com.rsschool.android2021;


import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements ShowFragmentsInterface {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        // TODO: implement it
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment);
        transaction.commit();
    }

    public void showFirstFragment(int previousNumber) {
        openFirstFragment(previousNumber);
    }

    public void showSecondFragment(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void onBackPressed() {
        Button buttonBack = findViewById(R.id.back);
        if (buttonBack == null) {
            super.onBackPressed();
        } else {
            buttonBack.callOnClick();
        }
    }
}



package com.study.chazo.naverbandanalysis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.study.chazo.naverbandanalysis.auth.view.LoginActivity;

import static android.content.Intent.ACTION_MAIN;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLoginActivity();
        setContentView(R.layout.activity_main);
    }

    private void checkLoginActivity(){
        if(getIntent() != null && getIntent().getAction() != null && getIntent().getAction().equals(ACTION_MAIN)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}

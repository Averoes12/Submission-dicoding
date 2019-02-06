package com.averoes.viewandviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle("Huawei Nova 3i");
        }
    }
}

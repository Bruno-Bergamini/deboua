package com.example.deboua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_time_feeling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_feeling);
    }
    public void nextButton(View view) {
        Intent intent = new Intent(this, activity_welcome.class);
        startActivity(intent);
    }
}
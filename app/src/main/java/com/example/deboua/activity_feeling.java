package com.example.deboua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.example.deboua.ui.Database;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class activity_feeling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling);
    }

    public void openConfirmationDialog(View view) {
        System.out.println("aaaaaaa");
    }
}
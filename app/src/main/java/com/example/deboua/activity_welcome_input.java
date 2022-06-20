package com.example.deboua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.example.deboua.ui.Database;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class activity_welcome_input extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_input);
    }

    public void nextButton(View view) {
        TextInputEditText messageView = (TextInputEditText) findViewById(R.id.message);
        String message = messageView.getText().toString();
        System.out.println(message);
        if (message != "" && message != null) {
            Intent intent = new Intent(this, list_of_feelings.class);
            Feeling feeling = new Feeling(message, new Date().getTime());
            Database db = new Database(this);
            db.addFeeling(feeling);
            startActivity(intent);
        }
    }
}
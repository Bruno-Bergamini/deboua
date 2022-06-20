package com.example.deboua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.deboua.ui.Database;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startButton(View view) {
        Intent intent = new Intent(this, activity_welcome.class);
        startActivity(intent);
    }

    public static class activity_feeling extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome_input);
        }

        public void nextButton(View view) {
            Intent intent = new Intent(this, list_of_feelings.class);
            TextInputEditText messageView = (TextInputEditText) findViewById(R.id.message);
            String message = messageView.getText().toString();
            Feeling feeling = new Feeling(message, new Date().getTime());
            Database db = new Database(this);
            db.addFeeling(feeling);
            startActivity(intent);
        }
    }
}
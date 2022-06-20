package com.example.deboua;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deboua.ui.Database;

import java.text.SimpleDateFormat;
import java.util.Date;

public class list_of_feelings extends AppCompatActivity {

    AlertDialog alert;
    String selectedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Database db = new Database(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deseja mesmo deletar?");
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.out.println(selectedCard);
                db.getWritableDatabase().delete("feeling",  "feeling_id = ?", new String[] {selectedCard});
                db.close();
                loadFeelings("select * from feeling order by date desc");
                dialog.dismiss();
            }
        });
        alert = builder.create();
        setContentView(R.layout.activity_list_of_feelings);
        String query = "select * from feeling";
        loadFeelings(query);
    }

    void loadFeelings(String query) {
        LinearLayout listOfFeelings = findViewById(R.id.listOfFeelings);
        listOfFeelings.removeAllViews();
        Database db = new Database(this);
        Cursor cursor = db.getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                @SuppressLint("Range") String message = cursor.getString(cursor.getColumnIndex("message"));
                @SuppressLint("Range") long dateMili = cursor.getLong(cursor.getColumnIndex("date"));
                @SuppressLint("Range") int feeling_id = cursor.getInt(cursor.getColumnIndex("feeling_id"));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String date = simpleDateFormat.format(new Date(dateMili));
                View view = getLayoutInflater().inflate(R.layout.activity_feeling,null, false);
                TextView messageView = (TextView) view.findViewById(R.id.message);
                messageView.setText(message);
                view.setTag(feeling_id);
                TextView dateView = (TextView) view.findViewById(R.id.date);
                dateView.setText(date);
                listOfFeelings.addView(view);
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        selectedCard = view.getTag().toString();
                        alert.show();
                        return false;
                    }
                });
                cursor.moveToNext();
            }
            cursor.close();
        }
    }

    public void createNewFeeling(View view) {
        Intent intent = new Intent(this, activity_welcome_input.class);
        startActivity(intent);
    }

    public void onFilter(View view) throws Exception {
        Database db = new Database(this);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String min_date_string = ((TextView) findViewById(R.id.min_date)).getText().toString() + " 00:00:00";
        String max_date_string = ((TextView) findViewById(R.id.max_date)).getText().toString() + " 00:00:00";
        Date min_date = null;
        Date max_date = null;
        try {
            min_date = sdf.parse(min_date_string);

        } catch (Exception e) {}
        try {
            max_date = sdf.parse(max_date_string);
        } catch (Exception e) {}

        String query = "select * from feeling where ";
        if (min_date != null) {
            long min_date_milis = min_date.getTime();
            query += "date >= " + min_date_milis;
        }
        if (max_date != null) {
            if (min_date != null) {
                query += " and ";
            }
            long max_date_milis = max_date.getTime();
            query += "date <= " + max_date_milis;
        }

        loadFeelings(query);
        closeFilter(view);
    }

    public void openFilter(View view) {
        Animation anim = new TranslateAnimation(700, 0, 0, 0);
        anim.setDuration(300);
        View card = findViewById(R.id.filter);
        card.startAnimation(anim);
        card.setVisibility(View.VISIBLE);
    }

    public void closeFilter(View view) {
        Animation anim = new TranslateAnimation(0, 700, 0, 0);
        anim.setDuration(300);
        View card = findViewById(R.id.filter);
        card.startAnimation(anim);
        card.setVisibility(View.INVISIBLE);
    }

}
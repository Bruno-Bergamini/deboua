package com.example.deboua.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.deboua.Feeling;

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE = "deboua2";

    public static final String FEELING_TABLE = "feeling";
    public static final String FEELING_ID_COLUMN = "feeling_id";
    public static final String MESSAGE_COLUMN = "message";
    public static final String DATE_COLUMN = "date";

    public Database(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createFeelingTable(sqLiteDatabase);
    }
    void createFeelingTable(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + FEELING_TABLE +  "(" +
                FEELING_ID_COLUMN + " INTEGER PRIMARY KEY, " +
                MESSAGE_COLUMN + " TEXT," +
                DATE_COLUMN + " INTEGER)";
        sqLiteDatabase.execSQL(query);
    }

    public void addFeeling (Feeling feeling) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MESSAGE_COLUMN, feeling.getMessage());
        values.put(DATE_COLUMN, feeling.getDate());
        db.insert("feeling", null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
}

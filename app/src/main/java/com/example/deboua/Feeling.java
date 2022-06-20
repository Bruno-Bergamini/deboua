package com.example.deboua;

public class Feeling {
    String message;
    long date;

    public Feeling(String message, long date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public long getDate() {
        return date;
    }
}

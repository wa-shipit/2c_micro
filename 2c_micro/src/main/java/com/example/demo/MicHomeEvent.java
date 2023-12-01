package com.example.demo;

public class MicHomeEvent {
    private String title;
    private String date;

    public MicHomeEvent(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}

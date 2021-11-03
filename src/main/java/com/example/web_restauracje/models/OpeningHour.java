package com.example.web_restauracje.models;

public class OpeningHour {
    private int open;
    private int close;
    private String when;

    public OpeningHour() {}

    public OpeningHour(int open, int close, String when) {
        this.open = open;
        this.close = close;
        this.when = when;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getClose() {
        return close;
    }

    public void setClose(int close) {
        this.close = close;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }
}
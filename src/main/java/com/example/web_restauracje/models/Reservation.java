package com.example.web_restauracje.models;

public class Reservation {
    private String date;
    private int from;
    private String person;
    private int to;
    private int table;

    public Reservation() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public Reservation(String date, int from, String person, int to, int tableId) {
        this.date = date;
        this.from = from;
        this.person = person;
        this.to = to;
        this.table = tableId;
    }
}
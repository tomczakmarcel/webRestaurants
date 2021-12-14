package com.example.web_restauracje.models;

public class Reservation {
    private String date;
    private int from;
    private String person;
    private String id;
    private int restaurantId;
    private int to;
    private int table;

    public Reservation() {}

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Reservation(String date, int from, String person, int to, int tableId, int restaurantId, String id) {
        this.date = date;
        this.from = from;
        this.person = person;
        this.to = to;
        this.table = tableId;
        this.restaurantId = restaurantId;
        this.id = id;
    }
}
package com.example.web_restauracje.models;

import java.util.ArrayList;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String description;
    private String logoURL;
    private ArrayList<Meal> mealList;
    private ArrayList<Reservation> reservationList;
    private boolean expanded;
    private ArrayList<OpeningHour> openingHour;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Meal> getMealList() { return mealList; }

    public String getLogoURL() { return logoURL; }
    public void setLogoURL(String logoURL){ this.logoURL = logoURL; };

    public void setMealList(ArrayList<Meal> mealList) {
        this.mealList = mealList;
    }

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(ArrayList<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public ArrayList<OpeningHour> getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(ArrayList<OpeningHour> openingHour) {
        this.openingHour = openingHour;
    }

    public Restaurant() {}

    public Restaurant(int restaurantId, String name, String logoURL, String description, ArrayList<Meal> mealList,
                      ArrayList<Reservation> reservationList, boolean expanded, ArrayList<OpeningHour> openingHour) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.mealList = mealList;
        this.reservationList = reservationList;
        this.expanded = expanded;
        this.openingHour = openingHour;
        this.logoURL = logoURL;
    }
}

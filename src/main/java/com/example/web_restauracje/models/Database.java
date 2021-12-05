package com.example.web_restauracje.models;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Database {

    private static Database INSTANCE;
    private static final ArrayList<Restaurant> restaurantList = new ArrayList<>();

    private Database(){}

    public static Database getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public static ArrayList<Restaurant> getRestaurantList(){
        return restaurantList;
    }
    public static ArrayList<Meal> getMealListFromRestaurant(String name){
        var restaurant = getRestaurant(name);
        var mealList = restaurant.getMealList();
        return mealList;
    }
    public static void addRestaurant(Restaurant restaurant){
        var name = restaurant.getName();
        var test = restaurant;
        try {
            test = restaurantList.stream().filter(x -> x.getName().equals(name)).findFirst().get();
        }
        catch(Exception e)
        {System.out.println(e);
        test = null;}
        if (test == null) {
            restaurantList.add(restaurant);
        }
    }

    @NotNull
    public static Restaurant getRestaurant(String name){
        return restaurantList.stream().filter(x -> x.getName().equals(name)).findFirst().get();
    }

    @NotNull
    public static Restaurant getRestaurantById(Integer id){
        var test = restaurantList;
        return restaurantList.stream().filter(x -> x.getRestaurantId() == id).findFirst().get();
    }

    public static OpeningHour getOpeningHour(String restaurantName, String date){
        int open = getRestaurant(restaurantName)
                .getOpeningHour().stream().filter(x -> x.getWhen().equals(date)).findFirst().get().getOpen();
        int close = getRestaurant(restaurantName)
                .getOpeningHour().stream().filter(x -> x.getWhen().equals(date)).findFirst().get().getClose();
        return new OpeningHour(open, close, date);
    }

    public static ArrayList<Reservation> getReservationList (String restaurantName, String date){
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Object newReservation :
                getRestaurant(restaurantName).getReservationList().stream().filter(x -> x.getDate().equals(date)).toArray()) {
            reservations.add((Reservation)newReservation);
        }

        return reservations;
    }
}

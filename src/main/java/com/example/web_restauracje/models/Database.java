package com.example.web_restauracje.models;

import com.example.web_restauracje.firebase.FirebaseInit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;

import javax.management.relation.RelationServiceMBean;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Database {

    private static Database INSTANCE;
    private static final ArrayList<Restaurant> restaurantList = new ArrayList<>();
    private static ArrayList<Reservation> reservationList = new ArrayList<>();
    private static ArrayList<User> userList = new ArrayList<>();

    private Database() {
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public static ArrayList<Restaurant> getRestaurantList()
    {
        ArrayList<Restaurant> restaurants= new ArrayList<>();
        try {
            restaurants = restaurantList;
        }
        catch (NullPointerException exception){
            getRestaurantList();
        }
        return restaurants;
    }

    public static ArrayList<Meal> getMealListFromRestaurant(String name) {
        var restaurant = getRestaurant(name);
        var mealList = restaurant.getMealList();
        return mealList;
    }

    public static void addRestaurant(Restaurant restaurant) {
        var name = restaurant.getName();
        var test = restaurant;
        try {
            test = restaurantList.stream().filter(x -> x.getName().equals(name)).findFirst().get();
        } catch (Exception e) {
            System.out.println(e);
            test = null;
        }
        if (test == null) {
            restaurantList.add(restaurant);
        }
    }

    @NotNull
    public static Restaurant getRestaurant(String name) {
        return restaurantList.stream().filter(x -> x.getName().equals(name)).findFirst().get();
    }

    @NotNull
    public static Restaurant getRestaurantById(Integer id) {
        var test = restaurantList;
        return restaurantList.stream().filter(x -> x.getRestaurantId() == id).findFirst().get();
    }

    public static OpeningHour getOpeningHour(String restaurantName, String date) {
        int open = getRestaurant(restaurantName)
                .getOpeningHour().stream().filter(x -> x.getWhen().equals(date)).findFirst().get().getOpen();
        int close = getRestaurant(restaurantName)
                .getOpeningHour().stream().filter(x -> x.getWhen().equals(date)).findFirst().get().getClose();
        return new OpeningHour(open, close, date);
    }

    public static ArrayList<Reservation> getReservationList(String restaurantName, String date) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Object newReservation :
                getRestaurant(restaurantName).getReservationList().stream().filter(x -> x.getDate().equals(date)).toArray()) {
            reservations.add((Reservation) newReservation);
        }

        return reservations;
    }


    public static void loadReservationList() throws ExecutionException, InterruptedException {
        reservationList = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("reservationList").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot doc : documents) {

            String id = doc.getId();
            int to = Integer.parseInt(Objects.requireNonNull(doc.get("to")).toString());
            int from = Integer.parseInt(Objects.requireNonNull(doc.get("from")).toString());
            int restaurantId = Integer.parseInt(Objects.requireNonNull(doc.get("restaurantId")).toString());
            int table = Integer.parseInt(Objects.requireNonNull(doc.get("table")).toString());
            String person = (String) doc.get("person");
            String date = (String) doc.get("date");
            Reservation reservation = new Reservation(date, from, person, to, table, restaurantId, id);
            reservationList.add(reservation);

        }
    }

    public static ArrayList<Reservation> getAllReservationList() {
        return reservationList;
    }


    public static ArrayList<Reservation> getReservationList(String restaurantName) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Object newReservation :
                reservationList.stream().filter(x -> x.getRestaurantId() == getRestaurant(restaurantName).getRestaurantId()).toArray()) {
            reservations.add((Reservation) newReservation);
        }

        return reservations;
    }

    public static Reservation getReservationById(String id) {
        Object o = getAllReservationList().stream().filter(x -> x.getId().equals(id)).findFirst();
        System.out.println();
        return getAllReservationList().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(new Reservation());
    }

    public static void UpdateReservation(Reservation reservation) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = (Map<String, Object>) oMapper.convertValue(reservation, Map.class);
        map.remove("id");
        Firestore db = FirestoreClient.getFirestore();
        db.collection("reservationList").document(reservation.getId()).update(map);
    }

    public static void DeleteReservation(Reservation reservation) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("reservationList").document(reservation.getId()).delete();
    }

    public static void AddReservation(Reservation reservation) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("reservationList").add(reservation);
    }


    //dziala szybciej
    public static void loadUserList() throws ExecutionException, InterruptedException {

        ArrayList<User> users = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot doc : documents) {
            User newUser = new User(doc.getString("Name"), doc.getString("Surname"), doc.getString("E-mail"),
                    Boolean.TRUE.equals(doc.getBoolean("isAdmin")), doc.getId());
            Object o = doc.getId();
            users.add(newUser);
        }
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static ArrayList<Meal> getMealList(String restaurantName) throws IOException, ExecutionException, InterruptedException {
        ArrayList<Meal> meals;
        try {
            meals = getRestaurant(restaurantName).getMealList();
            return meals;
        } catch (NullPointerException exception) {
            FirebaseInit.init();
            return new ArrayList<>();
        }
    }

    public static ArrayList<Meal> getMealListWithCategory(String restaurantName, String category) throws IOException, ExecutionException, InterruptedException {
        ArrayList mealList = new ArrayList();
        Collections.addAll(mealList, Database.getMealList(restaurantName).stream().filter(x -> x.getCategory().equals(category)).toArray());

        return mealList;
    }

    public static ArrayList<String> getCategory(String restaurantName) throws IOException, ExecutionException, InterruptedException {
        ArrayList<String> allCategory = new ArrayList<>();
        ArrayList<String> uniqueCategory = new ArrayList<>();
        for (int i = 0; i < Database.getMealList(restaurantName).size(); i++) {
            allCategory.add(Database.getMealList(restaurantName).get(i).getCategory());
        }

        allCategory.stream().distinct().forEach(uniqueCategory::add);
        return uniqueCategory;
    }

    public static ArrayList<OpeningHour> getOpeningHours(String restaurantName) throws IOException {
        var restaurant = getRestaurant(restaurantName);
        var openingHour = restaurant.getOpeningHour();
        return openingHour;
    }

    public static void editHours(String restaurantName, int newCloseTime, int newOpenTime, String when) throws IOException, ExecutionException, InterruptedException {
        OpeningHour openH = getOpeningHours(restaurantName).stream().filter(x -> x.getWhen().equals(when)).findFirst().orElse(null);
        removeOpeningHour(restaurantName, openH);
        openH.setOpen(newOpenTime);
        openH.setClose(newCloseTime);
        openH.setWhen(when);
        getOpeningHours(restaurantName).add(openH);
        updateDataOnFirebase();
    }

    public static void updateDataOnFirebase() throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        var restaurantsArray = Database.getRestaurantList();
        mDatabase.child("restaurants").setValueAsync(restaurantsArray); //ciekawe czy to zadziala v1 xD
        FirebaseInit.init(); //ciekawe czy to zadziala v2 xD
    }

    protected static void removeOpeningHour(String restaurantName, OpeningHour openingHour) throws IOException, ExecutionException, InterruptedException {
        getOpeningHours(restaurantName).remove(openingHour);
        updateDataOnFirebase();
    }
}

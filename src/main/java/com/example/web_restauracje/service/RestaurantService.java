package com.example.web_restauracje.service;

import com.example.web_restauracje.models.Product;
import com.example.web_restauracje.models.Restaurant;
import com.example.web_restauracje.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ExecutionException;

@Service
public class RestaurantService {

    private static final String COLLECTION_NAME = "restaurants";

    public Restaurant getRestaurantDetailsByName(String name) throws ExecutionException, InterruptedException {

        //Firestore dbFirestore = FirestoreClient.getFirestore();
        DatabaseReference dbFirebase = FirebaseDatabase.getInstance().getReference();

        var test = dbFirebase.child(COLLECTION_NAME).getDatabase();
        Restaurant restaurant = null;
/*        if (document.exists()) {
            user = document.toObject(User.class);
            return user;
        } else {
            return null;
        }*/

        return restaurant;
    }
/*    public Product getProductDetailsByName(String name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore= FirestoreClient.getFirestore();

        DocumentReference documentReference=dbFirestore.collection(COLLECTION_NAME).document(name);

        ApiFuture<DocumentSnapshot> future=documentReference.get();

        DocumentSnapshot document=future.get();

        Product product=null;
        if(document.exists()) {
            product = document.toObject(Product.class);
            return  product;
        }else{
            return null;
        }
    }*/
}

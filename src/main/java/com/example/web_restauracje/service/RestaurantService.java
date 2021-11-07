package com.example.web_restauracje.service;

import com.example.web_restauracje.models.Database;
import com.example.web_restauracje.models.Product;
import com.example.web_restauracje.models.Restaurant;
import com.example.web_restauracje.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.service.Firebase;
import org.apache.coyote.Adapter;
import org.springframework.stereotype.Service;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@Service
public class RestaurantService {

    private static final String COLLECTION_NAME = "restaurants";

    public void getRestaurantDetailsByName(String name) throws ExecutionException, InterruptedException, FirebaseException {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("restaurants").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Restaurant res = ds.getValue(Restaurant.class);
                    Database.getInstance().addRestaurant(res);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getRestaurantDatabaseDetailsByName() throws ExecutionException, InterruptedException, FirebaseException {

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

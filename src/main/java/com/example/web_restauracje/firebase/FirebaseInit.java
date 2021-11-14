package com.example.web_restauracje.firebase;

import com.example.web_restauracje.models.Database;
import com.example.web_restauracje.models.Restaurant;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Service
public class FirebaseInit {

    @PostConstruct
    public static void init() throws IOException {

        FileInputStream serviceAccount = null;

        try {
        serviceAccount = new FileInputStream("./firebaseKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://integracjaprojekt-default-rtdb.firebaseio.com")
                .build();
        if(FirebaseApp.getApps().isEmpty())
        { FirebaseApp.initializeApp(options);};}
        catch (Exception e){
            e.printStackTrace();
        }

        var dataSnapShot = new ValueEventListener() {
            @Override
            public void onDataChange(@org.jetbrains.annotations.NotNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Restaurant res = ds.getValue(Restaurant.class);
                    Database.getInstance().addRestaurant(res);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError);
            }
        };
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("restaurants").addListenerForSingleValueEvent(dataSnapShot);
    }
}


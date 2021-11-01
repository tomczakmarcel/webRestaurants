package com.example.web_restauracje.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
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
    public void init() throws IOException {

        FileInputStream serviceAccount = null;

        try {
        serviceAccount = new FileInputStream("./firebaseKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://integracjaprojekt-default-rtdb.firebaseio.com")
                .build();

/*		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}*/
        FirebaseApp.initializeApp(options);}
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.example.web_restauracje.service;

import com.example.web_restauracje.models.User;
import com.example.web_restauracje.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "users";

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = db.collection(COLLECTION_NAME).document(user.getName()).set(user);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public User getUserDetailsByName(String name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

    DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(name);

    ApiFuture<DocumentSnapshot> future = documentReference.get();

    DocumentSnapshot document = future.get();

    User user = null;
        if (document.exists()) {
        user = document.toObject(User.class);
        return user;
    } else {
        return null;
    }
}

    public List<User> getUserDetails() throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        Iterable<DocumentReference> documentReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<User> userList = new ArrayList<>();
        User user = null;

        while (iterator.hasNext()) {
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();

            user = document.toObject(User.class);
            userList.add(user);

        }
        return userList;
    }


    public String updateUser(User user) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getName()).set(user);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(String name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(name).delete();

        return "Document with User ID" + name + " has been deleted successfully";
    }
}

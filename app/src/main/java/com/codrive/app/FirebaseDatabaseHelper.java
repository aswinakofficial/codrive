package com.codrive.app;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    userModel userModel = new userModel();

    public interface DataStatus{
        void DataIsLoaded(userModel userModel);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FirebaseDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void readUser(String reference, DataStatus dataStatus)
    {
        databaseReference = firebaseDatabase.getReference(reference);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String fullName = snapshot.child("fullName").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String age = snapshot.child("age").getValue(String.class);
                    String phoneNo = snapshot.child("phoneNo").getValue(String.class);
                    Log.d("Fullname:", fullName);
                    userModel userModel1 = new userModel(fullName,email,age,phoneNo);
                    dataStatus.DataIsLoaded(userModel1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

package com.codrive.app;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.text.similarity.CosineSimilarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseDatabaseHelper {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    userModel userModel = new userModel();

    public interface DataStatus{
        void DataIsLoaded(userModel userModel);
        void TripDataIsLoaded(List<tripModel> trips);
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
    public List<tripModel> readTrips(String destination, DataStatus dataStatus){
        List<tripModel> trips = new ArrayList<>();
        databaseReference = firebaseDatabase.getReference("Trip");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot tripSnapshot: snapshot.getChildren()){
                    String tripID = tripSnapshot.getKey();
                    String Destination = tripSnapshot.child("destination").getValue(String.class);
                    Map<CharSequence, Integer> vector1 = new HashMap<>();
                    String[] words = Destination.toLowerCase().split("\\s+");
                    for (String word : words) {
                        if (vector1.containsKey(word)) {
                            vector1.put(word, vector1.get(word) + 1);
                        } else {
                            vector1.put(word, 1);
                        }
                    }
                    Map<CharSequence, Integer> vector2 = new HashMap<>();
                    String[] words2 = destination.toLowerCase().split("\\s+");
                    for (String word : words2) {
                        if (vector2.containsKey(word)) {
                            vector2.put(word, vector2.get(word) + 1);
                        } else {
                            vector2.put(word, 1);
                        }
                    }

                    CosineSimilarity cosineSimilarity = new CosineSimilarity();
                    double similarity = cosineSimilarity.cosineSimilarity(vector1, vector2);

                    if (similarity >= 0.33 ) {
                        String startLoc = tripSnapshot.child("startLocation").getValue(String.class);
                        String Date = tripSnapshot.child("date").getValue(String.class);
                        String time = tripSnapshot.child("time").getValue(String.class);
                        String vacancy = tripSnapshot.child("vacancy").getValue(String.class);
                        String driverName = tripSnapshot.child("driverName").getValue(String.class);
                        String driverNo = tripSnapshot.child("driverPhoneNo").getValue(String.class);
                        String vehicleModel = tripSnapshot.child("vehicleModel").getValue(String.class);
                        String vehicleNo  = tripSnapshot.child("vehicleRegistrationNo").getValue(String.class);
                        String vehicleMilega = tripSnapshot.child("vehicleMilage").getValue(String.class);
                        String pick1 = tripSnapshot.child("pickupPoint1").getValue(String.class);
                        String pick2 = tripSnapshot.child("pickupPoint2").getValue(String.class);
                        String pick3 = tripSnapshot.child("pickupPoint3").getValue(String.class);
                        String pick4 = tripSnapshot.child("pickupPoint4").getValue(String.class);
                        String pick5 = tripSnapshot.child("pickupPoint5").getValue(String.class);

                        tripModel trip = new tripModel(tripID, startLoc, Destination, Date, time, vacancy, driverName, driverNo, vehicleModel, vehicleNo, vehicleMilega, pick1, pick2, pick3, pick4, pick5);
                        trips.add(trip);
                    } else {

                    }
                }
                dataStatus.TripDataIsLoaded(trips);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return null;
    }
}

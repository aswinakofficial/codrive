package com.codrive.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class joinedTripDetails extends AppCompatActivity {
    FirebaseUser user;
    String tripId;

    public interface DataLoader{
        public void UserTripId(String tripID);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joined_trip_details);
        user = FirebaseAuth.getInstance().getCurrentUser();
        DataLoader dataLoader = null;


        //fetching userDetails
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("UserDetails/"+user.getUid());
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String tripId = snapshot.child("TripJoined").getValue(String.class);
                    dataLoader.UserTripId(tripId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        new DataLoader(){
            @Override
            public void UserTripId(String tripID) {
                tripId = tripID;
            }
        };
        Toast.makeText(this, tripId, Toast.LENGTH_SHORT).show();
    }
}
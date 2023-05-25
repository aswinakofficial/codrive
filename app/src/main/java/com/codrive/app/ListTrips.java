package com.codrive.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.airbnb.lottie.L;

import java.util.ArrayList;
import java.util.List;

public class ListTrips extends AppCompatActivity {
    RecyclerView listTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trips);
        Intent intent = getIntent();
        String searchAddress = intent.getStringExtra(HomeActivity.SEARCH_LOCATION);
        final List<tripModel>[] TripsToDisplay = new List[]{new ArrayList<>()};

        FirebaseDatabaseHelper firebaseDatabaseHelper = new FirebaseDatabaseHelper();
        listTrips = findViewById(R.id.tripList);
        listTrips.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewForTrips adapter = new RecyclerViewForTrips();
        listTrips.setAdapter(adapter);

        new FirebaseDatabaseHelper().readTrips(searchAddress, new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(userModel userModel) {

            }

            @Override
            public void TripDataIsLoaded(List<tripModel> trips) {
                adapter.setTrips(trips);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }
}
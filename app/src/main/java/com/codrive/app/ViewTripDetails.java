package com.codrive.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ViewTripDetails extends AppCompatActivity {
    TextInputEditText date;
    AutoCompleteTextView createTripStartLoc;
    AutoCompleteTextView createTripDestination;

    TextInputEditText createTripTime;
    TextInputEditText createTripVacancy;
    TextInputEditText createTripDriverName;
    TextInputEditText createTripDriverNumber;
    TextInputEditText createTripVehicleModel;
    TextInputEditText createTripVehicleNumber;
    TextInputEditText createTripVehicleMilage;
    AutoCompleteTextView pickupPoint1;
    AutoCompleteTextView pickupPoint2;
    AutoCompleteTextView pickupPoint3;
    AutoCompleteTextView pickupPoint4;
    AutoCompleteTextView pickupPoint5;
    Button joinTrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip_details);
        Intent intent = getIntent();

        tripModel trip = intent.getParcelableExtra(RecyclerViewForTrips.TRIP_DETAILS);
        Toast.makeText(this, trip.toString(), Toast.LENGTH_SHORT).show();

    }
}
package com.codrive.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.List;

public class ViewTripDetails extends AppCompatActivity {
    TextView joinStartLocation;
    TextView joinDestination;
    TextView joinDate;
    TextView joinTime;
    TextView joinVacancy;
    TextView joinDriverName;
    TextView joinDriverNumber;
    TextView joinVehicleModel;
    TextView joinVehicleNumber;
    TextView joinVehicleMileage;
    TextInputEditText passengerName;
    TextInputEditText passengerAge;
    TextInputEditText passengerNumber;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip_details);
        Intent intent = getIntent();
        user = FirebaseAuth.getInstance().getCurrentUser();
        new FirebaseDatabaseHelper().readUser(user.getUid(), new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(userModel userModel) {
                if(!TextUtils.isEmpty(userModel.getFullName())){

                }
                if(!TextUtils.isEmpty(userModel.getAge())){

                }
                if(!TextUtils.isEmpty(userModel.getPhoneNo())){

                }
            }

            @Override
            public void TripDataIsLoaded(List<tripModel> trips) {

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

        tripModel trip = intent.getParcelableExtra(RecyclerViewForTrips.TRIP_DETAILS);
        Toast.makeText(this, trip.toString(), Toast.LENGTH_SHORT).show();


    }
}
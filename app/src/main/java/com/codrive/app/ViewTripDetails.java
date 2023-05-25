package com.codrive.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
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
    Spinner pickUpPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip_details);
        Intent intent = getIntent();
        tripModel trip = intent.getParcelableExtra(RecyclerViewForTrips.TRIP_DETAILS);

        joinStartLocation = findViewById(R.id.joinStartLocation);
        joinDestination = findViewById(R.id.joindestination);
        joinDate = findViewById(R.id.Date);
        joinTime = findViewById(R.id.Time);
        joinVacancy = findViewById(R.id.joinvacancy);
        joinDriverName = findViewById(R.id.joindriverName);
        joinDriverNumber = findViewById(R.id.joinDriverNumber);
        joinVehicleModel = findViewById(R.id.joinVenicleModel);
        joinVehicleNumber = findViewById(R.id.joinVehicleNumber);
        joinVehicleMileage = findViewById(R.id.joinVehicleMileage);
        passengerName = findViewById(R.id.Name1);
        passengerAge = findViewById(R.id.age1);
        passengerNumber = findViewById(R.id.phoneNumber1);
        pickUpPoints = findViewById(R.id.pickupLocations);

        //setting values
        joinStartLocation.setText(" Start Location: "+trip.getStartLocation());
        joinDestination.setText(" Destination: "+trip.getDestination());
        joinDate.setText(" Date: "+trip.getDate());
        joinTime.setText(" Time: "+trip.getTime());
        joinVacancy.setText(" Vacancy: "+trip.getVacancy());
        joinDriverName.setText(" Driver Name: "+trip.getDriverName());
        joinDriverNumber.setText(" Driver Phone: "+trip.getDriverPhoneNo());
        joinVehicleModel.setText(" Vehicle Model: "+trip.getVehicleModel());
        joinVehicleNumber.setText(" Vehicle Number: "+trip.getVehicleRegistrationNo());
        joinVehicleMileage.setText(" Vehicle Milage: "+trip.getVehicleMilage()+"Km/h");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        if(!TextUtils.isEmpty(trip.getPickupPoint1())){
            adapter.add(trip.getPickupPoint1());
        }
        if(!TextUtils.isEmpty(trip.getPickupPoint2())){
            adapter.add(trip.getPickupPoint2());
        }
        if(!TextUtils.isEmpty(trip.getPickupPoint3())){
            adapter.add(trip.getPickupPoint3());
        }
        if(!TextUtils.isEmpty(trip.getPickupPoint4())){
            adapter.add(trip.getPickupPoint4());
        }
        if(!TextUtils.isEmpty(trip.getPickupPoint5())){
            adapter.add(trip.getPickupPoint5());
        }
        pickUpPoints.setAdapter(adapter);

        user = FirebaseAuth.getInstance().getCurrentUser();
        new FirebaseDatabaseHelper().readUser("UserDetails/"+user.getUid(), new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(userModel userModel) {
                if(!TextUtils.isEmpty(userModel.getFullName())){
                    passengerName.setText(userModel.getFullName());
                }
                if(!TextUtils.isEmpty(userModel.getAge())){
                    passengerAge.setText(userModel.getAge());
                }
                if(!TextUtils.isEmpty(userModel.getPhoneNo())){
                    passengerNumber.setText(userModel.getPhoneNo());
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



    }
}
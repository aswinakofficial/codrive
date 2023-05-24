package com.codrive.app;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.codrive.app.adapter.PlaceAutoSuggestAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;


public class CreateTripFragment extends Fragment {
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
    Button createTripButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseUser user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_trip, container, false);

        createTripStartLoc = view.findViewById(R.id.createTripStartLoc);
        createTripDestination = view.findViewById(R.id.createTripDestination);
        date = view.findViewById(R.id.createTripDate);
        createTripTime = view.findViewById(R.id.createTripTime);
        createTripVacancy = view.findViewById(R.id.createTripVacancy);
        createTripDriverName = view.findViewById(R.id.createTripDriverName);
        createTripDriverNumber = view.findViewById(R.id.createTripDriverNumber);
        createTripVehicleModel = view.findViewById(R.id.createTripVehicleModel);
        createTripVehicleNumber= view.findViewById(R.id.createTripVehicleNumber);
        createTripVehicleMilage = view.findViewById(R.id.createTripVehicleMileage);
        pickupPoint1 = view.findViewById(R.id.pickUppoint1);
        pickupPoint2 = view.findViewById(R.id.pickUppoint2);
        pickupPoint3 = view.findViewById(R.id.pickUppoint3);
        pickupPoint4 = view.findViewById(R.id.pickUppoint4);
        pickupPoint5 = view.findViewById(R.id.pickUppoint5);
        createTripButton = view.findViewById(R.id.createTripButton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = firebaseDatabase.getReference("Trip/"+user.getUid());
        //Updating trip data base

        createTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startLoc = createTripStartLoc.getText().toString();
                String Destination = createTripDestination.getText().toString();
                String Date = date.getText().toString();
                String time = createTripTime.getText().toString();
                String vacancy = createTripVacancy.getText().toString();
                String driverName = createTripDriverName.getText().toString();
                String driverNumber = createTripDriverNumber.getText().toString();
                String vehicleModel = createTripVehicleModel.getText().toString();
                String vehicleNumber = createTripVehicleNumber.getText().toString();
                String vehicleMileage = createTripVehicleMilage.getText().toString();
                String pick1 = pickupPoint1.getText().toString();
                String pick2 = pickupPoint2.getText().toString();
                String pick3 = pickupPoint3.getText().toString();
                String pick4 = pickupPoint4.getText().toString();
                String pick5 = pickupPoint5.getText().toString();
                if (TextUtils.isEmpty(startLoc) || TextUtils.isEmpty(Destination) || TextUtils.isEmpty(Date) || TextUtils.isEmpty(time) || TextUtils.isEmpty(vacancy) || TextUtils.isEmpty(driverName) || TextUtils.isEmpty(driverNumber) || TextUtils.isEmpty(vehicleModel) || TextUtils.isEmpty(vehicleNumber) || TextUtils.isEmpty(vehicleMileage)) {

                    Toast.makeText(view.getContext(), "Enter All Details!!", Toast.LENGTH_SHORT).show();
                } else {
                    if(TextUtils.isEmpty(pick1) || TextUtils.isEmpty(pick2) || TextUtils.isEmpty(pick3)){
                        Toast.makeText(view.getContext(), "Enter Atleast 3 Pickup Points", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        tripModel trip = new tripModel(user.getUid(), startLoc, Destination, Date, time, vacancy, driverName, driverNumber, vehicleModel, vehicleNumber, vehicleMileage, pick1, pick2, pick3, pick4, pick5);
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    Toast.makeText(view.getContext(), "A Trip Already Exists", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(view.getContext(), "Trip Created Successfully", Toast.LENGTH_SHORT).show();
                                    reference.setValue(trip);
                                    createTripStartLoc.getText().clear();
                                    createTripDestination.getText().clear();
                                    date.getText().clear();
                                    createTripTime.getText().clear();
                                    createTripVacancy.getText().clear();
                                    createTripDriverName.getText().clear();
                                    createTripDriverNumber.getText().clear();
                                    createTripVehicleModel.getText().clear();
                                    createTripVehicleNumber.getText().clear();
                                    createTripVehicleMilage.getText().clear();
                                    pickupPoint1.getText().clear();
                                    pickupPoint2.getText().clear();
                                    pickupPoint3.getText().clear();
                                    pickupPoint4.getText().clear();
                                    pickupPoint5.getText().clear();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }

                }
            }
        });



        //auto complete start location
        final AutoCompleteTextView autoCompleteTextView=view.findViewById(R.id.createTripStartLoc);
        autoCompleteTextView.setAdapter(new PlaceAutoSuggestAdapter(requireActivity(),android.R.layout.simple_list_item_1));

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ",autoCompleteTextView.getText().toString());
                LatLng latLng=getLatLngFromAddress(autoCompleteTextView.getText().toString());
                if(latLng!=null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address=getAddressFromLatLng(latLng);
                    if(address!=null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ",""+address.getAddressLine(0));
                        Log.d("Phone : ",""+address.getPhone());
                        Log.d("Pin Code : ",""+address.getPostalCode());
                        Log.d("Feature : ",""+address.getFeatureName());
                        Log.d("More : ",""+address.getLocality());
                    }
                    else {
                        Log.d("Adddress","Address Not Found");
                    }
                }
                else {
                    Log.d("Lat Lng","Lat Lng Not Found");
                }

            }
        });
        //auto complete start location
        final AutoCompleteTextView autoCompleteTextViewEndDest=view.findViewById(R.id.createTripDestination);
        autoCompleteTextViewEndDest.setAdapter(new PlaceAutoSuggestAdapter(requireActivity(),android.R.layout.simple_list_item_1));

        autoCompleteTextViewEndDest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ",autoCompleteTextView.getText().toString());
                LatLng latLng=getLatLngFromAddress(autoCompleteTextView.getText().toString());
                if(latLng!=null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address=getAddressFromLatLng(latLng);
                    if(address!=null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ",""+address.getAddressLine(0));
                        Log.d("Phone : ",""+address.getPhone());
                        Log.d("Pin Code : ",""+address.getPostalCode());
                        Log.d("Feature : ",""+address.getFeatureName());
                        Log.d("More : ",""+address.getLocality());
                    }
                    else {
                        Log.d("Adddress","Address Not Found");
                    }
                }
                else {
                    Log.d("Lat Lng","Lat Lng Not Found");
                }

            }
        });
        //auto complete start location
        final AutoCompleteTextView autoCompleteTextViewPickup1=view.findViewById(R.id.pickUppoint1);
        autoCompleteTextViewPickup1.setAdapter(new PlaceAutoSuggestAdapter(requireActivity(),android.R.layout.simple_list_item_1));

        autoCompleteTextViewPickup1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ",autoCompleteTextView.getText().toString());
                LatLng latLng=getLatLngFromAddress(autoCompleteTextView.getText().toString());
                if(latLng!=null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address=getAddressFromLatLng(latLng);
                    if(address!=null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ",""+address.getAddressLine(0));
                        Log.d("Phone : ",""+address.getPhone());
                        Log.d("Pin Code : ",""+address.getPostalCode());
                        Log.d("Feature : ",""+address.getFeatureName());
                        Log.d("More : ",""+address.getLocality());
                    }
                    else {
                        Log.d("Adddress","Address Not Found");
                    }
                }
                else {
                    Log.d("Lat Lng","Lat Lng Not Found");
                }

            }
        });
        //auto complete start location
        final AutoCompleteTextView autoCompleteTextViewPickup2=view.findViewById(R.id.pickUppoint2);
        autoCompleteTextViewPickup2.setAdapter(new PlaceAutoSuggestAdapter(requireActivity(),android.R.layout.simple_list_item_1));

        autoCompleteTextViewPickup2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ",autoCompleteTextView.getText().toString());
                LatLng latLng=getLatLngFromAddress(autoCompleteTextView.getText().toString());
                if(latLng!=null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address=getAddressFromLatLng(latLng);
                    if(address!=null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ",""+address.getAddressLine(0));
                        Log.d("Phone : ",""+address.getPhone());
                        Log.d("Pin Code : ",""+address.getPostalCode());
                        Log.d("Feature : ",""+address.getFeatureName());
                        Log.d("More : ",""+address.getLocality());
                    }
                    else {
                        Log.d("Adddress","Address Not Found");
                    }
                }
                else {
                    Log.d("Lat Lng","Lat Lng Not Found");
                }

            }
        });
        //auto complete start location
        final AutoCompleteTextView autoCompleteTextViewPickup3=view.findViewById(R.id.pickUppoint3);
        autoCompleteTextViewPickup3.setAdapter(new PlaceAutoSuggestAdapter(requireActivity(),android.R.layout.simple_list_item_1));

        autoCompleteTextViewPickup3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ",autoCompleteTextView.getText().toString());
                LatLng latLng=getLatLngFromAddress(autoCompleteTextView.getText().toString());
                if(latLng!=null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address=getAddressFromLatLng(latLng);
                    if(address!=null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ",""+address.getAddressLine(0));
                        Log.d("Phone : ",""+address.getPhone());
                        Log.d("Pin Code : ",""+address.getPostalCode());
                        Log.d("Feature : ",""+address.getFeatureName());
                        Log.d("More : ",""+address.getLocality());
                    }
                    else {
                        Log.d("Adddress","Address Not Found");
                    }
                }
                else {
                    Log.d("Lat Lng","Lat Lng Not Found");
                }

            }
        });
        //auto complete start location
        final AutoCompleteTextView autoCompleteTextViewPickup4=view.findViewById(R.id.pickUppoint4);
        autoCompleteTextViewPickup4.setAdapter(new PlaceAutoSuggestAdapter(requireActivity(),android.R.layout.simple_list_item_1));

        autoCompleteTextViewPickup4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ",autoCompleteTextView.getText().toString());
                LatLng latLng=getLatLngFromAddress(autoCompleteTextView.getText().toString());
                if(latLng!=null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address=getAddressFromLatLng(latLng);
                    if(address!=null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ",""+address.getAddressLine(0));
                        Log.d("Phone : ",""+address.getPhone());
                        Log.d("Pin Code : ",""+address.getPostalCode());
                        Log.d("Feature : ",""+address.getFeatureName());
                        Log.d("More : ",""+address.getLocality());
                    }
                    else {
                        Log.d("Adddress","Address Not Found");
                    }
                }
                else {
                    Log.d("Lat Lng","Lat Lng Not Found");
                }

            }
        });
        //auto complete start location
        final AutoCompleteTextView autoCompleteTextViewPickup5=view.findViewById(R.id.pickUppoint5);
        autoCompleteTextViewPickup5.setAdapter(new PlaceAutoSuggestAdapter(requireActivity(),android.R.layout.simple_list_item_1));

        autoCompleteTextViewPickup5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ",autoCompleteTextView.getText().toString());
                LatLng latLng=getLatLngFromAddress(autoCompleteTextView.getText().toString());
                if(latLng!=null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address=getAddressFromLatLng(latLng);
                    if(address!=null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ",""+address.getAddressLine(0));
                        Log.d("Phone : ",""+address.getPhone());
                        Log.d("Pin Code : ",""+address.getPostalCode());
                        Log.d("Feature : ",""+address.getFeatureName());
                        Log.d("More : ",""+address.getLocality());
                    }
                    else {
                        Log.d("Adddress","Address Not Found");
                    }
                }
                else {
                    Log.d("Lat Lng","Lat Lng Not Found");
                }

            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        //autocomplete



        // Inflate the layout for this fragment
        return view;

    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Handle the selected date
                String selectedDate = (month + 1) + "/" + dayOfMonth + "/" + year;
                date.setText(selectedDate);
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
    private LatLng getLatLngFromAddress(String address){

        Geocoder geocoder=new Geocoder(requireActivity());
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if(addressList!=null){
                Address singleaddress=addressList.get(0);
                LatLng latLng=new LatLng(singleaddress.getLatitude(),singleaddress.getLongitude());
                return latLng;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private Address getAddressFromLatLng(LatLng latLng){
        Geocoder geocoder=new Geocoder(requireActivity());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
            if(addresses!=null){
                Address address=addresses.get(0);
                return address;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
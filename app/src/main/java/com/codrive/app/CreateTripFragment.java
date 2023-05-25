package com.codrive.app;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.codrive.app.adapter.PlaceAutoSuggestAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

public class CreateTripFragment extends Fragment {
    TextInputEditText date;
    TextInputEditText createTripStartLoc;
    TextInputEditText createTripDestination;
    TextInputEditText createTripDate;
    TextInputEditText createTripTime;
    TextInputEditText createTripVacancy;
    TextInputEditText createTripDriverName;
    TextInputEditText createTripDriverNumber;
    TextInputEditText createTripVehicleModel;
    TextInputEditText createTripVehicleNumber;
    TextInputEditText createTripVehicleMilage;
    TextInputEditText createTripButton;
    Handler handler;
    Runnable runnable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_trip, container, false);

        // Auto complete start location
        final AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.createTripStartLoc);
        autoCompleteTextView.setAdapter(new PlaceAutoSuggestAdapter(requireActivity(), android.R.layout.simple_list_item_1));

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle the selected address
                String selectedAddress = autoCompleteTextView.getText().toString();
                LatLng latLng = getLatLngFromAddress(selectedAddress);
                if (latLng != null) {
                    Log.d("Lat Lng: ", latLng.latitude + ", " + latLng.longitude);
                    Address address = getAddressFromLatLng(latLng);
                    if (address != null) {
                        Log.d("Address: ", address.toString());
                        Log.d("Address Line: ", address.getAddressLine(0));
                        Log.d("Phone: ", address.getPhone());
                        Log.d("Pin Code: ", address.getPostalCode());
                        Log.d("Feature: ", address.getFeatureName());
                        Log.d("More: ", address.getLocality());
                    } else {
                        Log.d("Address", "Address Not Found");
                    }
                } else {
                    Log.d("Lat Lng", "Lat Lng Not Found");
                }
            }
        });

        // Auto complete end destination
        final AutoCompleteTextView autoCompleteTextViewEndDest = view.findViewById(R.id.createTripDestination);
        autoCompleteTextViewEndDest.setAdapter(new PlaceAutoSuggestAdapter(requireActivity(), android.R.layout.simple_list_item_1));

        autoCompleteTextViewEndDest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle the selected address
                String selectedAddress = autoCompleteTextViewEndDest.getText().toString();
                LatLng latLng = getLatLngFromAddress(selectedAddress);
                if (latLng != null) {
                    Log.d("Lat Lng: ", latLng.latitude + ", " + latLng.longitude);
                    Address address = getAddressFromLatLng(latLng);
                    if (address != null) {
                        Log.d("Address: ", address.toString());
                        Log.d("Address Line: ", address.getAddressLine(0));
                        Log.d("Phone: ", address.getPhone());
                        Log.d("Pin Code: ", address.getPostalCode());
                        Log.d("Feature: ", address.getFeatureName());
                        Log.d("More: ", address.getLocality());
                    } else {
                        Log.d("Address", "Address Not Found");
                    }
                } else {
                    Log.d("Lat Lng", "Lat Lng Not Found");
                }
            }
        });

        createTripTime = view.findViewById(R.id.createTripTime);
        createTripTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        date = view.findViewById(R.id.createTripDate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        return view;
    }

    private void showTimePickerDialog() {
        // Get the current time
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a new TimePickerDialog instance
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Handle the selected time
                        String selectedTime = hourOfDay + ":" + minute;
                        // Do something with the selected time, e.g., update a TextInputEditText
                        createTripTime.setText(selectedTime);
                    }
                },
                hour,
                minute,
                false // Set to true for 24-hour format, false for 12-hour format
        );

        // Show the time picker dialog
        timePickerDialog.show();
    }

    private void showDatePickerDialog() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new DatePickerDialog instance
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Handle the selected date
                        String selectedDate = (month + 1) + "/" + dayOfMonth + "/" + year;
                        // Do something with the selected date, e.g., update a TextInputEditText
                        date.setText(selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );

        // Show the date picker dialog
        datePickerDialog.show();
    }

    private LatLng getLatLngFromAddress(String address) {
        Geocoder geocoder = new Geocoder(requireActivity());
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if (addressList != null && !addressList.isEmpty()) {
                Address singleAddress = addressList.get(0);
                LatLng latLng = new LatLng(singleAddress.getLatitude(), singleAddress.getLongitude());
                return latLng;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Address getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(requireActivity());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                return address;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

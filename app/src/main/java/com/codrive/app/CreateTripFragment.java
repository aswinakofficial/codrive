package com.codrive.app;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_trip, container, false);

        date = view.findViewById(R.id.createTripDate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


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
}
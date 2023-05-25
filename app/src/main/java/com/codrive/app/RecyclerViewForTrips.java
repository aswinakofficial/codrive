package com.codrive.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewForTrips extends RecyclerView.Adapter<RecyclerViewForTrips.TripView>{
    private Context context;
    List<tripModel> trips = new ArrayList<>();

    public List<tripModel> getTrips() {
        return trips;
    }

    public void setTrips(List<tripModel> trips) {
        this.trips = trips;
    }


    @NonNull
    @Override
    public TripView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_layout, parent, false);
        TripView Holder =new TripView(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TripView holder, int position) {
        String tripID = trips.get(position).getTripID().toString();
        String startLoc = trips.get(position).getStartLocation().toString();
        String Destination = trips.get(position).getDestination();
        String Date = trips.get(position).getDate();
        String Time = trips.get(position).getTime();
        String vacancy = trips.get(position).getVacancy();
        String driverName= trips.get(position).getDriverName();
        String driverPhoneNo = trips.get(position).getDriverPhoneNo();
        String vehiceModel = trips.get(position).getVehicleModel();
        String vehicleNo = trips.get(position).getVehicleRegistrationNo();
        String vehicleMileage = trips.get(position).getVehicleMilage();
        String pick1 = trips.get(position).getPickupPoint1();
        String pick2 = trips.get(position).getPickupPoint2();
        String pick3 = trips.get(position).getPickupPoint3();
        String pick4 = trips.get(position).getPickupPoint4();
        String pick5 = trips.get(position).getPickupPoint5();

        tripModel trip = new tripModel(tripID, startLoc, Destination, Date, Time, vacancy, driverName, driverPhoneNo,
                vehiceModel, vehicleNo, vehicleMileage, pick1, pick2, pick3, pick4, pick5);

        holder.startLocation.setText(startLoc);
        holder.destination.setText(Destination);
        holder.date.setText(Date);
        holder.time.setText(Time);

        holder.viewTripDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "View clicked", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(view.getContext(), );
            }
        });
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    class TripView extends RecyclerView.ViewHolder {
        TextView startLocation, destination, date, time;
        Button viewTripDetails;

        public TripView(@NonNull View itemView) {
            super(itemView);
            startLocation = itemView.findViewById(R.id.startLocation);
            destination = itemView.findViewById(R.id.destination);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            viewTripDetails = itemView.findViewById(R.id.viewTripDetails);
        }
    }
}

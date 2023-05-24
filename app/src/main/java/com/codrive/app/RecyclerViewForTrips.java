package com.codrive.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.startLocation.setText(trips.get(position).getStartLocation());
        holder.destination.setText(trips.get(position).getDestination());
        holder.date.setText(trips.get(position).getDate());
        holder.time.setText(trips.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    class TripView extends RecyclerView.ViewHolder {
        TextView startLocation, destination, date, time;

        public TripView(@NonNull View itemView) {
            super(itemView);
            startLocation = itemView.findViewById(R.id.startLocation);
            destination = itemView.findViewById(R.id.destination);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}

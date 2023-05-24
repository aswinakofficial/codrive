package com.codrive.app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class tripModel implements Parcelable {
    String tripID;
    String startLocation;
    String destination;
    String date;
    String time;
    String vacancy;
    String driverName;
    String driverPhoneNo;
    String vehicleModel;
    String vehicleRegistrationNo;
    String vehicleMilage;
    String pickupPoint1;
    String pickupPoint2;
    String pickupPoint3;
    String pickupPoint4;
    String pickupPoint5;

    public tripModel() {
    }

    public tripModel(String tripID, String startLocation, String destination, String date, String time, String vacancy, String driverName, String driverPhoneNo, String vehicleModel, String vehicleRegistrationNo, String vehicleMilage, String pickupPoint1, String pickupPoint2, String pickupPoint3, String pickupPoint4, String pickupPoint5) {
        this.tripID = tripID;
        this.startLocation = startLocation;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.vacancy = vacancy;
        this.driverName = driverName;
        this.driverPhoneNo = driverPhoneNo;
        this.vehicleModel = vehicleModel;
        this.vehicleRegistrationNo = vehicleRegistrationNo;
        this.vehicleMilage = vehicleMilage;
        this.pickupPoint1 = pickupPoint1;
        this.pickupPoint2 = pickupPoint2;
        this.pickupPoint3 = pickupPoint3;
        this.pickupPoint4 = pickupPoint4;
        this.pickupPoint5 = pickupPoint5;
    }

    protected tripModel(Parcel in) {
        tripID = in.readString();
        startLocation = in.readString();
        destination = in.readString();
        date = in.readString();
        time = in.readString();
        vacancy = in.readString();
        driverName = in.readString();
        driverPhoneNo = in.readString();
        vehicleModel = in.readString();
        vehicleRegistrationNo = in.readString();
        vehicleMilage = in.readString();
        pickupPoint1 = in.readString();
        pickupPoint2 = in.readString();
        pickupPoint3 = in.readString();
        pickupPoint4 = in.readString();
        pickupPoint5 = in.readString();
    }

    public static final Creator<tripModel> CREATOR = new Creator<tripModel>() {
        @Override
        public tripModel createFromParcel(Parcel in) {
            return new tripModel(in);
        }

        @Override
        public tripModel[] newArray(int size) {
            return new tripModel[size];
        }
    };

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhoneNo() {
        return driverPhoneNo;
    }

    public void setDriverPhoneNo(String driverPhoneNo) {
        this.driverPhoneNo = driverPhoneNo;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleRegistrationNo() {
        return vehicleRegistrationNo;
    }

    public void setVehicleRegistrationNo(String vehicleRegistrationNo) {
        this.vehicleRegistrationNo = vehicleRegistrationNo;
    }

    public String getVehicleMilage() {
        return vehicleMilage;
    }

    public void setVehicleMilage(String vehicleMilage) {
        this.vehicleMilage = vehicleMilage;
    }

    public String getPickupPoint1() {
        return pickupPoint1;
    }

    public void setPickupPoint1(String pickupPoint1) {
        this.pickupPoint1 = pickupPoint1;
    }

    public String getPickupPoint2() {
        return pickupPoint2;
    }

    public void setPickupPoint2(String pickupPoint2) {
        this.pickupPoint2 = pickupPoint2;
    }

    public String getPickupPoint3() {
        return pickupPoint3;
    }

    public void setPickupPoint3(String pickupPoint3) {
        this.pickupPoint3 = pickupPoint3;
    }

    public String getPickupPoint4() {
        return pickupPoint4;
    }

    public void setPickupPoint4(String pickupPoint4) {
        this.pickupPoint4 = pickupPoint4;
    }

    public String getPickupPoint5() {
        return pickupPoint5;
    }

    public void setPickupPoint5(String pickupPoint5) {
        this.pickupPoint5 = pickupPoint5;
    }

    @Override
    public String toString() {
        return "tripModel{" +
                "tripID='" + tripID + '\'' +
                ", startLocation='" + startLocation + '\'' +
                ", destination='" + destination + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", vacancy='" + vacancy + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverPhoneNo='" + driverPhoneNo + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleRegistrationNo='" + vehicleRegistrationNo + '\'' +
                ", vehicleMilage='" + vehicleMilage + '\'' +
                ", pickupPoint1='" + pickupPoint1 + '\'' +
                ", pickupPoint2='" + pickupPoint2 + '\'' +
                ", pickupPoint3='" + pickupPoint3 + '\'' +
                ", pickupPoint4='" + pickupPoint4 + '\'' +
                ", pickupPoint5='" + pickupPoint5 + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(tripID);
        parcel.writeString(startLocation);
        parcel.writeString(destination);
        parcel.writeString(date);
        parcel.writeString(time);
        parcel.writeString(vacancy);
        parcel.writeString(driverName);
        parcel.writeString(driverPhoneNo);
        parcel.writeString(vehicleModel);
        parcel.writeString(vehicleRegistrationNo);
        parcel.writeString(vehicleMilage);
        parcel.writeString(pickupPoint1);
        parcel.writeString(pickupPoint2);
        parcel.writeString(pickupPoint3);
        parcel.writeString(pickupPoint4);
        parcel.writeString(pickupPoint5);
    }
}

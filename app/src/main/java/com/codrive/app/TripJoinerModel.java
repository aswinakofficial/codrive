package com.codrive.app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TripJoinerModel implements Parcelable {
    String joinerID;
    String name;
    String age;
    String phoneNo;
    String pickUpPoint;

    public TripJoinerModel(String joinerID, String name, String age, String phoneNo, String pickUpPoint) {
        this.joinerID = joinerID;
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.pickUpPoint = pickUpPoint;
    }

    public TripJoinerModel() {
    }

    protected TripJoinerModel(Parcel in) {
        joinerID = in.readString();
        name = in.readString();
        age = in.readString();
        phoneNo = in.readString();
        pickUpPoint = in.readString();
    }

    public static final Creator<TripJoinerModel> CREATOR = new Creator<TripJoinerModel>() {
        @Override
        public TripJoinerModel createFromParcel(Parcel in) {
            return new TripJoinerModel(in);
        }

        @Override
        public TripJoinerModel[] newArray(int size) {
            return new TripJoinerModel[size];
        }
    };

    public String getJoinerID() {
        return joinerID;
    }

    public void setJoinerID(String joinerID) {
        this.joinerID = joinerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint(String pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }

    @Override
    public String toString() {
        return "TripJoinerModel{" +
                "joinerID='" + joinerID + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", pickUpPoint='" + pickUpPoint + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(joinerID);
        parcel.writeString(name);
        parcel.writeString(age);
        parcel.writeString(phoneNo);
        parcel.writeString(pickUpPoint);
    }
}

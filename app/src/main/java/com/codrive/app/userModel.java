package com.codrive.app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class userModel implements Parcelable {
    String fullName;
    String email;
    String age;
    String phoneNo;

    public userModel() {
    }

    public userModel( String fullName, String email, String age, String phoneNo) {

        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.phoneNo = phoneNo;
    }

    protected userModel(Parcel in) {
        fullName = in.readString();
        email = in.readString();
        age = in.readString();
        phoneNo = in.readString();
    }

    public static final Creator<userModel> CREATOR = new Creator<userModel>() {
        @Override
        public userModel createFromParcel(Parcel in) {
            return new userModel(in);
        }

        @Override
        public userModel[] newArray(int size) {
            return new userModel[size];
        }
    };

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(fullName);
        parcel.writeString(email);
        parcel.writeString(age);
        parcel.writeString(phoneNo);
    }
}

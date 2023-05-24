package com.codrive.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.net.vcn.VcnUnderlyingNetworkTemplate;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class UserProfileFragment extends Fragment {

    FloatingActionButton logoutButton;
    TextInputEditText profileEmail;
    TextInputEditText profileName;
    TextInputEditText profileAge;
    TextInputEditText profilePhone;
    FloatingActionButton profileEditButton;
    FloatingActionButton profileConfirmButton;
    Boolean isEditable = false;
    public interface UserDetailsCallback {
        void onUserDetailsLoaded(userModel userDetails);
        void onUserDetailsNotFound();
        void onUserDetailsError(String errorMessage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        logoutButton = view.findViewById(R.id.logoutButton);
        profileEmail = view.findViewById(R.id.profileEmail);
        profileName = view.findViewById(R.id.profileName);
        profileAge = view.findViewById(R.id.profileAge);
        profilePhone = view.findViewById(R.id.profilePhone);
        profileEditButton = view.findViewById(R.id.profileEditButton);
        profileConfirmButton = view.findViewById(R.id.profileConfirmButton);

        //Disabling editability

        profileEmail.setEnabled(false);
        profileName.setEnabled(false);
        profileAge.setEnabled(false);
        profilePhone.setEnabled(false);

        //Setting Profile Details
        //for reducing delay use shared pref/sql for data loading while registration(for fututre reference)
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        new FirebaseDatabaseHelper().readUser("UserDetails/" + user.getUid(), new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(userModel userModel) {
                if(!TextUtils.isEmpty(userModel.getEmail())){
                    profileEmail.setText(userModel.getEmail());
                }
                if(!TextUtils.isEmpty(userModel.getFullName())){
                    profileName.setText(userModel.getFullName());
                }
                if(!TextUtils.isEmpty(userModel.getAge())){
                    profileAge.setText(userModel.getAge());
                }
                if(!TextUtils.isEmpty(userModel.getPhoneNo())){
                    profilePhone.setText(userModel.getPhoneNo());
                }
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

            @Override
            public void TripDataIsLoaded(List<tripModel> trips) {

            }
        });

        //Logout Activity
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(view.getContext(), "Logging Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        //Enabling and disabling editing

        profileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEditable) {
                    profileName.setEnabled(true);
                    profileName.setFocusableInTouchMode(true);
                    profileName.requestFocus();
                    profileAge.setEnabled(true);
                    profileAge.setFocusableInTouchMode(true);
                    profileAge.requestFocus();
                    profilePhone.setEnabled(true);
                    profilePhone.setFocusableInTouchMode(true);
                    profilePhone.requestFocus();
                    profileConfirmButton.setClickable(true);
                    isEditable = true;
                    profileEditButton.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                }
                else{
                    profileName.setEnabled(false);
                    profileAge.setEnabled(false);
                    profilePhone.setEnabled(false);
                    profileConfirmButton.setClickable(false);
                    isEditable = false;
                    int Red = Color.RED;
                    profileEditButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                }

            }
        });

        //Updating the user details
        profileConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateUserName = profileName.getText().toString();
                String updateUserEmail = profileEmail.getText().toString();
                String updateUserAge = profileAge.getText().toString();
                String updatePhoneNo = profilePhone.getText().toString();
                DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("UserDetails/"+user.getUid());
                Map<String, Object> updateUserData = new HashMap<>();
                updateUserData.put("fullName", updateUserName);
                updateUserData.put("email", updateUserEmail);
                updateUserData.put("age", updateUserAge);
                updateUserData.put("phoneNo", updatePhoneNo);
                dRef.updateChildren(updateUserData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        profileName.setEnabled(false);
                        profileAge.setEnabled(false);
                        profilePhone.setEnabled(false);
                        profileConfirmButton.setClickable(false);
                    }
                });
            }
        });
        return view;
    }
}

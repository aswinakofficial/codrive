package com.codrive.app;

import android.app.Activity;
import android.content.Intent;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CompletableFuture;

public class UserProfileFragment extends Fragment {

    FloatingActionButton logoutButton;
    ImageButton imageUploadButton;
    ImageView profileImage;
    TextInputEditText profileEmail;
    TextInputEditText profileName;
    TextInputEditText profileAge;
    TextInputEditText profilePhone;
    FloatingActionButton profileEditButton;
    FloatingActionButton profileConfirmButton;
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
        imageUploadButton = view.findViewById(R.id.imageUploadButton);
        profileImage = view.findViewById(R.id.profieImage);
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
        imageUploadButton.setClickable(false);

        //Selecting image using imagebutton

        imageUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1234);
            }
        });

        //Setting Profile Details
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
                    profileAge.setText(Integer.parseInt(userModel.getEmail()));
                }
                if(!TextUtils.isEmpty(userModel.getPhoneNo())){
                    profilePhone.setText(Integer.parseInt(userModel.getPhoneNo()));
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

        //Enabling editing

        profileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileEmail.setEnabled(true);
                profileEmail.setFocusableInTouchMode(true);
                profileEmail.requestFocus();
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

            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            profileImage.setImageURI(selectedImageUri);
        }
    }
}

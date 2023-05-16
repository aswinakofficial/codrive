package com.codrive.app;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.net.vcn.VcnUnderlyingNetworkTemplate;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

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

        //Selecting image using imagebutton

        imageUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1234);
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

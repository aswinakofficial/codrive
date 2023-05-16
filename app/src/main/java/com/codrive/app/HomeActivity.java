package com.codrive.app;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.codrive.app.ActivityMainBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getSupportActionBar().hide();

        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());


        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.shorts:
                    replaceFragment(new CreateTripFragment());
                    break;
                case R.id.subscriptions:
                    replaceFragment(new ManageTripFragment());
                    break;
                case R.id.library:
                    replaceFragment(new UserProfileFragment());
                    break;
            }

            return true;
        });



        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });

        //side drawer navigation
//        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
//        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.navigation_view);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                drawerLayout.openDrawer(GravityCompat.START);
//
//            }
//        });
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
//
//                int id = item.getItemId();
//                drawerLayout.closeDrawer(GravityCompat.START);
//                switch (id)
//                {
//
//                    case R.id.nav_home:
//                        Toast.makeText(HomeActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();break;
//                    case R.id.nav_message:
//                        Toast.makeText(HomeActivity.this, "Message is Clicked",Toast.LENGTH_SHORT).show();break;
//                    case R.id.synch:
//                        Toast.makeText(HomeActivity.this, "Synch is Clicked",Toast.LENGTH_SHORT).show();break;
//                    case R.id.trash:
//                        Toast.makeText(HomeActivity.this, "Trash is Clicked",Toast.LENGTH_SHORT).show();break;
//                    case R.id.settings:
//                        Toast.makeText(HomeActivity.this, "Settings is Clicked",Toast.LENGTH_SHORT).show();break;
//                    case R.id.logoutItem:
//                        FirebaseAuth.getInstance().signOut();
//                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
//                        finish();
//                        Toast.makeText(HomeActivity.this, "Logging Out",Toast.LENGTH_SHORT).show();break;
//                    case R.id.nav_share:
//                        Toast.makeText(HomeActivity.this, "Share is clicked",Toast.LENGTH_SHORT).show();break;
//                    case R.id.nav_rate:
//                        Toast.makeText(HomeActivity.this, "Rate us is Clicked",Toast.LENGTH_SHORT).show();break;
//                    default:
//                        return true;
//
//                }
//                return true;
//            }
//        });

    }
    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(HomeActivity.this,"Upload a Video is clicked",Toast.LENGTH_SHORT).show();

            }
        });

        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(HomeActivity.this,"Create a short is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(HomeActivity.this,"Go live is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}
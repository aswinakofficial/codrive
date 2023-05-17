package com.codrive.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {

    TextInputEditText registerEmail;
    TextInputEditText  registerName;
    TextInputEditText registerConPass;
    Button registerButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registerEmail = findViewById(R.id.registerEmail);
        registerName = findViewById(R.id.registerName);
        registerConPass = findViewById(R.id.registrationPassword);
        registerButton = findViewById(R.id.registrationButton);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();





        //Registration Process

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "Loading..", Toast.LENGTH_SHORT).show();
                String userEmail = registerEmail.getText().toString();
                String userName = registerName.getText().toString();
                String userConPass = registerConPass.getText().toString();

                //Check for empty details
                if(TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userName) || TextUtils.isEmpty(userConPass)){
                    Toast.makeText(SignupActivity.this, "Enter Details", Toast.LENGTH_SHORT).show();
                }
                //Proceed to registration
                else {
                    mAuth.createUserWithEmailAndPassword(userEmail,userConPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if registration is successful
                            if (task.isSuccessful()){
                                userModel userModel = new userModel(userName, userEmail, "", "");
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                DatabaseReference databaseReference = firebaseDatabase.getReference("UserDetails");
                                databaseReference.child(user.getUid()).setValue(userModel);
                                Toast.makeText(SignupActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
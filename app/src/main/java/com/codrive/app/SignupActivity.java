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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    TextInputEditText registerEmail;
    TextInputEditText  registerPass;
    TextInputEditText registerConPass;
    Button registerButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registerEmail = findViewById(R.id.registerEmail);
        registerPass = findViewById(R.id.registrationPass);
        registerConPass = findViewById(R.id.registerConPass);
        registerButton = findViewById(R.id.registrationButton);
        mAuth = FirebaseAuth.getInstance();

        //Registration Process

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = registerEmail.getText().toString();
                String userPass = registerPass.getText().toString();
                String userConPass = registerConPass.getText().toString();

                //Check for empty details
                if(TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPass) || TextUtils.isEmpty(userConPass)){
                    Toast.makeText(SignupActivity.this, "Enter Details", Toast.LENGTH_SHORT).show();
                }
                //Password and confirm password crosscheck
                else if (!userPass.equals(userConPass)) {
                    Toast.makeText(SignupActivity.this, "Password Does Not Match!!", Toast.LENGTH_SHORT).show();
                }
                //Proceed to registration
                else {
                    mAuth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if registration is successful
                            if (task.isSuccessful()){
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
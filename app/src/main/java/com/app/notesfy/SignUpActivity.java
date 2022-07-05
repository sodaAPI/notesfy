package com.app.notesfy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout regUser, regEmail, regPass;
    Button btnLogin, btnRegister;
    TextView text_or, signupLogo;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Hooks
        regUser = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPass = findViewById(R.id.reg_password);
        btnLogin = findViewById(R.id.btnLog);
        btnRegister = findViewById(R.id.btnSign);
        text_or = findViewById(R.id.tv_or);
        signupLogo = findViewById(R.id.signuplogo);

        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);

                Pair[] pairs = new Pair[5];
                pairs [0] = new Pair<View, String>(btnLogin, "login_btn");
                pairs [1] = new Pair<View, String>(btnRegister, "reg_btn");
                pairs [2] = new Pair<View, String>(signupLogo, "logo_text");
                pairs [3] = new Pair<View, String>(regEmail, "email_trans");
                pairs [4] = new Pair<View, String>(regPass, "pw_trans");

                ActivityOptionsCompat optionsSign = ActivityOptionsCompat.makeSceneTransitionAnimation(SignUpActivity.this,pairs);

                startActivity(intent, optionsSign.toBundle());
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = regUser.getEditText().getText().toString().trim();
                String email = regEmail.getEditText().getText().toString().trim();
                String password = regPass.getEditText().getText().toString().trim();

                if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else if (password.length()<7){
                    Toast.makeText(SignUpActivity.this, "Password should be greater than 7 digits", Toast.LENGTH_SHORT).show();
                } else {
                    // Register User
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("users");
//
//                // Get all Values
//                String username = regUser.getEditText().getText().toString();
//                String email = regEmail.getEditText().getText().toString();
//                String password = regPass.getEditText().getText().toString();
//
//                UserHelper helperClass = new UserHelper(username, email, password);
//
//                reference.setValue(helperClass);

            }
        });
    }

    private void sendEmailVerification() {

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(SignUpActivity.this, "Verification email is sent, Please verify and login.", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                }
            });
        } else {
            Toast.makeText(SignUpActivity.this, "Failed to verify email, Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

}
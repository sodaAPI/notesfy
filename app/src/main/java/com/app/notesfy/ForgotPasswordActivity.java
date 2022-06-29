package com.app.notesfy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextInputLayout forgotEmail;
    Button btnRecover;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

//        Objects.requireNonNull(getSupportActionBar()).hide();

        forgotEmail = findViewById(R.id.forgot_email);
        btnRecover = findViewById(R.id.btnRecov);

        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();

        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = forgotEmail.getEditText().getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(ForgotPasswordActivity.this, "Enter your email first!", Toast.LENGTH_LONG).show();
                }
                else {
                    // Password Recover

                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity.this, "Mail sent, check your email to reset password.", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
                            } else {
                                Toast.makeText(ForgotPasswordActivity.this, "Email is wrong or doesn't exist.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}
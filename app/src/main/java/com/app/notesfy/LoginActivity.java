package com.app.notesfy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout logUser, loginEmail, LoginPassword;
    ProgressBar loadingLog;
    Button btnLogin, btnRegister, forgotPassword;
    TextView text_or, loginLogo;


    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hooks
        loginEmail = findViewById(R.id.logEmail);
        LoginPassword = findViewById(R.id.logPassword);
        btnLogin = findViewById(R.id.btnLog);
        btnRegister = findViewById(R.id.btnSign);
        forgotPassword = findViewById(R.id.forgotPass);
        loadingLog = findViewById(R.id.loadingLogin);
        text_or = findViewById(R.id.tv_or);
        loginLogo = findViewById(R.id.loginlogo);

        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

//        if (firebaseUser != null){
//            finish();
//            startActivity(new Intent(LoginActivity.this,NotesActivity.class));
//        }

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);

                Pair[] pairs = new Pair[3];
                pairs [0] = new Pair<View, String>(loginLogo, "logo_text");
                pairs [1] = new Pair<View, String>(loginEmail, "email_trans");
                pairs [2] = new Pair<View, String>(btnLogin, "login_btn");

                ActivityOptionsCompat optionsFP = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this,pairs);

                startActivity(intent, optionsFP.toBundle());
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);

                Pair[] pairs = new Pair[5];
                pairs [0] = new Pair<View, String>(btnLogin, "login_btn");
                pairs [1] = new Pair<View, String>(btnRegister, "reg_btn");
                pairs [2] = new Pair<View, String>(loginLogo, "logo_text");
                pairs [3] = new Pair<View, String>(loginEmail, "email_trans");
                pairs [4] = new Pair<View, String>(LoginPassword, "pw_trans");

                ActivityOptionsCompat optionsLog = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this,pairs);

                startActivity(intent, optionsLog.toBundle());
            }
        });

    }

    private void CheckLogin() {

        String email = loginEmail.getEditText().getText().toString().trim();
        String password = LoginPassword.getEditText().getText().toString().trim();

        if(password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
        } else {
            // Login User

            loadingLog.setVisibility(View.VISIBLE);

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        checkEmailVerification();
                    } else {
                        Toast.makeText(LoginActivity.this, "Email or account doesn't exist.", Toast.LENGTH_SHORT).show();
                        loadingLog.setVisibility(View.INVISIBLE);
                    }

                }
            });
        }

//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
//                        }
//
//                        // ...
//                    }
//                });

    }

    private void checkEmailVerification() {

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser.isEmailVerified()== true){

            Toast.makeText(LoginActivity.this, "You are logged in.", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(LoginActivity.this,NotesActivity.class));
        } else {
            loadingLog.setVisibility(View.INVISIBLE);
            Toast.makeText(LoginActivity.this, "Please verify your email first.", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }

    }
}
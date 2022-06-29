package com.app.notesfy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseLoginSignup_Activity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    ImageView image;
    TextView desc, text_or;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_signup);

        // Hooks
        btnLogin = findViewById(R.id.btnLog);
        btnRegister = findViewById(R.id.btnSign);
        image = findViewById(R.id.logo);
        desc = findViewById(R.id.tv_desc);
        text_or = findViewById(R.id.tv_or);

        getSupportActionBar().hide();

        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(ChooseLoginSignup_Activity.this,LoginActivity.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(ChooseLoginSignup_Activity.this,SignUpActivity.class);
            startActivity(intent);
        });

    }
}
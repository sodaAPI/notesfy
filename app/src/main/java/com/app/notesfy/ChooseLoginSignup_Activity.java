package com.app.notesfy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

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
//            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ChooseLoginSignup_Activity.this, btnLogin, ViewCompat.getTransitionName(btnLogin));

            Pair[] pairs = new Pair[3];
            pairs [0] = new Pair<View, String>(btnLogin, "login_btn");
            pairs [1] = new Pair<View, String>(btnRegister, "reg_btn");
            pairs [2] = new Pair<View, String>(text_or, "tv_or");

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ChooseLoginSignup_Activity.this,pairs);

            startActivity(intent, options.toBundle());
        });

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(ChooseLoginSignup_Activity.this,SignUpActivity.class);
//            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ChooseLoginSignup_Activity.this, btnRegister, ViewCompat.getTransitionName(btnRegister));

            Pair[] pairs = new Pair[3];
            pairs [0] = new Pair<View, String>(btnLogin, "login_btn");
            pairs [1] = new Pair<View, String>(btnRegister, "reg_btn");
            pairs [2] = new Pair<View, String>(text_or, "tv_or");

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ChooseLoginSignup_Activity.this,pairs);

            startActivity(intent, options.toBundle());
        });

    }
}
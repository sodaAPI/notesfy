package com.app.notesfy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {

    private  static  int SPLASH_SCREEN = 5000;

    //Variables
    Animation topAnim, rightAnim;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Animations
        rightAnim = AnimationUtils.loadAnimation(this,R.anim.right_anim);

        //Hooks
        image = findViewById(R.id.logo);
        getSupportActionBar().hide();

        //Set Animation
        image.setAnimation(rightAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,ChooseLoginSignup_Activity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
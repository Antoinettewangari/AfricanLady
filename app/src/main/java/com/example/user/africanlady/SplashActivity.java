package com.example.user.africanlady;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private static int splashTimeOut=3000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView=(ImageView)findViewById(R.id.welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        },splashTimeOut);


        Animation a= AnimationUtils.loadAnimation(this,R.anim.mysplashanimation);
        imageView.startAnimation(a);

    }
    }


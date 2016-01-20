package com.example.zahan.kuetbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


public class Splash extends Activity {
    Intent mainintent;
    ImageView image;
    ImageView bus;
    Animation down, up, animation, still;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        image = (ImageView) findViewById(R.id.image);
        bus = (ImageView) findViewById(R.id.bus);
        mainintent = new Intent("com.example.zahan.kuetbus.MainActivity");
        still = new TranslateAnimation(0, 0, 0, 0);
        still.setDuration(1000);
        still.setFillAfter(true);
        down = new TranslateAnimation(0, -50, 0, 0);
        down.setDuration(800);
        down.setFillAfter(true);
        animation = new TranslateAnimation(-50, 1000, 0, 0);
        animation.setDuration(1000);
        animation.setFillAfter(false);
        up = new TranslateAnimation(0, 0, -100, 100);
        up.setDuration(500);
        up.setFillAfter(true);


        still.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation still) {

            }

            @Override
            public void onAnimationEnd(Animation still) {

                image.startAnimation(down);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        down.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation down) {

            }

            @Override
            public void onAnimationEnd(Animation down) {

                image.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                image.setVisibility(View.INVISIBLE);
                bus.setVisibility(View.VISIBLE);
                bus.startAnimation(up);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        up.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation up) {


            }

            @Override
            public void onAnimationEnd(Animation up) {


                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(mainintent);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        image.startAnimation(still);

    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
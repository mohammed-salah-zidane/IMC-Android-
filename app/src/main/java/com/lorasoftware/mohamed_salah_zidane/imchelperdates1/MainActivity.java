package com.lorasoftware.mohamed_salah_zidane.imchelperdates1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import gr.net.maroulis.library.EasySplashScreen;

public class MainActivity extends AppCompatActivity {

    ImageView logo;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*logo = (ImageView)findViewById(R.id.imc);
        @SuppressLint("ResourceType") Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.animator.translate);
        logo.startAnimation( anim);

        @SuppressLint("ResourceType") Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(),R.animator.alpha);
        logo.startAnimation( anim);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
        startActivity(intent);
*/


        EasySplashScreen config = new EasySplashScreen(MainActivity.this)
                .withFullScreen()
                .withTargetActivity(HomeActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundResource(R.drawable.wallpaper)
                .withLogo(R.drawable.imc)
                .withFooterText("IMC IT Dept. ©2019")
                .withAfterLogoText("International Medical Center");
        config.getFooterTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setFontFeatureSettings("Georgia");
        config.getFooterTextView().setFontFeatureSettings("Georgia");
        config.getLogo().setScaleType(ImageView.ScaleType.CENTER);
        /*config.getLogo().setScaleX((float) 0.3);
        config.getLogo().setScaleY((float) 0.3);
        config.getAfterLogoTextView().setScaleX((float) 0.7);
        config.getAfterLogoTextView().setScaleY((float) 0.7);
        config.getAfterLogoTextView().setTranslationY(-340);*/

        @SuppressLint("ResourceType") Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(),R.animator.alpha);
        config.getLogo().startAnimation(anim1);

        config.getAfterLogoTextView().startAnimation(anim1);

        View view = config.create();
        setContentView(view);
    }
}

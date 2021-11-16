package com.example.daybreak;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static int TIMEOUT = 3000;

    // Variables
    Animation topAnimation, bottomAnimation;
    ImageView image;
    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Launch the layout -> splash.xml
        setContentView(R.layout.activity_splashscreen);


        // Animations
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        // Hooks
        image = findViewById(R.id.daybreak);
        slogan = findViewById(R.id.slogan);
        logo = findViewById(R.id.company_name);

        image.setAnimation(topAnimation);
        logo.setAnimation(topAnimation);
        slogan.setAnimation(bottomAnimation);

        // Launching Main Activity Class
        Thread splashThread = new Thread(() -> {
            try {
                Thread.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // To Start the thread
        splashThread.start();
    }
}
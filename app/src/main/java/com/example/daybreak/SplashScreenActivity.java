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
    TextView app_name, app_slogan;

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
        image = findViewById(R.id.app_logo);
        app_slogan = findViewById(R.id.app_slogan);
        app_name = findViewById(R.id.app_name);

        image.setAnimation(topAnimation);
        app_name.setAnimation(topAnimation);
        app_slogan.setAnimation(bottomAnimation);

        // Launching Main Activity Class
        Thread splashThread = new Thread(() -> {
            try {
                Thread.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // To Start the thread
        splashThread.start();
    }
}
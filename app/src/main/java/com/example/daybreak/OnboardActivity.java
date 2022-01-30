package com.example.daybreak;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OnboardActivity extends AppCompatActivity {

    private SharedPreferences loginPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        Bundle animationBundle = ActivityOptions.makeCustomAnimation(this, R.animator.slide_in_right,
                R.animator.slide_out_left).toBundle();

        // Shared preference
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        boolean saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if (saveLogin == true) {
            Intent i = new Intent(OnboardActivity.this, MainActivity.class);
            startActivity(i);
        }

        // Linkage to the sign in button
        Button sign_in_button = (Button) findViewById(R.id.sign_in_button);
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent intent = new Intent(OnboardActivity.this, LoginActivity.class);
                startActivity(intent, animationBundle);
            }
        });

        // Linkage to the sign up button
        Button sign_up_button = (Button) findViewById(R.id.sign_up_button);
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent intent = new Intent(OnboardActivity.this, SignupActivity.class);
                startActivity(intent, animationBundle);
            }
        });

        // Possible viewPager2?
    }
}
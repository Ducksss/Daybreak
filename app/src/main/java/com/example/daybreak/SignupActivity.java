package com.example.daybreak;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Bundle animationBundle = ActivityOptions.makeCustomAnimation(this, R.animator.slide_in_left,
                R.animator.slide_out_right).toBundle();

        // Back button
        ImageView back_button = (ImageView) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent intent = new Intent(SignupActivity.this, OnboardActivity.class);
                startActivity(intent, animationBundle);
            }
        });

        // Sign up button
        Button sign_up_button = (Button) findViewById(R.id.sign_up_button);
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent, animationBundle);
            }
        });
    }
}
package com.example.daybreak;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VerifyEmailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        Bundle animationBundle = ActivityOptions.makeCustomAnimation(this, R.animator.slide_in_left,
                R.animator.slide_out_right).toBundle();

        Bundle animationBundle2 = ActivityOptions.makeCustomAnimation(this, R.animator.slide_in_right,
                R.animator.slide_out_left).toBundle();

        switch (view.getId()) {
            case R.id.back_button:
                i = new Intent(VerifyEmailActivity.this, SignupActivity.class);
                startActivity(i, animationBundle);

            case R.id.skip_button:
                i = new Intent(VerifyEmailActivity.this, LoginActivity.class);
                startActivity(i, animationBundle2);
                finish();

            case R.id.resend_email_button:

        }
    }
}
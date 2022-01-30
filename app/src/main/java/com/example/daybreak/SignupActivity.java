package com.example.daybreak;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        Bundle animationBundle = ActivityOptions.makeCustomAnimation(this, R.animator.slide_in_left,
                R.animator.slide_out_right).toBundle();

        Bundle animationBundle2 = ActivityOptions.makeCustomAnimation(this, R.animator.slide_in_right,
                R.animator.slide_out_left).toBundle();

        switch (v.getId()) {
            case R.id.back_button:
                Intent intent = new Intent(SignupActivity.this, OnboardActivity.class);
                startActivity(intent, animationBundle);

            case R.id.sign_up_button:
                TextInputLayout username_text_input = findViewById(R.id.username_text_input);
                TextInputLayout email_text_input = findViewById(R.id.email_text_input);
                TextInputLayout contact_number_text_input = findViewById(R.id.contact_number_text_input);
                TextInputLayout password_text_input = findViewById(R.id.password_text_input);

                final String username_text_input_string = username_text_input.getEditText().getText().toString();
                final String email_text_input_string = email_text_input.getEditText().getText().toString();
                final String contact_number_text_input_string = contact_number_text_input.getEditText().getText().toString();
                final String password_text_input_string = password_text_input.getEditText().getText().toString();

                // ========= VALIDATION =========
                // Username validation
                if (!isFieldValid(username_text_input_string)) {
                    username_text_input.setError("Please enter your username!");
                    username_text_input.requestFocus();
                    return;
                } else {
                    username_text_input.setErrorEnabled(false);
                }

                // Email validation
                if (!isFieldValid(email_text_input_string)) {
                    email_text_input.setError("Please enter your email!");
                    email_text_input.requestFocus();
                    return;
                } else {
                    email_text_input.setErrorEnabled(false);
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email_text_input_string).matches()) {
                    email_text_input.setError("Please enter a valid email address!");
                    email_text_input.requestFocus();
                    return;
                } else {
                    email_text_input.setErrorEnabled(false);
                }

                // Contact Number validation
                if (!isFieldValid(contact_number_text_input_string)) {
                    contact_number_text_input.setError("Please enter your contact number!");
                    contact_number_text_input.requestFocus();
                    return;
                } else {
                    contact_number_text_input.setErrorEnabled(false);
                }

                if (!Patterns.PHONE.matcher(contact_number_text_input_string).matches()) {
                    contact_number_text_input.setError("Please enter a valid contact number!");
                    contact_number_text_input.requestFocus();
                    return;
                } else {
                    contact_number_text_input.setErrorEnabled(false);
                }

                // Password validation
                if (!isFieldValid(password_text_input_string)) {
                    password_text_input.setError("Please enter your password!");
                    password_text_input.requestFocus();
                    return;
                } else {
                    password_text_input.setErrorEnabled(false);
                }

                if (!(password_text_input_string.length() >= 8)) {
                    password_text_input.setError("Your password must be a minimum of 8 characters");
                    password_text_input.requestFocus();
                    return;
                } else {
                    password_text_input.setErrorEnabled(false);
                }

                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email_text_input_string, password_text_input_string)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                   @Override
                                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                                       if (task.isSuccessful()) {
                                                           User user = new User(username_text_input_string, email_text_input_string, contact_number_text_input_string, password_text_input_string, false);

                                                           // Firebase table
                                                           FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://daybreak-b9a84-default-rtdb.asia-southeast1.firebasedatabase.app");
                                                           // Getting a reference of the table
                                                           DatabaseReference reference = rootNode.getReference("Users");
                                                           // Adding a user class to firebase
                                                           reference.child(FirebaseAuth
                                                                   .getInstance()
                                                                   .getCurrentUser()
                                                                   .getUid())
                                                                   .setValue(user)
                                                                   .addOnCompleteListener(
                                                                           new OnCompleteListener<Void>() {
                                                                               @Override
                                                                               public void onComplete(@NonNull Task<Void> task) {
                                                                                   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                                                   if (task.isSuccessful()) {
                                                                                       if (!user.isEmailVerified()) {
                                                                                           user.sendEmailVerification();
                                                                                       }

                                                                                       Intent intent = new Intent(SignupActivity.this, VerifyEmailActivity.class);
                                                                                       startActivity(intent, animationBundle2);
                                                                                   }
                                                                               }
                                                                           }
                                                                   );
                                                       } else {
                                                           email_text_input.setError("The email has already been taken!");
                                                           email_text_input.requestFocus();
                                                       }
                                                   }
                                               }
                        );
        }
    }

    public boolean isFieldValid(@Nullable String text) {
        return text.length() > 0;
    }
}
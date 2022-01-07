package com.example.daybreak;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Boolean saveLogin;
    private CheckBox save_login_checkbox;
    private TextInputLayout email_text_input;
    private TextInputEditText email_edit_text;
    private TextInputLayout passwordTextInput;
    private TextInputEditText passwordEditText;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // For better coding, remove this when the app is ready
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        passwordTextInput = findViewById(R.id.password_text_input);
        passwordEditText = findViewById(R.id.password_edit_text);
        email_text_input = findViewById(R.id.email_text_input);
        email_edit_text = findViewById(R.id.email_edit_text);
        save_login_checkbox = (CheckBox) findViewById(R.id.save_login_checkbox);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            email_edit_text.setText(loginPreferences.getString("username", ""));
            passwordEditText.setText(loginPreferences.getString("password", ""));
            save_login_checkbox.setChecked(true);
        }

        // KEYSTROKE LISTENER
        // Clear the error once more than 8 characters are typed.
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setErrorEnabled(false);
                }

                return false;
            }
        });

        email_edit_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((isEmailValid(email_text_input.getEditText().getText().toString()))) {
                    email_text_input.setErrorEnabled(false);
                }

                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent i;
        Bundle animationBundle = ActivityOptions.makeCustomAnimation(this, R.animator.slide_in_left,
                R.animator.slide_out_right).toBundle();


        switch (v.getId()) {
            case R.id.back_button:
                Intent intent = new Intent(LoginActivity.this, OnboardActivity.class);
                startActivity(intent, animationBundle);

            case R.id.sign_in_button:
                final String email_text_input_string = email_text_input.getEditText().getText().toString();
                final String password_text_input_string = passwordTextInput.getEditText().getText().toString();

                // ========= VALIDATION =========
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

                if (!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError("Incorrect password!");
                }

                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email_text_input_string, password_text_input_string)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (save_login_checkbox.isChecked()) {
                                        loginPrefsEditor.putBoolean("saveLogin", true);
                                        loginPrefsEditor.putString("username", email_text_input_string);
                                        loginPrefsEditor.putString("password", password_text_input_string);
                                        loginPrefsEditor.commit();
                                    } else {
                                        loginPrefsEditor.clear();
                                        loginPrefsEditor.commit();
                                    }

                                    passwordTextInput.setErrorEnabled(false); // Clear the error
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    passwordTextInput.setError("Incorrect password!");
                                }
                            }
                        });
        }
    }

    /*
       In reality, this will have more complex logic including, but not limited to, actual
       authentication of the username and password.
    */
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }

    public boolean isEmailValid(@Nullable String text) {
        if (!isFieldValid(text)) {
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            return false;
        }

        return true;
    }

    public boolean isFieldValid(@Nullable String text) {
        return text.length() > 0;
    }
}
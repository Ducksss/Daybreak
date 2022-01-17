package com.example.daybreak.Timer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.daybreak.MainActivity;
import com.example.daybreak.R;

public class EndTimer extends Fragment {

    View view;
    TextView timerCount;
    private SharedPreferences timerPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_timer_end, container, false);
        timerPreferences = this.getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        loginPrefsEditor = timerPreferences.edit();
        timerCount = view.findViewById(R.id.timer_count);
        timerCount.setText(Integer.toString(timerPreferences.getInt("focusamount", 1)) +" Times");
        // Check if threshold is met
        if (true) {
            loginPrefsEditor.putInt("focusamount", timerPreferences.getInt("focusamount", 0)+1);
        } else {
            loginPrefsEditor.clear();
        }
        loginPrefsEditor.commit();

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button homeButton = view.findViewById(R.id.timer_home_button);
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}

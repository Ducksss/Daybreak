package com.example.daybreak.Timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.daybreak.R;

import java.util.Locale;

public class ContinuedTimer extends Fragment {
    View view;
    TextView tv;
    ItemViewModel viewModel;
    private static final long START_TIME_IN_MILLIS = 600000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long initialValue = 0;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_timer_continued, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        tv = view.findViewById(R.id.txt);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        initialValue = Integer.parseInt(viewModel.getSelectedItem().getValue()) * 60000;
        new CountDownTimer(Integer.parseInt(viewModel.getSelectedItem().getValue()) * 60000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                double percentageLeft = ((double) mTimeLeftInMillis / (double) initialValue) * 100;
                progressBar.setProgress((int) percentageLeft);
                progressBar.setMax(100);
                updateCountDownText();
            }

            public void onFinish() {
                tv.setText("Done!");
            }

        }.start();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        ImageView button = view.findViewById(R.id.timer_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            //Back Button
            @Override
            public void onClick(View v) {
                replaceFragment(new StartTimer());
                viewModel.setData("");
            }
        });
        //end Button
        Button endTimer = view.findViewById(R.id.sendData1btn);
        endTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add shared preference here

                replaceFragment(new EndTimer());
            }
        });
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tv.setText(timeLeftFormatted);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }
}





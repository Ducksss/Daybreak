package com.example.daybreak.Timer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.media.MediaPlayer;

import com.example.daybreak.R;

import org.w3c.dom.Text;

import java.util.Locale;

public class ContinuedTimer extends Fragment {
    View view;
    TextView textView;
    String TAG = "Main";
    TextView tv;
    ItemViewModel viewModel;
    private static final long START_TIME_IN_MILLIS = 600000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_timer_continued, container, false);

        tv = view.findViewById(R.id.txt);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        textView = view.findViewById(R.id.fragmentText);
        System.out.println(viewModel.getSelectedItem().getValue());
        new CountDownTimer(Integer.parseInt(viewModel.getSelectedItem().getValue())*60000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                tv.setText("done!");
            }

        }.start();



        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        Button button = view.findViewById(R.id.timer_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            //Back Button
            @Override
            public void onClick(View v) {
                replaceFragment(new StartTimer());
                viewModel.setData("");
            }
        });
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tv.setText(timeLeftFormatted);
    }
    //REPLACES FRAGMENT WITH THE NEXT FRAGMENT
    // Note that getting activity before parent class activity is mandatory or binding does not occur
    // Also not that though it is an entirely lifecycle, retracting from lifecycle is possible
    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }
}





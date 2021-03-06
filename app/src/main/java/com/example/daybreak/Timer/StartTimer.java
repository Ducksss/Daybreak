package com.example.daybreak.Timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.daybreak.R;

public class StartTimer extends Fragment {
    View view;
    ItemViewModel viewModel;
    TextView textView;
    NumberPicker numberPicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_timer_selection, container, false);

        textView = view.findViewById(R.id.timerTextView);
        numberPicker = view.findViewById(R.id.minutes_numberSpinner);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(120);

        textView.setText(String.format("0%s:00", numberPicker.getValue()));
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                if (i1 < 10) {
                    textView.setText(String.format("0%s:00", i1));

                } else {
                    textView.setText(String.format("%s:00", i1));
                }
            }
        });
        //end Button
        ImageView endTimer = view.findViewById(R.id.selection_back_button);
        endTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        Button button = view.findViewById(R.id.sendData1btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setData(Integer.toString(numberPicker.getValue()));
                replaceFragment(new ContinuedTimer());
            }
        });
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }
}

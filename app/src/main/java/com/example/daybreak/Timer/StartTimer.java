package com.example.daybreak.Timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
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

        Button button = view.findViewById(R.id.sendData1btn);
        textView = view.findViewById(R.id.timerTextView);
        numberPicker = view.findViewById(R.id.minutes_numberSpinner);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(60);
        textView.setText(String.format("User's Number: %s", numberPicker.getValue()));
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textView.setText(String.format("Focus Time: %s Minutes", i1));
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        Button button = view.findViewById(R.id.sendData1btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                viewModel.setData(Integer.toString(numberPicker.getValue()));
                replaceFragment(new ContinuedTimer());
            }
        });
    }

    //REPLACES FRAGMENT WITH THE NEXT FRAGMENT
    // Note that getting activity before parent class activity is mandatory or binding does not occur
    // Also not that though it is an entirely lifecycle, retracting from lifecycle is possible
    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }
}

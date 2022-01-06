package com.example.daybreak.ui.home;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.daybreak.R;
import com.example.daybreak.Timer.TimerActivity;
import com.example.daybreak.databinding.FragmentHomeBinding;
import com.example.daybreak.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements StartTimer.FragmentAListener, FragmentB.FragmentBListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding view;

    private androidx.viewpager2.widget.ViewPager2 ViewPager2;
    private List<RecyclerVideoViewItem> RecyclerVideoViewItemsList;
    private RecyclerVideoViewItemAdapter RecyclerVideoViewItemAdapter;
    private StartTimer StartTimer;
    private FragmentB fragmentB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerVideoViewItemsList = new ArrayList<>();
        ViewPager2 = view.findViewById(R.id.animated_video_background);
        bindVideoData();
        ViewPager2.setAdapter(new RecyclerVideoViewItemAdapter(RecyclerVideoViewItemsList));
//        StartTimer = new StartTimer();
//        fragmentB = new FragmentB();
//
//        getParentFragmentManager().beginTransaction()
//                .replace(R.id.container_a, StartTimer)
//                .replace(R.id.container_b, fragmentB)
//                .commit();
// Set an error if the password is less than 8 characters.
        view.findViewById(R.id.start_focus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent i = new Intent(getActivity(), TimerActivity.class);
                    startActivity(i);


            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }

    private void bindVideoData() {
        RecyclerVideoViewItemsList.add(new RecyclerVideoViewItem("Sunset from the beach", "“If there is no struggle, there is no progress.” —Frederick Douglass", "android.resource://" + getActivity().getPackageName() + "/" + R.raw.water));
        RecyclerVideoViewItemsList.add(new RecyclerVideoViewItem("Rocky river in the forest", "“The best way out is always through.” ―Robert Frost", "android.resource://" + getActivity().getPackageName() + "/" + R.raw.video));
        RecyclerVideoViewItemsList.add(new RecyclerVideoViewItem("Water flowing in the river", "“Courage is like a muscle. We strengthen it by use.” —Ruth Gordo", "android.resource://" + getActivity().getPackageName() + "/" + R.raw.waves));
        RecyclerVideoViewItemsList.add(new RecyclerVideoViewItem("Lofi in da hood", "More is lost by indecision than wrong decision.” —Marcus Tullius Cicero", "android.resource://" + getActivity().getPackageName() + "/" + R.raw.video));
    }

    @Override
    public void onInputASent(CharSequence input) {
        StartTimer.updateEditText(input);
    }

    @Override
    public void onInputBSent(CharSequence input) {
        fragmentB.updateEditText(input);
    }
}
package com.example.daybreak.ui.home;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.daybreak.R;
import com.example.daybreak.databinding.FragmentHomeBinding;
import com.example.daybreak.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding view;

    private androidx.viewpager2.widget.ViewPager2 ViewPager2;
    private List<RecyclerVideoViewItem> RecyclerVideoViewItemsList;
    private RecyclerVideoViewItemAdapter RecyclerVideoViewItemAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerVideoViewItemsList = new ArrayList<>();
        ViewPager2 = view.findViewById(R.id.animated_video_background);
        bindVideoData();
        ViewPager2.setAdapter(new RecyclerVideoViewItemAdapter(RecyclerVideoViewItemsList));

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
}
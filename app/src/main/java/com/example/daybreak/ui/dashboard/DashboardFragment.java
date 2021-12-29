package com.example.daybreak.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.daybreak.R;
import com.example.daybreak.databinding.FragmentDashboardBinding;
import com.google.android.material.tabs.TabLayout;

public class DashboardFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    DashboardFragmentAdapter adapter;
    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Tab layout and View pager
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager2 = view.findViewById(R.id.view_pager2);
        FragmentManager fm = getParentFragmentManager();

        adapter = new DashboardFragmentAdapter(fm, getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Explore"));
        tabLayout.addTab(tabLayout.newTab().setText("Meditation"));
        tabLayout.addTab(tabLayout.newTab().setText("Sleep"));
        tabLayout.addTab(tabLayout.newTab().setText("Sound"));
        tabLayout.addTab(tabLayout.newTab().setText("Delta1"));
        tabLayout.addTab(tabLayout.newTab().setText("Delta2"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
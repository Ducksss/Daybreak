package com.example.daybreak.ui.explore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.daybreak.InnerExploreActivity;
import com.example.daybreak.MainActivity;
import com.example.daybreak.OnboardActivity;
import com.example.daybreak.R;
import com.example.daybreak.SignupActivity;
import com.google.android.material.card.MaterialCardView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView singlePracticeRecyclerView;
    private RecyclerView meditationSeriesRecyclerView;
    private RecyclerView forYouSeriesRecyclerView;
    private ArrayList<RecyclerExploreCardItem> singlePracticeItems = new ArrayList<>();
    private ArrayList<RecyclerExploreCardItem> meditationSeriesItems = new ArrayList<>();
    private ArrayList<RecyclerExploreLongCardItem> forYouSeriesItems = new ArrayList<>();

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        bindRecyclerViews(view);

        // Find View of the MaterialCard
        MaterialCardView materialCardView = view.findViewById(R.id.card_view);
        materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InnerExploreActivity.class);
                intent.putExtra("firstKeyName","FirstKeyValue");
                intent.putExtra("secondKeyName","SecondKeyValue");
                startActivity(intent);
            }
        });

        return view;
    }

    private void bindRecyclerViews(View view) {
        // Get data for recycler views
        bindPodCastData();

        // SINGLE SERIES
        bindSinglePracticeItems(view);

        // MEDITATION SERIES
        bindMeditationSeriesItems(view);

        // SELECTED MIX

        // FOR YOU
        bindForYouSeriesItems(view);
    }

    private void bindPodCastData() {
        // SINGLE SERIES
        singlePracticeItems.add(new RecyclerExploreCardItem("Cloud", "10 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_4)));
        singlePracticeItems.add(new RecyclerExploreCardItem("Sedentary", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_2)));
        singlePracticeItems.add(new RecyclerExploreCardItem("Morning Planning", "5-10 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_3)));
        singlePracticeItems.add(new RecyclerExploreCardItem("Insomnia", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.background_image_1)));
        singlePracticeItems.add(new RecyclerExploreCardItem("Work Burnout", "5-10 Min • Singles", getActivity().getDrawable(R.drawable.background_image_2)));
        singlePracticeItems.add(new RecyclerExploreCardItem("Falling Asleep", "5 Min • Singles", getActivity().getDrawable(R.drawable.background_image_3)));

        // MEDITATION SERIES
        meditationSeriesItems.add(new RecyclerExploreCardItem("Dreamland", "10 Min • Singles", getActivity().getDrawable(R.drawable.background_image_3)));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Fantasyland", "5-10 Min • Singles", getActivity().getDrawable(R.drawable.background_image_2)));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Morning Exercise", "5 Min • Singles", getActivity().getDrawable(R.drawable.background_image_1)));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Healthy Eating", "10 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_3)));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Happiness", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_2)));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Coping With Stress", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_4)));

        // SELECTED MIX

        // SELECTED STORY


        // FOR YOU
        forYouSeriesItems.add(new RecyclerExploreLongCardItem("Steps", "Slow down your pace, feel the wonderful coordination of your body, and meet your own true self.", getActivity().getDrawable(R.drawable.calm_background_4), new String[]{"Meditation", "Melody"}));
        forYouSeriesItems.add(new RecyclerExploreLongCardItem("Street", "I know this one the best. I think of the streets in Japan, which are quiet and full of lovely shops. I want this alarm!", getActivity().getDrawable(R.drawable.calm_background_1), new String[]{"Nature", "Sleep"}));
        forYouSeriesItems.add(new RecyclerExploreLongCardItem("Lighthouse", "As I inhale the impalpable breeze that set in upon me, the ocean mysterious rolls toward me closer and closer.", getActivity().getDrawable(R.drawable.calm_background_3), new String[]{"Nature", "Meditation"}));
        forYouSeriesItems.add(new RecyclerExploreLongCardItem("Library", "Close the book, still feeling what it was like to dwell in that light.", getActivity().getDrawable(R.drawable.calm_background_2), new String[]{"Performance", "Stress"}));
    }

    public void bindSinglePracticeItems(View view) {
        // SINGLE SERIES
        singlePracticeRecyclerView = view.findViewById(R.id.selected_podcasts);
        // Set Layout Manager to RecyclerView
        singlePracticeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        //Create adapter
        RecyclerExploreCardAdapter myRecyclerViewAdapter = new RecyclerExploreCardAdapter(singlePracticeItems, new RecyclerExploreCardAdapter.MyRecyclerViewItemClickListener() {
            //Handling clicks
            @Override
            public void onItemClicked(RecyclerExploreCardItem country) {
                Intent intent = new Intent(getActivity(), InnerExploreActivity.class);

                startActivity(intent);
            }
        });
        singlePracticeRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    public void bindMeditationSeriesItems(View view) {
        meditationSeriesRecyclerView = view.findViewById(R.id.meditation_series);
        // Set Layout Manager to RecyclerView
        meditationSeriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        //Create adapter
        RecyclerExploreCardAdapter myRecyclerViewAdapter2 = new RecyclerExploreCardAdapter(meditationSeriesItems, new RecyclerExploreCardAdapter.MyRecyclerViewItemClickListener() {
            //Handling clicks navigation
            @Override
            public void onItemClicked(RecyclerExploreCardItem country) {
                Intent intent = new Intent(getActivity(), InnerExploreActivity.class);
                startActivity(intent);
            }
        });

        meditationSeriesRecyclerView.setAdapter(myRecyclerViewAdapter2);
    }

    public void bindForYouSeriesItems(View view) {
        forYouSeriesRecyclerView = view.findViewById(R.id.for_you_series);
        // Set Layout Manager to RecyclerView
        forYouSeriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        //Create adapter
        RecyclerExploreLongCardAdapter myRecyclerViewAdapter3 = new RecyclerExploreLongCardAdapter(forYouSeriesItems, new RecyclerExploreLongCardAdapter.MyRecyclerViewItemClickListener() {
            @Override
            public void onItemClicked(RecyclerExploreLongCardItem title) {
                Intent intent = new Intent(getActivity(), InnerExploreActivity.class);
                intent.putExtra("Title", "dynamic text");
                startActivity(intent);
            }
        });
        forYouSeriesRecyclerView.setAdapter(myRecyclerViewAdapter3);
    }
}
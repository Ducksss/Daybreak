package com.example.daybreak.ui.explore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daybreak.InnerExploreActivity;
import com.example.daybreak.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView specialisedRecyclerview;
    private ArrayList<RecyclerExploreCardItem> specialisedContentItem = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        bindPodCastData();
        bindSinglePracticeItems(view);

        return view;
    }

    private void bindPodCastData() {
        // MEDITATION
        specialisedContentItem.add(new RecyclerExploreCardItem("Fantasyland", "5-10 Min • Singles", getActivity().getDrawable(R.drawable.background_image_3), R.drawable.background_image_3, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        specialisedContentItem.add(new RecyclerExploreCardItem("Healthy Eating", "10 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_3), R.drawable.calm_background_3, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        specialisedContentItem.add(new RecyclerExploreCardItem("Zen Mediation", "30 Min • Singles", getActivity().getDrawable(R.drawable.image_5), R.drawable.image_5, "Use our Zen music for meditation as calming music to bring soothing relaxation and healing to your body-mind. Through our meditation music, we hope you can experience true fulfilment and stress relief. This peaceful music, enhanced with binaural beats, is ideal relaxing music for autogenic practice and can be used as stress relief music or peaceful music when practicing meditation for anxiety during quarantine.  Our relaxing music is gentle piano music used for healing meditation, as healing music or as ambient music, perfect for meditation or relaxation."));
        specialisedContentItem.add(new RecyclerExploreCardItem("Relaxing with Japanese Instruments", "3 Hours • Singles", getActivity().getDrawable(R.drawable.image_6), R.drawable.image_6, "Relaxing With Japanese Bamboo Flute , Guzheng, Erhu | Musical Instrument Collection"));
        specialisedContentItem.add(new RecyclerExploreCardItem("Lofi hip hop to study to", "1 Hour • Singles", getActivity().getDrawable(R.drawable.image_7), R.drawable.image_7, "This playlist contains the smoothest lofi hip hop beats, perfect to help you chill or fall asleep"));
        specialisedContentItem.add(new RecyclerExploreCardItem("Rain Relaxation", "15 Min • Singles", getActivity().getDrawable(R.drawable.image_8), R.drawable.image_8, "Gentle Rain at night for Sleep, Insomnia, Study, Relax. Gentle Night Rain Sounds (No Music, No Thunder) Gentle Rain for 3 Hours with a Dark Screen - the Rain is just visible. Highly recommended for Sleeping and insomnia. Also ideal for Relaxing or Studying. Light Rain works well for Reducing Stress or Anxiety. Gentle Rain to Block Noise, Help Baby Sleep, Relax Pets, Improve Focus, Meditate, Sleep Instantly, Sleep Immediately, Sleep Fast, Sleep without Waking. Share with family & friends to help them feel better too. Enjoy the Gentle Rain..."));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.meditation_series_title_1), getString(R.string.meditation_series_duration_1), getActivity().getDrawable(R.drawable.image_19), R.drawable.image_19, getString(R.string.meditation_series_description_1)));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.meditation_series_title_2), getString(R.string.meditation_series_duration_2), getActivity().getDrawable(R.drawable.image_20), R.drawable.image_20, getString(R.string.meditation_series_description_2)));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.meditation_series_title_3), getString(R.string.meditation_series_duration_3), getActivity().getDrawable(R.drawable.image_21), R.drawable.image_21, getString(R.string.meditation_series_description_3)));
    }

    public void bindSinglePracticeItems(View view) {
        // SINGLE SERIES
        specialisedRecyclerview = view.findViewById(R.id.specialised_recyclerview);
        // Set Layout Manager to RecyclerView
        specialisedRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //Create adapter
        RecyclerExploreCardAdapter myRecyclerViewAdapter = new RecyclerExploreCardAdapter(specialisedContentItem, new RecyclerExploreCardAdapter.MyRecyclerViewItemClickListener() {
            //Handling clicks
            @Override
            public void onItemClicked(RecyclerExploreCardItem item) {
                Intent intent = new Intent(getActivity(), InnerExploreActivity.class);
                intent.putExtra("Title", item.getTitle());
                intent.putExtra("Subtitle", item.getSubtitle());
                intent.putExtra("Background", item.getDrawableID());
                intent.putExtra("Description", item.getInnerContent());
                startActivity(intent);
            }
        });

        specialisedRecyclerview.setAdapter(myRecyclerViewAdapter);
    }
}
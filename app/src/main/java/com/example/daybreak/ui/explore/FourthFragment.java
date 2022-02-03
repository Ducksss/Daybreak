package com.example.daybreak.ui.explore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daybreak.InnerExploreActivity;
import com.example.daybreak.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FourthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FourthFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView specialisedRecyclerview;
    private ArrayList<RecyclerExploreCardItem> specialisedContentItem = new ArrayList<>();

    public FourthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FourthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FourthFragment newInstance(String param1, String param2) {
        FourthFragment fragment = new FourthFragment();
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
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_1), getString(R.string.selected_mix_duration_1), getActivity().getDrawable(R.drawable.image_9), R.drawable.image_9, getString(R.string.selected_mix_description_1)));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_2), getString(R.string.selected_mix_duration_2), getActivity().getDrawable(R.drawable.image_10), R.drawable.image_10, getString(R.string.selected_mix_description_2)));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_3), getString(R.string.selected_mix_duration_3), getActivity().getDrawable(R.drawable.image_11), R.drawable.image_11, getString(R.string.selected_mix_description_3)));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_4), getString(R.string.selected_mix_duration_4), getActivity().getDrawable(R.drawable.image_12), R.drawable.image_12, getString(R.string.selected_mix_description_4)));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_5), getString(R.string.selected_mix_duration_5), getActivity().getDrawable(R.drawable.image_13), R.drawable.image_13, getString(R.string.selected_mix_description_5)));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_6), getString(R.string.selected_mix_duration_6), getActivity().getDrawable(R.drawable.image_14), R.drawable.image_14, getString(R.string.selected_mix_description_6)));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_7), getString(R.string.selected_mix_duration_7), getActivity().getDrawable(R.drawable.image_15), R.drawable.image_15, getString(R.string.selected_mix_description_7)));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_8), getString(R.string.selected_mix_duration_8), getActivity().getDrawable(R.drawable.image_16), R.drawable.image_16, getString(R.string.selected_mix_description_8)));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_9), getString(R.string.selected_mix_duration_9), getActivity().getDrawable(R.drawable.image_17), R.drawable.image_17, getString(R.string.selected_mix_description_9)));
        specialisedContentItem.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_10), getString(R.string.selected_mix_duration_10), getActivity().getDrawable(R.drawable.image_18), R.drawable.image_18, getString(R.string.selected_mix_description_10)));
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
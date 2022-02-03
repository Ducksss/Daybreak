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
 * Use the {@link FifthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FifthFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView specialisedRecyclerview;
    private ArrayList<RecyclerExploreCardItem> specialisedContentItem = new ArrayList<>();

    public FifthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FifthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FifthFragment newInstance(String param1, String param2) {
        FifthFragment fragment = new FifthFragment();
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
        specialisedContentItem.add(new RecyclerExploreCardItem("Cinderella Part 1", "15 Min • Singles", getActivity().getDrawable(R.drawable.cinderella), R.drawable.cinderella, getString(R.string.cinderella_description)));
        specialisedContentItem.add(new RecyclerExploreCardItem("Cinderella Part 2", "15 Min • Singles", getActivity().getDrawable(R.drawable.cinderella), R.drawable.cinderella, getString(R.string.cinderella_description)));
        specialisedContentItem.add(new RecyclerExploreCardItem("Cinderella Part 3", "15 Min • Singles", getActivity().getDrawable(R.drawable.cinderella), R.drawable.cinderella, getString(R.string.cinderella_description)));
        specialisedContentItem.add(new RecyclerExploreCardItem("Cinderella Part 4", "15 Min • Singles", getActivity().getDrawable(R.drawable.cinderella), R.drawable.cinderella, getString(R.string.cinderella_description)));
        specialisedContentItem.add(new RecyclerExploreCardItem("Cinderella Part 5", "15 Min • Singles", getActivity().getDrawable(R.drawable.cinderella), R.drawable.cinderella, getString(R.string.cinderella_description)));
        specialisedContentItem.add(new RecyclerExploreCardItem("Jack & The Beanstalk Part 1", "15 Min • Singles", getActivity().getDrawable(R.drawable.jack_bean), R.drawable.jack_bean, getString(R.string.jack_and_the_beanstalk_description)));
        specialisedContentItem.add(new RecyclerExploreCardItem("Jack & The Beanstalk Part 2", "15 Min • Singles", getActivity().getDrawable(R.drawable.jack_bean), R.drawable.jack_bean, getString(R.string.jack_and_the_beanstalk_description)));
        specialisedContentItem.add(new RecyclerExploreCardItem("Jack & The Beanstalk Part 3", "15 Min • Singles", getActivity().getDrawable(R.drawable.jack_bean), R.drawable.jack_bean, getString(R.string.jack_and_the_beanstalk_description)));
        specialisedContentItem.add(new RecyclerExploreCardItem("Jack & The Beanstalk Part 4", "15 Min • Singles", getActivity().getDrawable(R.drawable.jack_bean), R.drawable.jack_bean, getString(R.string.jack_and_the_beanstalk_description)));
        specialisedContentItem.add(new RecyclerExploreCardItem("Jack & The Beanstalk Part 5", "15 Min • Singles", getActivity().getDrawable(R.drawable.jack_bean), R.drawable.jack_bean, getString(R.string.jack_and_the_beanstalk_description)));
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
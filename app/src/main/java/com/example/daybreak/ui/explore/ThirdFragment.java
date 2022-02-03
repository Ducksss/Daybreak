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
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView specialisedRecyclerview;
    private ArrayList<RecyclerExploreCardItem> specialisedContentItem = new ArrayList<>();

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
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
        specialisedContentItem.add(new RecyclerExploreCardItem("Cloud", "10 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_4), R.drawable.calm_background_4, "The vast blue emptiness of the sky stared back at me as our jet plane soared through the air.\n" + "\n" + "My nerves began to run wild, but I knew the events we'd be experiencing would be worth it."));
        specialisedContentItem.add(new RecyclerExploreCardItem("Sedentary", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_2), R.drawable.calm_background_2, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        specialisedContentItem.add(new RecyclerExploreCardItem("Insomnia", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.background_image_1), R.drawable.background_image_1, "Everyone experiences Insomnia, it is how you deal with it that distinguishes you from the rest."));
        specialisedContentItem.add(new RecyclerExploreCardItem("Work Burnout", "5-10 Min • Singles", getActivity().getDrawable(R.drawable.background_image_2), R.drawable.background_image_2, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        specialisedContentItem.add(new RecyclerExploreCardItem("Evening Blues", "30 Min • Singles", getActivity().getDrawable(R.drawable.image_1), R.drawable.image_1, "The lone lamp post of the one-street town flickered, not quite dead but definitely on its way out. Suitcase by her side, she paid no heed to the light, the street or the town. A car was coming down the street and with her arm outstretched and thumb in the air, she had a plan."));
        specialisedContentItem.add(new RecyclerExploreCardItem("Gloom and Doom", "3 Hours • Singles", getActivity().getDrawable(R.drawable.image_2), R.drawable.image_2, "The wave crashed and hit the sandcastle head-on. The sandcastle began to melt under the waves force and as the wave receded, half the sandcastle was gone. The next wave hit, not quite as strong, but still managed to cover the remains of the sandcastle and take more of it away. The third wave, a big one, crashed over the sandcastle completely covering and engulfing it. When it receded, there was no trace the sandcastle ever existed and hours of hard work disappeared forever."));
        specialisedContentItem.add(new RecyclerExploreCardItem("Redline", "1 Hour • Singles", getActivity().getDrawable(R.drawable.image_3), R.drawable.image_3, "The red line moved across the page. With each millimeter it advanced forward, something changed in the room. The actual change taking place was difficult to perceive, but the change was real. The red line continued relentlessly across the page and the room would never be the same."));
        specialisedContentItem.add(new RecyclerExploreCardItem("Whiskey on the Bench", "15 Min • Singles", getActivity().getDrawable(R.drawable.image_4), R.drawable.image_4, "The red line moved across the page. With each millimeter it advanced forward, something changed in the room. The actual change taking place was difficult to perceive, but the change was real. The red line continued relentlessly across the page and the room would never be the same."));
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
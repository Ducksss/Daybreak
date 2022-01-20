package com.example.daybreak.ui.explore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.daybreak.InnerExploreActivity;
import com.example.daybreak.MainActivity;
import com.example.daybreak.OnboardActivity;
import com.example.daybreak.R;
import com.example.daybreak.SignupActivity;
import com.google.android.material.card.MaterialCardView;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
    private List<SearchItem> countryList;
    private String mParam1;
    private String mParam2;
    private Bitmap bitmap;
    private RecyclerView singlePracticeRecyclerView;
    private RecyclerView meditationSeriesRecyclerView;
    private RecyclerView forYouSeriesRecyclerView;
    private ArrayList<RecyclerExploreCardItem> singlePracticeItems = new ArrayList<>();
    private ArrayList<RecyclerExploreCardItem> meditationSeriesItems = new ArrayList<>();
    private ArrayList<RecyclerExploreLongCardItem> forYouSeriesItems = new ArrayList<>();
    private List<SearchItem> exampleList;
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
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        fillExampleList();
        AutoCompleteTextView editText = view.findViewById(R.id.actv);
        AutoCompleteSearchAdapter adapter = new AutoCompleteSearchAdapter(getActivity(), exampleList);
        editText.setAdapter(adapter);
        bindRecyclerViews(view);

        // Find View of the MaterialCard
        MaterialCardView materialCardView = view.findViewById(R.id.card_view);


        materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InnerExploreActivity.class);
                intent.putExtra("Title", "Mind Before Matter");
                intent.putExtra("Subtitle", "SecondKeyValue");
                intent.putExtra("background", R.drawable.background_image_1);
                intent.putExtra("Description", "mind before matter.\n" +
                        "\n" +
                        "You can't force my decisions.\n" +
                        "\n" +
                        "If I don't want to talk about it, then that's just the way it is.\"\n" +
                        "\n" +
                        "How many people do you know who have this kind of mindset?\n" +
                        "\n" +
                        "This is a poor quality to have.\n" +
                        "\n" +
                        "She is being swayed by her emotions, instead of trusting her judgment, or even considering her son's words.\n" +
                        "\n" +
                        "Instead of seeing that he is holding back for his own reasons, she sees that he doesn't want to talk about it, and as a result, she is pulling away.\n" +
                        "\n" +
                        "Yes, when people like this get offended by your comments, it is usually because they believe that their loved one is a coward, or maybe just need more understanding.\n" +
                        "\n" +
                        "They don't know how to handle your comment.");
                startActivity(intent);
            }
        });

        return view;
    }
    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new SearchItem(R.drawable.calm_background_4, "Steps", "Slow down your pace, feel the wonderful coordination of your body, and meet your own true self."));
        exampleList.add(new SearchItem(R.drawable.calm_background_1, "Street", "I know this one the best. I think of the streets in Japan, which are quiet and full of lovely shops. I want this alarm!"));
        exampleList.add(new SearchItem(R.drawable.calm_background_3,  "Light", "As I inhale the impalpable breeze that set in upon me, the ocean mysterious rolls toward me closer and closer."));
        exampleList.add(new SearchItem(R.drawable.calm_background_2,  "Library", "Close the book, still feeling what it was like to dwell in that light."));
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
        singlePracticeItems.add(new RecyclerExploreCardItem("Cloud", "10 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_4), R.drawable.calm_background_4, "The vast blue emptiness of the sky stared back at me as our jet plane soared through the air.\n" +
                "\n" +
                "My nerves began to run wild, but I knew the events we'd be experiencing would be worth it."));
        singlePracticeItems.add(new RecyclerExploreCardItem("Sedentary", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_2), R.drawable.calm_background_2, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        singlePracticeItems.add(new RecyclerExploreCardItem("Morning Planning", "5-10 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_3), R.drawable.calm_background_3, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        singlePracticeItems.add(new RecyclerExploreCardItem("Insomnia", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.background_image_1), R.drawable.background_image_1, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        singlePracticeItems.add(new RecyclerExploreCardItem("Work Burnout", "5-10 Min • Singles", getActivity().getDrawable(R.drawable.background_image_2), R.drawable.background_image_2, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        singlePracticeItems.add(new RecyclerExploreCardItem("Falling Asleep", "5 Min • Singles", getActivity().getDrawable(R.drawable.background_image_3), R.drawable.background_image_3, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));

        // MEDITATION SERIES
        meditationSeriesItems.add(new RecyclerExploreCardItem("Dreamland", "10 Min • Singles", getActivity().getDrawable(R.drawable.background_image_3), R.drawable.background_image_3, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Fantasyland", "5-10 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_2), R.drawable.background_image_3, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Morning Exercise", "5 Min • Singles", getActivity().getDrawable(R.drawable.background_image_1), R.drawable.background_image_1, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Healthy Eating", "10 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_3), R.drawable.calm_background_3, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Happiness", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_2), R.drawable.calm_background_2, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Coping With Stress", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_4), R.drawable.calm_background_4, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));

        // SELECTED MIX

        // SELECTED STORY


        // FOR YOU
        forYouSeriesItems.add(new RecyclerExploreLongCardItem("Steps", "Slow down your pace, feel the wonderful coordination of your body, and meet your own true self.", getActivity().getDrawable(R.drawable.calm_background_4), new String[]{"Meditation", "Melody"}, R.drawable.calm_background_4, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        forYouSeriesItems.add(new RecyclerExploreLongCardItem("Street", "I know this one the best. I think of the streets in Japan, which are quiet and full of lovely shops. I want this alarm!", getActivity().getDrawable(R.drawable.calm_background_1), new String[]{"Nature", "Sleep"}, R.drawable.calm_background_4, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        forYouSeriesItems.add(new RecyclerExploreLongCardItem("Lighthouse", "As I inhale the impalpable breeze that set in upon me, the ocean mysterious rolls toward me closer and closer.", getActivity().getDrawable(R.drawable.calm_background_3), new String[]{"Nature", "Meditation"}, R.drawable.calm_background_4, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        forYouSeriesItems.add(new RecyclerExploreLongCardItem("Library", "Close the book, still feeling what it was like to dwell in that light.", getActivity().getDrawable(R.drawable.calm_background_2), new String[]{"Performance", "Stress"}, R.drawable.calm_background_4, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
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
            public void onItemClicked(RecyclerExploreCardItem item) {
                Intent intent = new Intent(getActivity(), InnerExploreActivity.class);
                intent.putExtra("Title", item.getTitle());
                intent.putExtra("Subtitle", item.getSubtitle());
                intent.putExtra("Background", item.getDrawableID());
                intent.putExtra("Description", item.getInnerContent());
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
            public void onItemClicked(RecyclerExploreCardItem item) {
                Intent intent = new Intent(getActivity(), InnerExploreActivity.class);
                intent.putExtra("Title", item.getTitle());
                intent.putExtra("Subtitle", item.getSubtitle());
                intent.putExtra("Background", item.getDrawableID());
                intent.putExtra("Description", item.getInnerContent());
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
            public void onItemClicked(RecyclerExploreLongCardItem item) {
                Intent intent = new Intent(getActivity(), InnerExploreActivity.class);
                intent.putExtra("Title", item.getTitle());
                intent.putExtra("Subtitle", item.getSubtitle());
                intent.putExtra("Chip", item.getCardChips());
                intent.putExtra("Background", item.getDrawableID());
                intent.putExtra("Description", item.getInnerContent());
                startActivity(intent);
            }
        });
        forYouSeriesRecyclerView.setAdapter(myRecyclerViewAdapter3);
    }
}
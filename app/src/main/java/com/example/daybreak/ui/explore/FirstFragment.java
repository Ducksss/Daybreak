package com.example.daybreak.ui.explore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daybreak.InnerExploreActivity;
import com.example.daybreak.R;
import com.google.android.material.card.MaterialCardView;

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
    private String mParam1;
    private String mParam2;
    private RecyclerView singlePracticeRecyclerView;
    private RecyclerView meditationSeriesRecyclerView;
    private RecyclerView forYouSeriesRecyclerView;
    private ArrayList<RecyclerExploreCardItem> selectedMixItems = new ArrayList<>();
    private ArrayList<RecyclerExploreCardItem> singlePracticeItems = new ArrayList<>();
    private ArrayList<RecyclerExploreCardItem> meditationSeriesItems = new ArrayList<>();
    private ArrayList<RecyclerExploreCardItem> selectedStoriesItems = new ArrayList<>();

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
                intent.putExtra("Subtitle", "10 Min • Singles");
                intent.putExtra("Background", R.drawable.calm_background_3);
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
        exampleList.add(new SearchItem(R.drawable.calm_background_4, "Steps", "10 Min • Singles", "Slow down your pace, feel the wonderful coordination of your body, and meet your own true self."));
        exampleList.add(new SearchItem(R.drawable.calm_background_1, "Street", "10 Min • Singles", "I know this one the best. I think of the streets in Japan, which are quiet and full of lovely shops. I want this alarm!"));
        exampleList.add(new SearchItem(R.drawable.calm_background_3, "Light", "5 Min • Singles", "As I inhale the impalpable breeze that set in upon me, the ocean mysterious rolls toward me closer and closer."));
        exampleList.add(new SearchItem(R.drawable.calm_background_2, "Library", "5 Min • Singles", "Close the book, still feeling what it was like to dwell in that light."));
        exampleList.add(new SearchItem(R.drawable.calm_background_4, "Cloud", "10 Min • Singles", "The vast blue emptiness of the sky stared back at me as our jet plane soared through the air.\n" + "\n" + "My nerves began to run wild, but I knew the events we'd be experiencing would be worth it."));
        exampleList.add(new SearchItem(R.drawable.calm_background_2, "Sedentary", "5-15 Min • Singles", "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        exampleList.add(new SearchItem(R.drawable.background_image_1, "Insomnia", "5-15 Min • Singles", "Everyone experiences Insomnia, it is how you deal with it that distinguishes you from the rest."));
        exampleList.add(new SearchItem(R.drawable.background_image_2, "Work Burnout", "5-10 Min • Singles", "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        exampleList.add(new SearchItem(R.drawable.image_1, "Evening Blues", "30 Min • Singles", "The lone lamp post of the one-street town flickered, not quite dead but definitely on its way out. Suitcase by her side, she paid no heed to the light, the street or the town. A car was coming down the street and with her arm outstretched and thumb in the air, she had a plan."));
        exampleList.add(new SearchItem(R.drawable.image_2, "Gloom and Doom", "3 Hours • Singles", "The wave crashed and hit the sandcastle head-on. The sandcastle began to melt under the waves force and as the wave receded, half the sandcastle was gone. The next wave hit, not quite as strong, but still managed to cover the remains of the sandcastle and take more of it away. The third wave, a big one, crashed over the sandcastle completely covering and engulfing it. When it receded, there was no trace the sandcastle ever existed and hours of hard work disappeared forever."));
        exampleList.add(new SearchItem(R.drawable.image_3, "Redline", "1 Hour • Singles", "The red line moved across the page. With each millimeter it advanced forward, something changed in the room. The actual change taking place was difficult to perceive, but the change was real. The red line continued relentlessly across the page and the room would never be the same."));
        exampleList.add(new SearchItem(R.drawable.image_4, "Whiskey on the Bench", "15 Min • Singles", "The red line moved across the page. With each millimeter it advanced forward, something changed in the room. The actual change taking place was difficult to perceive, but the change was real. The red line continued relentlessly across the page and the room would never be the same."));
        exampleList.add(new SearchItem(R.drawable.background_image_3, "Fantasyland", "5-10 Min • Singles", "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        exampleList.add(new SearchItem(R.drawable.calm_background_3, "Healthy Eating", "10 Min • Singles", "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        exampleList.add(new SearchItem(R.drawable.image_5, "Zen Mediation", "30 Min • Singles", "Use our Zen music for meditation as calming music to bring soothing relaxation and healing to your body-mind. Through our meditation music, we hope you can experience true fulfilment and stress relief. This peaceful music, enhanced with binaural beats, is ideal relaxing music for autogenic practice and can be used as stress relief music or peaceful music when practicing meditation for anxiety during quarantine.  Our relaxing music is gentle piano music used for healing meditation, as healing music or as ambient music, perfect for meditation or relaxation."));
        exampleList.add(new SearchItem(R.drawable.image_6, "Relaxing with Japanese Instruments", "3 Hours • Singles", "Relaxing With Japanese Bamboo Flute , Guzheng, Erhu | Musical Instrument Collection"));
        exampleList.add(new SearchItem(R.drawable.image_7, "Lofi hip hop to study to", "1 Hour • Singles", "This playlist contains the smoothest lofi hip hop beats, perfect to help you chill or fall asleep"));
        exampleList.add(new SearchItem(R.drawable.image_8, "Rain Relaxation", "15 Min • Singles", "Gentle Rain at night for Sleep, Insomnia, Study, Relax. Gentle Night Rain Sounds (No Music, No Thunder) Gentle Rain for 3 Hours with a Dark Screen - the Rain is just visible. Highly recommended for Sleeping and insomnia. Also ideal for Relaxing or Studying. Light Rain works well for Reducing Stress or Anxiety. Gentle Rain to Block Noise, Help Baby Sleep, Relax Pets, Improve Focus, Meditate, Sleep Instantly, Sleep Immediately, Sleep Fast, Sleep without Waking. Share with family & friends to help them feel better too. Enjoy the Gentle Rain..."));
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
        // SINGLE SERIES - MOST UPDATED - COUNT - 8
        singlePracticeItems.add(new RecyclerExploreCardItem("Cloud", "10 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_4), R.drawable.calm_background_4, "The vast blue emptiness of the sky stared back at me as our jet plane soared through the air.\n" + "\n" + "My nerves began to run wild, but I knew the events we'd be experiencing would be worth it."));
        singlePracticeItems.add(new RecyclerExploreCardItem("Sedentary", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_2), R.drawable.calm_background_2, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        singlePracticeItems.add(new RecyclerExploreCardItem("Insomnia", "5-15 Min • Singles", getActivity().getDrawable(R.drawable.background_image_1), R.drawable.background_image_1, "Everyone experiences Insomnia, it is how you deal with it that distinguishes you from the rest."));
        singlePracticeItems.add(new RecyclerExploreCardItem("Work Burnout", "5-10 Min • Singles", getActivity().getDrawable(R.drawable.background_image_2), R.drawable.background_image_2, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        singlePracticeItems.add(new RecyclerExploreCardItem("Evening Blues", "30 Min • Singles", getActivity().getDrawable(R.drawable.image_1), R.drawable.image_1, "The lone lamp post of the one-street town flickered, not quite dead but definitely on its way out. Suitcase by her side, she paid no heed to the light, the street or the town. A car was coming down the street and with her arm outstretched and thumb in the air, she had a plan."));
        singlePracticeItems.add(new RecyclerExploreCardItem("Gloom and Doom", "3 Hours • Singles", getActivity().getDrawable(R.drawable.image_2), R.drawable.image_2, "The wave crashed and hit the sandcastle head-on. The sandcastle began to melt under the waves force and as the wave receded, half the sandcastle was gone. The next wave hit, not quite as strong, but still managed to cover the remains of the sandcastle and take more of it away. The third wave, a big one, crashed over the sandcastle completely covering and engulfing it. When it receded, there was no trace the sandcastle ever existed and hours of hard work disappeared forever."));
        singlePracticeItems.add(new RecyclerExploreCardItem("Redline", "1 Hour • Singles", getActivity().getDrawable(R.drawable.image_3), R.drawable.image_3, "The red line moved across the page. With each millimeter it advanced forward, something changed in the room. The actual change taking place was difficult to perceive, but the change was real. The red line continued relentlessly across the page and the room would never be the same."));
        singlePracticeItems.add(new RecyclerExploreCardItem("Whiskey on the Bench", "15 Min • Singles", getActivity().getDrawable(R.drawable.image_4), R.drawable.image_4, "The red line moved across the page. With each millimeter it advanced forward, something changed in the room. The actual change taking place was difficult to perceive, but the change was real. The red line continued relentlessly across the page and the room would never be the same."));

        // MEDITATION SERIES - MOST UPDATED - COUNT - 6
        meditationSeriesItems.add(new RecyclerExploreCardItem("Fantasyland", "5-10 Min • Singles", getActivity().getDrawable(R.drawable.background_image_3), R.drawable.background_image_3, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Healthy Eating", "10 Min • Singles", getActivity().getDrawable(R.drawable.calm_background_3), R.drawable.calm_background_3, "Sedentary relatives who watch TV and eat the worst, indeed eat what is often labeled as \"healthy,\" \"good,\" or \"diet\" foods do the most harm to a family's health. \"Sedentary living and eating promotes the development and accumulation of age-related disease,\" wrote Dr. Douglas Denzer"));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Zen Mediation", "30 Min • Singles", getActivity().getDrawable(R.drawable.image_5), R.drawable.image_5, "Use our Zen music for meditation as calming music to bring soothing relaxation and healing to your body-mind. Through our meditation music, we hope you can experience true fulfilment and stress relief. This peaceful music, enhanced with binaural beats, is ideal relaxing music for autogenic practice and can be used as stress relief music or peaceful music when practicing meditation for anxiety during quarantine.  Our relaxing music is gentle piano music used for healing meditation, as healing music or as ambient music, perfect for meditation or relaxation."));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Relaxing with Japanese Instruments", "3 Hours • Singles", getActivity().getDrawable(R.drawable.image_6), R.drawable.image_6, "Relaxing With Japanese Bamboo Flute , Guzheng, Erhu | Musical Instrument Collection"));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Lofi hip hop to study to", "1 Hour • Singles", getActivity().getDrawable(R.drawable.image_7), R.drawable.image_7, "This playlist contains the smoothest lofi hip hop beats, perfect to help you chill or fall asleep"));
        meditationSeriesItems.add(new RecyclerExploreCardItem("Rain Relaxation", "15 Min • Singles", getActivity().getDrawable(R.drawable.image_8), R.drawable.image_8, "Gentle Rain at night for Sleep, Insomnia, Study, Relax. Gentle Night Rain Sounds (No Music, No Thunder) Gentle Rain for 3 Hours with a Dark Screen - the Rain is just visible. Highly recommended for Sleeping and insomnia. Also ideal for Relaxing or Studying. Light Rain works well for Reducing Stress or Anxiety. Gentle Rain to Block Noise, Help Baby Sleep, Relax Pets, Improve Focus, Meditate, Sleep Instantly, Sleep Immediately, Sleep Fast, Sleep without Waking. Share with family & friends to help them feel better too. Enjoy the Gentle Rain..."));

        // SELECTED MIX - MOST UPDATED - COUNT - 10
        selectedMixItems.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_1), getString(R.string.selected_mix_duration_1), getActivity().getDrawable(R.drawable.image_9), R.drawable.image_9, getString(R.string.selected_mix_description_1)));
        selectedMixItems.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_2), getString(R.string.selected_mix_duration_2), getActivity().getDrawable(R.drawable.image_10), R.drawable.image_10, getString(R.string.selected_mix_description_2)));
        selectedMixItems.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_3), getString(R.string.selected_mix_duration_3), getActivity().getDrawable(R.drawable.image_11), R.drawable.image_11, getString(R.string.selected_mix_description_3)));
        selectedMixItems.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_4), getString(R.string.selected_mix_duration_4), getActivity().getDrawable(R.drawable.image_12), R.drawable.image_12, getString(R.string.selected_mix_description_4)));
        selectedMixItems.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_5), getString(R.string.selected_mix_duration_5), getActivity().getDrawable(R.drawable.image_13), R.drawable.image_13, getString(R.string.selected_mix_description_5)));
        selectedMixItems.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_6), getString(R.string.selected_mix_duration_6), getActivity().getDrawable(R.drawable.image_14), R.drawable.image_14, getString(R.string.selected_mix_description_6)));
        selectedMixItems.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_7), getString(R.string.selected_mix_duration_7), getActivity().getDrawable(R.drawable.image_15), R.drawable.image_15, getString(R.string.selected_mix_description_7)));
        selectedMixItems.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_8), getString(R.string.selected_mix_duration_8), getActivity().getDrawable(R.drawable.image_16), R.drawable.image_16, getString(R.string.selected_mix_description_8)));
        selectedMixItems.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_9), getString(R.string.selected_mix_duration_9), getActivity().getDrawable(R.drawable.image_17), R.drawable.image_17, getString(R.string.selected_mix_description_9)));
        selectedMixItems.add(new RecyclerExploreCardItem(getString(R.string.selected_mix_title_10), getString(R.string.selected_mix_duration_10), getActivity().getDrawable(R.drawable.image_18), R.drawable.image_18, getString(R.string.selected_mix_description_10)));

        // SELECTED STORY - MOST UPDATED - COUNT - 10
        selectedStoriesItems.add(new RecyclerExploreCardItem("Cinderella Part 1", "15 Min • Singles", getActivity().getDrawable(R.drawable.cinderella), R.drawable.cinderella, getString(R.string.cinderella_description)));
        selectedStoriesItems.add(new RecyclerExploreCardItem("Cinderella Part 2", "15 Min • Singles", getActivity().getDrawable(R.drawable.cinderella), R.drawable.cinderella, getString(R.string.cinderella_description)));
        selectedStoriesItems.add(new RecyclerExploreCardItem("Cinderella Part 3", "15 Min • Singles", getActivity().getDrawable(R.drawable.cinderella), R.drawable.cinderella, getString(R.string.cinderella_description)));
        selectedStoriesItems.add(new RecyclerExploreCardItem("Cinderella Part 4", "15 Min • Singles", getActivity().getDrawable(R.drawable.cinderella), R.drawable.cinderella, getString(R.string.cinderella_description)));
        selectedStoriesItems.add(new RecyclerExploreCardItem("Cinderella Part 5", "15 Min • Singles", getActivity().getDrawable(R.drawable.cinderella), R.drawable.cinderella, getString(R.string.cinderella_description)));
        selectedStoriesItems.add(new RecyclerExploreCardItem("Cinderella Part 5", "15 Min • Singles", getActivity().getDrawable(R.drawable.cinderella), R.drawable.cinderella, getString(R.string.cinderella_description)));
        selectedStoriesItems.add(new RecyclerExploreCardItem("Jack & The Beanstalk Part 1", "15 Min • Singles", getActivity().getDrawable(R.drawable.jack_bean), R.drawable.jack_bean, getString(R.string.jack_and_the_beanstalk_description)));
        selectedStoriesItems.add(new RecyclerExploreCardItem("Jack & The Beanstalk Part 2", "15 Min • Singles", getActivity().getDrawable(R.drawable.jack_bean), R.drawable.jack_bean, getString(R.string.jack_and_the_beanstalk_description)));
        selectedStoriesItems.add(new RecyclerExploreCardItem("Jack & The Beanstalk Part 3", "15 Min • Singles", getActivity().getDrawable(R.drawable.jack_bean), R.drawable.jack_bean, getString(R.string.jack_and_the_beanstalk_description)));
        selectedStoriesItems.add(new RecyclerExploreCardItem("Jack & The Beanstalk Part 4", "15 Min • Singles", getActivity().getDrawable(R.drawable.jack_bean), R.drawable.jack_bean, getString(R.string.jack_and_the_beanstalk_description)));
        selectedStoriesItems.add(new RecyclerExploreCardItem("Jack & The Beanstalk Part 5", "15 Min • Singles", getActivity().getDrawable(R.drawable.jack_bean), R.drawable.jack_bean, getString(R.string.jack_and_the_beanstalk_description)));

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
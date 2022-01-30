package com.example.daybreak.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.daybreak.R;
import com.example.daybreak.Timer.TimerActivity;
import com.example.daybreak.User;
import com.example.daybreak.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment implements StartTimer.FragmentAListener, FragmentB.FragmentBListener {

    private FirebaseUser MUser;
    private FragmentHomeBinding view;
    private HomeViewModel homeViewModel;

    private FragmentB fragmentB;
    private StartTimer StartTimer;
    private androidx.viewpager2.widget.ViewPager2 ViewPager2;
    private List<RecyclerVideoViewItem> RecyclerVideoViewItemsList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView user_greeting = view.findViewById(R.id.user_greeting);

        // Dynamically greet the user
        MUser = FirebaseAuth.getInstance().getCurrentUser();
        // Retrieving data from firebase on the user user name / full name
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://daybreak-b9a84-default-rtdb.asia-southeast1.firebasedatabase.app");
        // Getting a reference of the table
        DatabaseReference reference = rootNode.getReference("Users");
        // Retrieving the UID
        String UserID = MUser.getUid();
        // Calling database for the items
        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                String username = user.getUsername();
                user_greeting.setText("Hi, " + username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Uh oh! Failure!", Toast.LENGTH_LONG).show();
            }
        });

        // Dynamically display timing of the day
        Calendar cal = Calendar.getInstance();
        int timeOfDay = cal.get(Calendar.HOUR_OF_DAY);
        TextView time_greeting = view.findViewById(R.id.time_greeting);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            time_greeting.setText("Good Morning");
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            time_greeting.setText("Good Afternoon");
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            time_greeting.setText("Good Evening");
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            time_greeting.setText("Good Night");
        }

        // Setting the background of the viewpager
        bindVideoData();
        ViewPager2 = view.findViewById(R.id.animated_video_background);
        ViewPager2.setAdapter(new RecyclerVideoViewItemAdapter(RecyclerVideoViewItemsList));

        view.findViewById(R.id.start_focus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), TimerActivity.class);
                startActivity(i);
            }
        });
        final Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        ImageView btn_done = view.findViewById(R.id.start_focus_button);
        btn_done.setAnimation(animShake);
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
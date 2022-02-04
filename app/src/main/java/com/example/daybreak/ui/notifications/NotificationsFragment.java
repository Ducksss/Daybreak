package com.example.daybreak.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.daybreak.R;
import com.example.daybreak.User;
import com.example.daybreak.databinding.FragmentNotificationsBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    // Database
    private FirebaseUser MUser;
    private SharedPreferences timerPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    // Premium
    private User user;
    private boolean isPremium;

    // Graphical
    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    List<DataEntry> pieChartData = new ArrayList<>();
    List<DataEntry> columnChartData = new ArrayList<>();

    // Adverts
    AdView adView1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        // -----DATABASE + ADS + PREMIUM
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
                user = snapshot.getValue(User.class);
                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
                CollapsingToolbarLayout toolBarLayout = view.findViewById(R.id.toolbar_layout);

                // User tracker
                TextView user_greeting = view.findViewById(R.id.user_greeting);
                if (user.getUsername().length() <= 13) {
                    user_greeting.setText(user.getUsername());
                } else {
                    user_greeting.setText(user.getUsername().substring(0, 13) + "...");
                }

                isPremium = (boolean) user.getPremium();

                // Date tracker
                LocalDate from = Instant.ofEpochMilli(MUser.getMetadata().getCreationTimestamp()).atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate to = LocalDate.now();
                long result = ChronoUnit.DAYS.between(from, to);

                TextView day_tracker_greeting = view.findViewById(R.id.day_tracker_greeting);
                if (result == 0) {
                    day_tracker_greeting.setText("Joined Daybreak for " + String.valueOf(result + 1) + " day");
                } else {
                    day_tracker_greeting.setText("Joined Daybreak for " + String.valueOf(result + 1) + " days");
                }

                // ----- PREMIUM
                try {
                    MaterialCardView support_premium_button = view.findViewById(R.id.support_premium_button);
                    // if is premium, don't show the "buy premium stuff"
                    if (isPremium == true) {
                        support_premium_button.setVisibility(View.GONE);
                    } else {
                        support_premium_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                                BottomSheetDialog bottomSheetDialog2 = new BottomSheetDialog(getContext());

                                View parentView = getLayoutInflater().inflate(R.layout.activity_bottom_modal, null);
                                View parentView2 = getLayoutInflater().inflate(R.layout.activity_bottom_modal_paid, null);

                                bottomSheetDialog.setContentView(parentView);
                                bottomSheetDialog2.setContentView(parentView2);

                                // Showing the first modal
                                bottomSheetDialog.show();

                                parentView.findViewById(R.id.confirm_payment_button).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        bottomSheetDialog.dismiss();
                                        bottomSheetDialog2.show();

                                        parentView2.findViewById(R.id.payment_done_button).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                reference.child(UserID).child("premium").setValue(true);
                                                bottomSheetDialog2.dismiss();
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.d("Failure Point - Alpha", e.toString());
                }

                // ----- ADS
                try {
                    if (!isPremium) {
                        AdView adView = new AdView(view.getContext());
                        adView.setAdSize(AdSize.BANNER);
                        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

                        MobileAds.initialize(view.getContext(), new OnInitializationCompleteListener() {
                            @Override
                            public void onInitializationComplete(InitializationStatus initializationStatus) {
                            }
                        });

                        adView1 = view.findViewById(R.id.adView);
                        AdRequest adRequest = new AdRequest.Builder().build();
                        adView1.loadAd(adRequest);

                        adView1.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                super.onAdClosed();
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                super.onAdFailedToLoad(loadAdError);
                            }

                            @Override
                            public void onAdOpened() {
                                super.onAdOpened();
                            }

                            @Override
                            public void onAdLoaded() {
                                super.onAdLoaded();
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.d("Failure point - Beta", e.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Uh oh! Failure!", Toast.LENGTH_LONG).show();
            }
        });

        // ----- GRAPHICAL
        // Pie chart creation
        try {
            bindGraphData();
            AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
            APIlib.getInstance().setActiveAnyChartView(anyChartView);
            anyChartView.setProgressBar(view.findViewById(R.id.progress_bar));

            Pie pie = AnyChart.pie();
            pie.palette(new String[]{"#8CE6BE", "#8DBEB5"});
            pie.data(pieChartData);

            anyChartView.setChart(pie);

            // Cartesian chart creation
            AnyChartView anyChartView1 = view.findViewById(R.id.any_chart_view1);
            APIlib.getInstance().setActiveAnyChartView(anyChartView1);

            Cartesian cartesian = AnyChart.column();
            Column column = cartesian.column(columnChartData);
            column.tooltip()
                    .titleFormat("{%X}")
                    .position(Position.CENTER_BOTTOM)
                    .anchor(Anchor.CENTER_BOTTOM)
                    .offsetX(0d)
                    .offsetY(0d)
                    .format("{%Value}{groupsSeparator: } Hours");

            cartesian.animation(true);
            cartesian.yScale().minimum(0d);
            cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: } Hrs");
            cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
            cartesian.interactivity().hoverMode(HoverMode.BY_X);
            cartesian.palette(new String[]{"#97C4BB", "#E26D5C"});
            anyChartView1.setChart(cartesian);

            timerPreferences = this.getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
            loginPrefsEditor = timerPreferences.edit();
            TextView tv = view.findViewById(R.id.focus_description);
            tv.setText(Integer.toString(timerPreferences.getInt("focusamount", 1)) + " Times");
            // Check if threshold is met
            if (true) {
                loginPrefsEditor.putInt("focusamount", timerPreferences.getInt("focusamount", 0) + 0);
            } else {
                loginPrefsEditor.clear();
            }
            loginPrefsEditor.commit();
        } catch (Exception e) {

        }

        return view;
    }

    public void bindGraphData() {
        pieChartData.add(new ValueDataEntry("Focus", 10));
        pieChartData.add(new ValueDataEntry("Not focused", 12));

        columnChartData.add(new ValueDataEntry("Sun", 4));
        columnChartData.add(new ValueDataEntry("Mon", 1));
        columnChartData.add(new ValueDataEntry("Tue", 2));
        columnChartData.add(new ValueDataEntry("Wed", 6));
        columnChartData.add(new ValueDataEntry("Thu", 4));
        columnChartData.add(new ValueDataEntry("Fri", 6));
        columnChartData.add(new ValueDataEntry("Sat", 7));
        columnChartData.add(new ValueDataEntry("Sun", 2));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
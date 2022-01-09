package com.example.daybreak.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
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
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    List<DataEntry> pieChartData = new ArrayList<>();
    List<DataEntry> columnChartData = new ArrayList<>();
    private FirebaseUser MUser;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        bindGraphData();
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
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
                ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
                CollapsingToolbarLayout toolBarLayout = view.findViewById(R.id.toolbar_layout);
                toolBarLayout.setTitle(user.getUsername());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Uh oh! Failure!", Toast.LENGTH_LONG).show();
            }
        });


        // Pie chart creation
        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        anyChartView.setProgressBar(view.findViewById(R.id.progress_bar));

        Pie pie = AnyChart.pie();
        pie.palette(new String[]{"#ffd505", "#ff7e05"});
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
        cartesian.palette(new String[]{"#ff7e05", "#ff7e05"});
        anyChartView1.setChart(cartesian);

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

    public void createPieChart() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
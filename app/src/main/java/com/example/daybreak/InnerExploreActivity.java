package com.example.daybreak;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.daybreak.Timer.EndTimer;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daybreak.databinding.ActivityInnerExploreBinding;

import java.util.Optional;

public class InnerExploreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityInnerExploreBinding binding;
    private MediaPlayer player = null;
    private TextView titleTextView;
    private TextView subtitleTextView;
    private TextView descriptionTextView;
    private Chip Chip1;
    private Chip Chip2;
    private ImageView imageView;
    private String Title;
    private String Subtitle;
    private String Description;
    private String[] ChipArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInnerExploreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;

        Spinner spinner = findViewById(R.id.playback_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.playback_speed_labels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // Getting views
        imageView = findViewById(R.id.imageView1);
        titleTextView = findViewById(R.id.inner_explore_title);
        subtitleTextView = findViewById(R.id.inner_explore_subtitle);
        descriptionTextView = findViewById(R.id.inner_explore_description);
        Chip1 = findViewById(R.id.chip6);
        Chip2 = findViewById(R.id.chip7);

        if (bundle != null) {
            Title = Optional.ofNullable(intent.getStringExtra("Title")).orElse("Unable to retrieve Title");
            Subtitle = Optional.ofNullable(intent.getStringExtra("Subtitle")).orElse("Unable to retrieve Subtitle");
            Description = Optional.ofNullable(intent.getStringExtra("Description")).orElse("Unable to retrieve Description");
            int diff = getIntent().getIntExtra("Background", 0);

            try {
                ChipArray = intent.getStringArrayExtra("Chip");
            } catch (Exception e) {

            }

            if (ChipArray != null) {
                Chip1.setText(ChipArray[0]);
                Chip2.setText(ChipArray[1]);
            }

            toolBarLayout.setTitle(Title);
            titleTextView.setText(Title);
            subtitleTextView.setText(Subtitle);
            descriptionTextView.setText(Description);
            imageView.getLayoutParams().height = 1500;

            Drawable image = getDrawable(diff);
            imageView.setImageDrawable(image);

        }

        if (player == null) {
            player = MediaPlayer.create(this, R.raw.bgm);

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player.start();
        //end Button
        ImageView endTimer = findViewById(R.id.inner_back_button);
        endTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();
                finish();
            }
        });
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Converts labels to readable value (position get at playback speed label)
        String text = getResources().getStringArray(R.array.playback_speed)[position];
        // Changes text size, can't do it non programatically/hard to do
        ((TextView) parent.getChildAt(0)).setTextSize(13);
        if (text.equals("1x")) {
            player.setPlaybackParams(player.getPlaybackParams().setSpeed(1f));
        } else if (text.equals("2x")) {
            player.setPlaybackParams(player.getPlaybackParams().setSpeed(2f));
        } else if (text.equals("4x")) {
            player.setPlaybackParams(player.getPlaybackParams().setSpeed(4f));
        } else if (text.equals("0.5x")) {
            player.setPlaybackParams(player.getPlaybackParams().setSpeed(0.5f));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
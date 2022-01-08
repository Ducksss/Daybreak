package com.example.daybreak;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.daybreak.databinding.ActivityInnerExploreBinding;

public class InnerExploreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ActivityInnerExploreBinding binding;
    MediaPlayer player = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityInnerExploreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        Spinner spinner = findViewById(R.id.playback_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.playback_speed, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        FloatingActionButton fab = binding.fab;
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if(text.equals("1x")){
            player.setPlaybackParams(player.getPlaybackParams().setSpeed(1f));
        }else if (text.equals("2x")){
            player.setPlaybackParams(player.getPlaybackParams().setSpeed(2f));
        }else if (text.equals("4x")){
            player.setPlaybackParams(player.getPlaybackParams().setSpeed(4f));
        }else if (text.equals("0.5x")){
            player.setPlaybackParams(player.getPlaybackParams().setSpeed(0.5f));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
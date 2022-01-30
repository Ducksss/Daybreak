package com.example.daybreak.ui.home;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daybreak.R;

import java.util.List;

public class RecyclerVideoViewItemAdapter extends RecyclerView.Adapter<RecyclerVideoViewItemAdapter.VideoViewHolder> {

    List<RecyclerVideoViewItem> RecyclerVideoViewItemList;
    MediaController mediaController;

    public RecyclerVideoViewItemAdapter(List<RecyclerVideoViewItem> RecyclerVideoViewItemList) {
        this.RecyclerVideoViewItemList = RecyclerVideoViewItemList;
    }

    @NonNull
    @Override
    public RecyclerVideoViewItemAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext()).inflate(R.layout.videoview_recycleritem, parent, false));
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVideoViewItemAdapter.VideoViewHolder holder, int position) {
        holder.setVideoData(RecyclerVideoViewItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return RecyclerVideoViewItemList.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView video_title_text;
        TextView video_description_text;
        VideoView animated_video_background_item;
        ProgressBar animated_video_background_progress_bar;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            // Gets the R.ID of the layout to know where to place after setting the following variables
            video_title_text = itemView.findViewById(R.id.video_title_text);
            video_description_text = itemView.findViewById(R.id.video_description_text);
            animated_video_background_item = itemView.findViewById(R.id.animated_video_background_item);
        }

        void setVideoData(RecyclerVideoViewItem RecyclerVideoViewItem) {
            // Finalising the settings
            video_title_text.setText(RecyclerVideoViewItem.getVideo_title_text());
            video_description_text.setText(RecyclerVideoViewItem.getVideo_description_text());

            animated_video_background_item.setVideoPath(RecyclerVideoViewItem.getAnimated_video_background_item_URL());
            animated_video_background_item.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();

                    // Get the video ratio and screen ratio of the video item
                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screenRatio = animated_video_background_item.getWidth() / (float) animated_video_background_item.getHeight();

                    float scale = videoRatio / screenRatio;
                    if (scale >= 1f) {
                        animated_video_background_item.setScaleX(scale);
                    } else {
                        animated_video_background_item.setScaleY(1f / scale);
                    }
                }
            });
            animated_video_background_item.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });

            // TRIAL 2
//            // Set the item to the URL File Path
//            System.out.println("TEST AREA 1");
//
//            MediaPlayer mediaPlayer = new MediaPlayer();
//            String d = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
//            try {
//                mediaPlayer.setDataSource(d);
//                mediaPlayer.prepare();
//                mediaPlayer.start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            TRIAL 1
//            Uri video = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "victamjpeg.sdp"));
//            String d = "http://res.cloudinary.com/sp-dit-chai-pin-zheng/video/upload/v1639862319/islzr2ldpp0qky0ihtjb.mp4";
//            animated_video_background_item.setVideoPath(d);
//            animated_video_background_item.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    animated_video_background_progress_bar.setVisibility(View.GONE);
//                    mp.start();
//
//                    // Get the video ratio and screen ratio of the video item
//                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
//                    float screenRatio = animated_video_background_item.getWidth() / (float) animated_video_background_item.getHeight();
//
//                    float scale = videoRatio / screenRatio;
//                    if (scale >= 1f) {
//                        animated_video_background_item.setScaleX(scale);
//                    } else {
//                        animated_video_background_item.setScaleY(1f / scale);
//                    }
//                }
//            });
//            animated_video_background_item.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    mp.start();
//                }
//            });
        }
    }
}

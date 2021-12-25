package com.example.daybreak.ui.home;

public class RecyclerVideoViewItem {
    private String video_title_text;
    private String video_description_text;
    private String animated_video_background_item_URL;

    public RecyclerVideoViewItem(String video_title_text, String video_description_text, String animated_video_background_item_URL) {
        this.video_title_text = video_title_text;
        this.video_description_text = video_description_text;
        this.animated_video_background_item_URL = animated_video_background_item_URL;
    }

    public String getVideo_title_text() {
        return video_title_text;
    }

    public String getVideo_description_text() {
        return video_description_text;
    }

    public String getAnimated_video_background_item_URL() {
        return animated_video_background_item_URL;
    }
}
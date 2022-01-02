package com.example.daybreak.ui.explore;

import android.graphics.drawable.Drawable;

public class RecyclerExploreCardItem {
    private String title;
    private String subtitle;
    private Drawable image;

    public RecyclerExploreCardItem(String title, String subtitle, Drawable image) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}

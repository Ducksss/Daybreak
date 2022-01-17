package com.example.daybreak.ui.explore;

import android.graphics.drawable.Drawable;

public class RecyclerExploreCardItem {
    private String title;
    private String subtitle;
    private Drawable image;
    private int drawableID;
    private String innerContent;

    public RecyclerExploreCardItem(String title, String subtitle, Drawable image, int drawableID, String innerContent) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.drawableID = drawableID;
        this.innerContent = innerContent;
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

    public int getDrawableID() {
        return drawableID;
    }

    public void setDrawableID(int drawableID) {
        this.drawableID = drawableID;
    }

    public String getInnerContent() {
        return innerContent;
    }

    public void setInnerContent(String innerContent) {
        this.innerContent = innerContent;
    }
}

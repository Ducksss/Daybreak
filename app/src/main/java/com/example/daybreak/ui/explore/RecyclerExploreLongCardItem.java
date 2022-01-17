package com.example.daybreak.ui.explore;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class RecyclerExploreLongCardItem {

    private String title;
    private String subtitle;
    private Drawable image;
    private String[] cardChips;
    private int drawableID;
    private String innerContent;

    public RecyclerExploreLongCardItem(String title, String subtitle, Drawable image, String[] cardChips, int drawableID, String innerContent) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.cardChips = cardChips;
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

    public String[] getCardChips() {
        return cardChips;
    }

    public void setCardChips(String[] cardChips) {
        this.cardChips = cardChips;
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

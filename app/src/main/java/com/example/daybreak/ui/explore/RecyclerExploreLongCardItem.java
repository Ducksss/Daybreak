package com.example.daybreak.ui.explore;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class RecyclerExploreLongCardItem {

    private String title;
    private String subtitle;
    private Drawable image;
    private String[] cardChips;

    public RecyclerExploreLongCardItem(String title, String subtitle, Drawable image, String[] cardChips) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.cardChips = cardChips;
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
}

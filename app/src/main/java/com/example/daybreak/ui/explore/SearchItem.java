package com.example.daybreak.ui.explore;

public class SearchItem {
    private int imageResource;
    private String title;
    private String description;

    public SearchItem(int imageResource, String title, String description) {
        this.imageResource = imageResource;
        this.title = title;
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText1() {
        return title;
    }

    public String getText2() {
        return description;
    }
}
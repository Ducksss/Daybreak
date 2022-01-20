package com.example.daybreak.ui.explore;

public class SearchItem {
    private int imageResource;
    private String title;
    private String subtitle;
    private String description;

    public SearchItem(int imageResource, String title, String subtitle, String description) {
        this.imageResource = imageResource;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public String getDescription() {
        return description;
    }

}
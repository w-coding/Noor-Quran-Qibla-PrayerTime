package com.dya.noor.module;

public class ActivityItem {

    // The name of the activity.
    private String name;

    // The description of the activity.
    private String description;

    // The image resource for the activity.
    private int imageResource;

    public ActivityItem(String name, String description, int imageResource) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}

package com.uet.quantity.uethackathon2015_team7.entity;

/**
 * Created by James Crabby on 11/21/2015.
 */
public class NavigationItem {
    private int imageID;
    private String name;

    public NavigationItem(int imageID, String name) {
        this.imageID = imageID;
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

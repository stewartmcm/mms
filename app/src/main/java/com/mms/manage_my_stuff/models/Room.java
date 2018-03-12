package com.mms.manage_my_stuff.models;

import java.util.ArrayList;

public class Room {

    private int id;
    private String title;
    private ArrayList<Box> boxes;
    private int boxCount;
    private boolean isPackedUp;

    private Room() { }

    public Room(String title, ArrayList<Box> boxes, int boxCount, boolean isPackedUp) {
        this.title = title;
        this.boxes = boxes;
        this.boxCount = boxCount;
        this.isPackedUp = isPackedUp;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public int getBoxCount() {
        return boxCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBoxCount(int boxCount) {
        this.boxCount = boxCount;
    }

    public boolean isPackedUp() {
        return isPackedUp;
    }

    public void setPackedUp(boolean packedUp) {
        isPackedUp = packedUp;
    }

}

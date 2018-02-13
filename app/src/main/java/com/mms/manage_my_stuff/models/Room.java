package com.mms.manage_my_stuff.models;

public class Room {

    String title;
    Box[] boxes;
    int boxCount;
    boolean isPackedUp;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBoxCount() {
        return boxCount;
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

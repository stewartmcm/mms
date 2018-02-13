package com.mms.manage_my_stuff.models;

public class Box {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PackedItem[] getPackedItems() {
        return packedItems;
    }

    public void setPackedItems(PackedItem[] packedItems) {
        this.packedItems = packedItems;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isPackedUp() {
        return isPackedUp;
    }

    public void setPackedUp(boolean packedUp) {
        isPackedUp = packedUp;
    }

    public boolean isPrioritized() {
        return isPrioritized;
    }

    public void setPrioritized(boolean prioritized) {
        isPrioritized = prioritized;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    int id;
    PackedItem[] packedItems;
    int size;
    boolean isPackedUp;
    boolean isPrioritized;
    boolean isDelivered;


}

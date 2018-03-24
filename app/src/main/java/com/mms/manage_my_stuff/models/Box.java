package com.mms.manage_my_stuff.models;

public class Box {

    int id;
    String title;
    PackedItem[] packedItems;
    int size;
    boolean isPrioritized;
    boolean isDelivered;

    public Box(int id, String title, PackedItem[] packedItems, int size, boolean isPrioritized, boolean isDelivered) {
        this.id = id;
        this.title = title;
        this.packedItems = packedItems;
        this.size = size;
        this.isPrioritized = isPrioritized;
        this.isDelivered = isDelivered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}

package com.mms.manage_my_stuff.event;

public class ToastEvent extends Event {

    public final String message;


    public ToastEvent(String message) {
        this.message = message;
    }
}

package com.mms.manage_my_stuff.events;

public class ToastEvent extends BaseUnboundViewEvent {
    private int toastText;
    private int toastLength;

    private ToastEvent(Object emitter) {
        this.emitter = emitter;
    }

    public ToastEvent toastText(int toastText) {
        this.toastText = toastText;
        return this;
    }

    public ToastEvent toastLength(int toastLength) {
        this.toastLength = toastLength;
        return this;
    }

    public static ToastEvent build(Object emitter) {
        return new ToastEvent(emitter);
    }

    public int getToastText() {
        return toastText;
    }

    public int getToastLength() {
        return toastLength;
    }
}

package com.mms.manage_my_stuff.events;

import android.support.annotation.StringRes;

public class SnackbarEvent extends BaseUnboundViewEvent {

    private @StringRes int textId;
    private int length;

    private SnackbarEvent(Object emitter) {
        this.emitter = emitter;
    }

    public SnackbarEvent textId(@StringRes int textId) {
        this.textId = textId;
        return this;
    }

    public SnackbarEvent length(int length) {
        this.length = length;
        return this;
    }

    public static SnackbarEvent build(Object emitter) {
        return new SnackbarEvent(emitter);
    }

    public int getTextId() {
        return textId;
    }

    public int getLength() {
        return length;
    }
}

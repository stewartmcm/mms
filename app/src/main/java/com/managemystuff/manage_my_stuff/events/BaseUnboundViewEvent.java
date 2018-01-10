package com.managemystuff.manage_my_stuff.events;

public abstract class BaseUnboundViewEvent {
    protected Object emitter;

    public Object getEmitter() {
        return emitter;
    }
}

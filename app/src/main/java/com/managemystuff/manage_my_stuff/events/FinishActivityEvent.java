package com.managemystuff.manage_my_stuff.events;

public class FinishActivityEvent extends BaseUnboundViewEvent {

    private FinishActivityEvent(Object emitter) {
        this.emitter = emitter;
    }

    public static FinishActivityEvent build(Object emitter) {
        return new FinishActivityEvent(emitter);
    }

    public FinishActivityEvent finishActivityEvent() {
        return this;
    }
}

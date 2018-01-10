package com.mms.manage_my_stuff.events;

public class StartActivityEvent extends BaseUnboundViewEvent {

    private Class startActivityClazz;
    private boolean launchExternalApplication;
    private String intentAction;
    private String intentParameter;
    private int intentFlags = 0;

    private StartActivityEvent(Object emitter) {
        this.emitter = emitter;
    }

    public StartActivityEvent activityName(Class startActivityClazz) {
        this.startActivityClazz = startActivityClazz;
        return this;
    }

    public static StartActivityEvent build(Object emitter) {
        return new StartActivityEvent(emitter);
    }

    public StartActivityEvent launchExternalApplication(boolean value) {
        this.launchExternalApplication = value;
        return this;
    }

    public boolean isLaunchingExternalApplication() {
        return this.launchExternalApplication;
    }

    public StartActivityEvent intentAction(String value) {
        this.intentAction = value;
        return this;
    }

    public String getIntentAction() {
        return this.intentAction;
    }

    public StartActivityEvent intentParameter(String value) {
        this.intentParameter = value;
        return this;
    }

    public String getIntentParameter() {
        return this.intentParameter;
    }

    public StartActivityEvent intentFlags(int intentFlags) {
        this.intentFlags = intentFlags;
        return this;
    }

    public Class<?> getStartActivity() {
        return startActivityClazz;
    }

    public int getIntentFlags() {
        return intentFlags;
    }
}

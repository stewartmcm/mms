package com.mms.manage_my_stuff.events;

import com.mms.manage_my_stuff.BaseFragment;

public class StartFragmentEvent extends BaseUnboundViewEvent {

    private Class<? extends BaseFragment> fragmentToStart;

    public StartFragmentEvent fragmentName(Class<? extends BaseFragment> fragmentToStart) {
        this.fragmentToStart = fragmentToStart;
        return this;
    }

    public static StartFragmentEvent build() {
        return new StartFragmentEvent();
    }

    public Class<? extends BaseFragment> getFragmentToStart() {
        return fragmentToStart;
    }
}

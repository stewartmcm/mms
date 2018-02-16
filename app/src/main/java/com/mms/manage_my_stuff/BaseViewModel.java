package com.mms.manage_my_stuff;

import com.mms.manage_my_stuff.events.StartFragmentEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;

public class BaseViewModel extends BaseLifeCycleViewModel {

    protected UnboundViewEventBus eventBus;

    public BaseViewModel(UnboundViewEventBus eventBus) {
        this.eventBus = eventBus;
    }

    protected void startFragment(Class<? extends BaseFragment> fragmentToStart) {
        StartFragmentEvent event = StartFragmentEvent.build().fragmentName(fragmentToStart);
        eventBus.send(event);
    }
}

package com.mms.manage_my_stuff.di;

import android.app.Application;
import android.content.Context;

import com.mms.manage_my_stuff.ui.RoomMenuViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    private RoomMenuViewModel roomMenuViewModel;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    RoomMenuViewModel provideRoomMenuViewModel() {
        return roomMenuViewModel;
    }
}

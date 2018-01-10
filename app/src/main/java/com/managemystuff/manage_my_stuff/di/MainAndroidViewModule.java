package com.managemystuff.manage_my_stuff.di;

import com.managemystuff.manage_my_stuff.ui.MainActivity;
import com.managemystuff.manage_my_stuff.ui.RoomMenuFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainAndroidViewModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract RoomMenuFragment bindRoomMenuFragment();
}

package com.mms.manage_my_stuff.di;

import com.mms.manage_my_stuff.ui.MainActivity;
import com.mms.manage_my_stuff.ui.boxcount.BoxCountListFragment;
import com.mms.manage_my_stuff.ui.boxtype.BoxTypeListFragment;
import com.mms.manage_my_stuff.ui.boxdetails.BoxDetailsListFragment;
import com.mms.manage_my_stuff.ui.roomlist.RoomListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainAndroidViewModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract RoomListFragment bindRoomListFragment();

    @ContributesAndroidInjector
    abstract BoxTypeListFragment bindBoxTypeListFragment();

    @ContributesAndroidInjector
    abstract BoxCountListFragment bindBoxCountListFragment();

    @ContributesAndroidInjector
    abstract BoxDetailsListFragment bindBoxDetailsListFragment();
}

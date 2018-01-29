package com.mms.manage_my_stuff.di;

import com.mms.manage_my_stuff.ui.BoxCountListFragment;
import com.mms.manage_my_stuff.ui.BoxTypeListFragment;
import com.mms.manage_my_stuff.ui.RoomListActivity;
import com.mms.manage_my_stuff.ui.RoomActivity;
import com.mms.manage_my_stuff.ui.RoomListFragment;
import com.mms.manage_my_stuff.ui.box_contents.BoxContentsActivity;
import com.mms.manage_my_stuff.ui.box_contents.BoxContentsListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainAndroidViewModule {

    @ContributesAndroidInjector
    abstract RoomListActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract RoomActivity bindRoomActivity();

    @ContributesAndroidInjector
    abstract BoxContentsActivity bindBoxContentsActivity();

    @ContributesAndroidInjector
    abstract RoomListFragment bindRoomMenuFragment();

    @ContributesAndroidInjector
    abstract BoxTypeListFragment bindBoxSelectionFragment();

    @ContributesAndroidInjector
    abstract BoxCountListFragment bindBoxCountFragment();

    @ContributesAndroidInjector
    abstract BoxContentsListFragment bindBoxContentsListFragment();
}

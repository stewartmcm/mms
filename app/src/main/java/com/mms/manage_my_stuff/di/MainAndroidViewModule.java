package com.mms.manage_my_stuff.di;

import com.mms.manage_my_stuff.ui.BoxCountListFragment;
import com.mms.manage_my_stuff.ui.BoxTypeListFragment;
import com.mms.manage_my_stuff.ui.roomlist.RoomListActivity;
import com.mms.manage_my_stuff.ui.RoomActivity;
import com.mms.manage_my_stuff.ui.boxdetails.BoxDetailsActivity;
import com.mms.manage_my_stuff.ui.boxdetails.BoxDetailsListFragment;
import com.mms.manage_my_stuff.ui.roomlist.RoomListViewModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainAndroidViewModule {

    @ContributesAndroidInjector
    abstract RoomListActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract RoomActivity bindRoomActivity();

    @ContributesAndroidInjector
    abstract BoxDetailsActivity bindBoxContentsActivity();

    @ContributesAndroidInjector
    abstract RoomListViewModel.RoomListFragment bindRoomMenuFragment();

    @ContributesAndroidInjector
    abstract BoxTypeListFragment bindBoxTypeFragment();

    @ContributesAndroidInjector
    abstract BoxCountListFragment bindBoxCountFragment();

    @ContributesAndroidInjector
    abstract BoxDetailsListFragment bindBoxContentsListFragment();
}

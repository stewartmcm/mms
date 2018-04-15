package com.mms.manage_my_stuff.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;


public class ToolbarViewModel extends AndroidViewModel {

    public ObservableField<String> title = new ObservableField<>();


    public ToolbarViewModel(@NonNull Application application) {
        super(application);
    }

    public void logout() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }


}

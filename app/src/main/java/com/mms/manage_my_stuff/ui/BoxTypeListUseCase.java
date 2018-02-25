package com.mms.manage_my_stuff.ui;

import com.mms.manage_my_stuff.UseCase;

public class BoxTypeListUseCase implements UseCase {

    String title;

    public BoxTypeListUseCase(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

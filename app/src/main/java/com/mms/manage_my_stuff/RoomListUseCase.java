package com.mms.manage_my_stuff;

public class RoomListUseCase implements UseCase {

    String title;

    public RoomListUseCase(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

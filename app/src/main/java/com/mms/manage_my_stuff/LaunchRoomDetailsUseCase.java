package com.mms.manage_my_stuff;

import com.mms.manage_my_stuff.models.Room;

public class LaunchRoomDetailsUseCase implements UseCase {

    Room room;

    public LaunchRoomDetailsUseCase(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}

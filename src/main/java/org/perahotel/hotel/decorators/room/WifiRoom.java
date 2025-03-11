package org.perahotel.hotel.decorators.room;

import org.perahotel.models.Room;

public class WifiRoom extends RoomDecorator {
    public WifiRoom(Room roomDecorated) {
        super(roomDecorated);
    }

    @Override
    public String Description() {
        return super.Description() + "with Wifi\n";
    }

    @Override
    public double Cost() {
        return super.Cost() + 5;
    }
}

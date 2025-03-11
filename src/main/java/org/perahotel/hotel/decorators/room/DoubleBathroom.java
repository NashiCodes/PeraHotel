package org.perahotel.hotel.decorators.room;

import org.perahotel.models.Room;

public class DoubleBathroom extends RoomDecorator {
    public DoubleBathroom(Room roomDecorated) {
        super(roomDecorated);
    }

    @Override
    public String Description() {
        return roomDecorated.Description() + "with two bathroom\n";
    }

    @Override
    public double Cost() {
        return roomDecorated.Cost() + 20;
    }
}

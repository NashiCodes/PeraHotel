package org.perahotel.hotel.decorators.room;

import org.perahotel.models.Room;

public class DoubleSingleBed extends RoomDecorator {
    public DoubleSingleBed(Room roomDecorated) {
        super(roomDecorated);
    }

    @Override
    public String Description() {
        return roomDecorated.Description() + "with double single bed\n";
    }

    @Override
    public double Cost() {
        return roomDecorated.Cost() + 10;
    }
}

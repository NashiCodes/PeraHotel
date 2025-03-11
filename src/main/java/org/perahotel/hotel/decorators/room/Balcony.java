package org.perahotel.hotel.decorators.room;

import org.perahotel.models.Room;

public class Balcony extends RoomDecorator {
    public Balcony(Room roomDecorated) {
        super(roomDecorated);
    }

    @Override
    public String Description() {
        return roomDecorated.Description() + "with balcony\n";
    }

    @Override
    public double Cost() {
        return roomDecorated.Cost() + 30;
    }
}

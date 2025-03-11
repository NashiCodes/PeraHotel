package org.perahotel.hotel.decorators.room;

import org.perahotel.models.Room;

public class KingSize extends RoomDecorator {
    public KingSize(Room roomDecorated) {
        super(roomDecorated);
    }

    @Override
    public String Description() {
        return super.Description() + "with a king size bed\n";
    }

    @Override
    public double Cost() {
        return super.Cost() + 20;
    }

}

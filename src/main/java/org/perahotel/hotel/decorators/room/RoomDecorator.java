package org.perahotel.hotel.decorators.room;

import org.perahotel.models.Room;
import org.perahotel.shared.Decorator;

public abstract class RoomDecorator extends Room implements Decorator {
    protected Room roomDecorated;

    public RoomDecorator(Room roomDecorated) {
        this.roomDecorated = roomDecorated;
    }

    @Override
    public String Description() {
        return roomDecorated.Description();
    }

    @Override
    public double Cost() {
        return roomDecorated.Cost();
    }
}

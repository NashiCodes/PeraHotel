package org.perahotel.hotel.decorators.room;

import org.perahotel.models.Room;
import org.perahotel.shared.Decorator;

public abstract class RoomDecorator extends Room implements Decorator {
    protected Room roomDecorated;

    public RoomDecorator(Room roomDecorated) {
        this.roomDecorated = roomDecorated;
        this.setId(roomDecorated.getId());
        this.setNumber(roomDecorated.getNumber());
        this.setPrice(roomDecorated.Cost());
        this.setState(roomDecorated.getState());
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

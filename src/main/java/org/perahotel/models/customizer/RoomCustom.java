package org.perahotel.models.customizer;

import org.perahotel.hotel.decorators.room.*;
import org.perahotel.models.Room;

public class RoomCustom {
    private Room room;

    public RoomCustom(Room room) {
        this.room = room;
    }

    public RoomCustom addWifi() {
        if (this.room instanceof WifiRoom) return this;
        this.room = new WifiRoom(this.room);
        return this;
    }

    public RoomCustom addBalcony() {
        if (this.room instanceof Balcony) return this;
        this.room = new Balcony(this.room);
        return this;
    }

    public RoomCustom dubleSingleBed() {
        if (this.room instanceof DoubleSingleBed) return this;
        this.room = new DoubleSingleBed(this.room);
        return this;
    }

    public RoomCustom addKingSize() {
        if (this.room instanceof KingSize) return this;
        this.room = new KingSize(this.room);
        return this;
    }

    public RoomCustom addDoubleBathroom() {
        if (this.room instanceof DoubleBathroom) return this;
        this.room = new DoubleBathroom(this.room);
        return this;
    }

    public Room build() {
        return this.room;
    }
}

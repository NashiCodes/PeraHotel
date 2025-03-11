package org.perahotel.hotel.states.room;

import org.perahotel.models.Room;

/**
 * Room is reserved.
 * <p>
 * Room can be occupied.
 * Room can be made available.
 * Room can be put under maintenance.
 */
public class Reserved extends RoomState {
    private static final Reserved instance = new Reserved();

    private Reserved() {
        super("Room is reserved");
    }

    public static Reserved getInstance() {
        if (instance == null) {
            return new Reserved();
        }
        return instance;
    }

    @Override
    public boolean available(Room room) {
        return transitionTo(room, Available.getInstance());
    }

    @Override
    public boolean maintenance(Room room) {
        return transitionTo(room, Maintenance.getInstance());
    }

    @Override
    public boolean occupy(Room room) {
        return transitionTo(room, Occupied.getInstance());
    }
}

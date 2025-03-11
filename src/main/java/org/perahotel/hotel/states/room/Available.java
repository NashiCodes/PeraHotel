package org.perahotel.hotel.states.room;

import org.perahotel.models.Room;

/**
 * Room is available.
 * <p>
 * Room can be reserved, occupied or put under maintenance.
 */
public class Available extends RoomState {
    private static final Available instance = new Available();

    private Available() {
        super("Room is available");
    }

    public static Available getInstance() {
        if (instance == null) {
            return new Available();
        }
        return instance;
    }

    @Override
    public boolean reserve(Room room) {
        return transitionTo(room, Reserved.getInstance());
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


package org.perahotel.hotel.states.room;

import org.perahotel.models.Room;

/**
 * Room is occupied.
 * <p>
 * Room can be disoccupied.
 * Room can be put under maintenance.
 */
public class Occupied extends RoomState {
    private static final Occupied instance = new Occupied();

    private Occupied() {
        super("Room is occupied");
    }

    public static Occupied getInstance() {
        if (instance == null) {
            return new Occupied();
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
}

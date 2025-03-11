package org.perahotel.hotel.states.room;

import org.perahotel.models.Room;

/**
 * Room is under maintenance.
 * <p>
 * Room can be made available.
 */
public class Maintenance extends RoomState {
    private static final Maintenance instance = new Maintenance();

    private Maintenance() {
        super("Room is under maintenance");
    }

    public static Maintenance getInstance() {
        if (instance == null) {
            return new Maintenance();
        }
        return instance;
    }

    @Override
    public boolean available(Room room) {
        return transitionTo(room, Available.getInstance());
    }
}

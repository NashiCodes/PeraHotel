package org.perahotel.hotel.states.room;

import org.perahotel.models.Room;
import org.perahotel.shared.BaseState;

/**
 * Represents the state of a Room.
 * <p>
 * A Room can be in one of the following states:
 * <ul>
 *     <li>Available</li>
 *     <li>Occupied</li>
 *     <li>Maintenance</li>
 *     <li>Reserved</li>
 * </ul>
 */
public abstract class RoomState extends BaseState<Room, RoomState> {
    protected RoomState(String stateMessage) {
        super(stateMessage);
    }

    @Override
    protected boolean transitionTo(Room mutable, RoomState next) {
        mutable.setState(next);
        return true;
    }

    public boolean reserve(Room room) {
        throw new IllegalStateException(this.getState() + " and cannot be reserved");
    }

    public boolean available(Room room) {
        throw new IllegalStateException(this.getState() + " and cannot be available");
    }

    public boolean maintenance(Room room) {
        throw new IllegalStateException(this.getState() + " and cannot be maintenance");
    }

    public boolean occupy(Room room) {
        throw new IllegalStateException(this.getState() + " and cannot be occupied");
    }

}

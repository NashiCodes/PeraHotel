package org.perahotel.hotel.states.reservation;


/**
 * Reservation CheckedOut state.
 * <p>
 * When a reservation is checked out,
 * it can no longer change its state.
 * </p>
 * If the guest wants to stay longer, a new reservation must be made.
 */
public class CheckedOut extends ReservationState {
    private static final CheckedOut instance = new CheckedOut();

    private CheckedOut() {
        super("Reservation is checked out");
    }

    public static CheckedOut getInstance() {
        return instance;
    }
}

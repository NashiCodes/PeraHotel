package org.perahotel.hotel.states.reservation;

import org.perahotel.models.Reservation;

/**
 * Reservation checked in state.
 * It means that the guest has arrived at the hotel,
 * and the reservation is active.
 * The guest can:
 * - Check out
 * - Cancel (The Hotel can cancel the reservation too for various reasons)
 */
public class CheckedIn extends ReservationState {
    private static final CheckedIn instance = new CheckedIn();

    private CheckedIn() {
        super("Reservation is checked in");
    }

    public static CheckedIn getInstance() {
        return instance;
    }

    public boolean CheckOut(Reservation reservation) {
        return transitionTo(reservation, CheckedOut.getInstance());
    }

    public boolean Cancel(Reservation reservation) {
        return transitionTo(reservation, Canceled.getInstance());
    }
}

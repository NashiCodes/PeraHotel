package org.perahotel.hotel.states.reservation;

/**
 * Reservation Canceled state.
 * When a reservation is canceled,
 * it can no longer change its state.
 */
public class Canceled extends ReservationState {
    private static final Canceled instance = new Canceled();

    private Canceled() {
        super("Reservation is canceled");
    }

    public static Canceled getInstance() {
        return instance;
    }
}

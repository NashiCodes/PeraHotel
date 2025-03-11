package org.perahotel.hotel.states.reservation;

import org.perahotel.models.Reservation;

/**
 * Reservation Confirmed state.
 * <p>
 * When a reservation is confirmed, it means that the Hotel has accepted the reservation.
 * The guest can:
 * - Check in
 * - Cancel
 * <p>
 * The Hotel can:
 * - Decline
 * - Check in
 */
public class Confirmed extends ReservationState {
    private static final Confirmed instance = new Confirmed();

    private Confirmed() {
        super("Reservation is confirmed");
    }

    public static Confirmed getInstance() {
        return instance;
    }

    public boolean Cancel(Reservation reservation) {
        return transitionTo(reservation, Canceled.getInstance());
    }

    public boolean CheckIn(Reservation reservation) {
        return transitionTo(reservation, CheckedIn.getInstance());
    }

    public boolean Decline(Reservation reservation) {
        return transitionTo(reservation, Declined.getInstance());
    }
}

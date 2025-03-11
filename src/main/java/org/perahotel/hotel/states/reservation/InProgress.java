package org.perahotel.hotel.states.reservation;

import org.perahotel.models.Reservation;

/**
 * Reservation InProgress state.
 * <p>
 * When a reservation is in progress, it means that the Hotel is still processing the reservation.
 * The guest can:
 * - Cancel
 * <p>
 * The Hotel can:
 * - Decline
 * - Confirm
 * <p>
 * If the Hotel confirms the reservation, the guest can check in.
 * If the Hotel declines the reservation, the guest must make a new reservation.
 * If the guest cancels the reservation, the reservation is canceled.
 */
public class InProgress extends ReservationState {
    private static final InProgress instance = new InProgress();

    private InProgress() {
        super("Reservation is in progress");
    }

    public static InProgress getInstance() {
        return instance;
    }

    public boolean Cancel(Reservation reservation) {
        return transitionTo(reservation, Canceled.getInstance());
    }

    public boolean Decline(Reservation reservation) {
        return transitionTo(reservation, Declined.getInstance());
    }

    public boolean Confirm(Reservation reservation) {
        return transitionTo(reservation, Confirmed.getInstance());
    }
}

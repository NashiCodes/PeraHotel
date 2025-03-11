package org.perahotel.hotel.states.reservation;

/**
 * Reservation Declined state.
 * <p>
 * When a reservation is declined, it means that the Hotel has rejected the reservation.
 * This can happen for various reasons.
 * No further action can be taken on a declined reservation.
 * The guest must make a new reservation.
 * Or the Hotel can make a new reservation for the guest.
 */
public class Declined extends ReservationState {
    private static final Declined instance = new Declined();

    private Declined() {
        super("Reservation is declined");
    }

    public static Declined getInstance() {
        return instance;
    }

}

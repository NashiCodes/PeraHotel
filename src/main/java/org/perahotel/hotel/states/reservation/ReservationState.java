package org.perahotel.hotel.states.reservation;

import org.perahotel.models.Reservation;
import org.perahotel.shared.BaseState;

/**
 * Represents the state of a reservation.
 * A reservation can be in one of the following states:
 * - InProgress
 * - Confirmed
 * - Declined
 * - CheckedIn
 * - CheckedOut
 * - Canceled
 * <p>
 * The transition between states is controlled by its subclasses.
 * When a reservation is in a certain state, it can only transition to a specific set of states.
 * When a reservation trys to transition to an invalid state, the transition will be rejected,
 * and throw an IllegalStateException with a message indicating the invalid transition.
 */
public class ReservationState extends BaseState<Reservation, ReservationState> {
    private final InProgress ReservationInProgress = InProgress.getInstance();
    private final Confirmed ReservationConfirmed = Confirmed.getInstance();
    private final CheckedIn ReservationCheckedIn = CheckedIn.getInstance();
    private final CheckedOut ReservationCheckedOut = CheckedOut.getInstance();
    private final Canceled ReservationCanceled = Canceled.getInstance();
    private final Declined ReservationDeclined = Declined.getInstance();

    protected ReservationState(String stateMessage) {
        super(stateMessage);
    }

    @Override
    protected boolean transitionTo(Reservation mutable, ReservationState next) {
        mutable.setState(next);
        return true;
    }

    public boolean CheckIn(Reservation reservation) {
        throw new IllegalStateException(this.getState() + " and cannot be checked in");
    }

    public boolean CheckOut(Reservation reservation) {
        throw new IllegalStateException(this.getState() + " and cannot be checked out");
    }

    public boolean Cancel(Reservation reservation) {
        throw new IllegalStateException(this.getState() + " and cannot be canceled");
    }

    public boolean Decline(Reservation reservation) {
        throw new IllegalStateException(this.getState() + " and cannot be declined");
    }

    public boolean Confirm(Reservation reservation) {
        throw new IllegalStateException(this.getState() + " and cannot be confirmed");
    }

}

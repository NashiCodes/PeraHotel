package org.perahotel.models.builders;

import org.perahotel.hotel.states.reservation.InProgress;
import org.perahotel.models.Reservation;
import org.perahotel.models.Room;
import org.perahotel.shared.Builder;

import java.util.UUID;

public class ReservationBuilder implements Builder<Reservation> {
    private final Reservation reservation;

    public ReservationBuilder(Room room, UUID guestId, int days) {
        reservation = new Reservation(room, guestId, days);
    }

    public ReservationBuilder() {
        reservation = new Reservation();
    }

    public Reservation build() {
        validateReservation();
        return reservation;
    }

    public ReservationBuilder setRoom(Room room) {
        reservation.setRoom(room);
        return this;
    }

    public ReservationBuilder setGuestId(UUID guest) {
        reservation.setGuestId(guest);
        return this;
    }

    public ReservationBuilder setDays(int days) {
        reservation.setDays(days);
        return this;
    }

    private void validateReservation() {
        if (reservation.getRoom() == null)
            throw new IllegalArgumentException("Room is required");
        if (reservation.getGuestId() == null)
            throw new IllegalArgumentException("Guest is required");
        if (reservation.getDays() <= 0)
            throw new IllegalArgumentException("Days must be greater than 0");
        if (reservation.getDays() > 30)
            throw new IllegalArgumentException("You can't reserve a room for more than 30 days");
        if (reservation.getState() != InProgress.getInstance())
            throw new IllegalArgumentException("Invalid reservation state");
    }
}

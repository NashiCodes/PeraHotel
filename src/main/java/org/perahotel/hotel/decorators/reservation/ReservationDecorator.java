package org.perahotel.hotel.decorators.reservation;

import org.perahotel.models.Reservation;
import org.perahotel.shared.Decorator;

public abstract class ReservationDecorator extends Reservation implements Decorator {
    protected Reservation reservationDecorated;

    public ReservationDecorator(Reservation reservationDecorated) {
        this.setGuestId(reservationDecorated.getGuestId());
        this.setRoom(reservationDecorated.getRoom());
        this.setDays(reservationDecorated.getDays());
        this.setState(reservationDecorated.getState());
        this.reservationDecorated = reservationDecorated;
    }

    @Override
    public String Description() {
        return reservationDecorated.Description();
    }

    @Override
    public double Cost() {
        return reservationDecorated.Cost();
    }
}

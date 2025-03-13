package org.perahotel.hotel.decorators.reservation;

import org.perahotel.models.Reservation;

public class BreakfastIncluded extends ReservationDecorator {
    public BreakfastIncluded(Reservation reservationDecorated) {
        super(reservationDecorated);
    }

    @Override
    public String Description() {
        return reservationDecorated.Description() + "with breakfast included\n";
    }

    @Override
    public double Cost() {
        return reservationDecorated.Cost() + (reservationDecorated.getDays() * 10);
    }
}

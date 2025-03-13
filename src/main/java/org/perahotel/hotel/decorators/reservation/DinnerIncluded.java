package org.perahotel.hotel.decorators.reservation;

import org.perahotel.models.Reservation;

public class DinnerIncluded extends ReservationDecorator {
    public DinnerIncluded(Reservation reservationDecorated) {
        super(reservationDecorated);
    }

    @Override
    public String Description() {
        return reservationDecorated.Description() + "with dinner included\n";
    }

    @Override
    public double Cost() {
        return reservationDecorated.Cost() + (reservationDecorated.getDays() * 20);
    }
}

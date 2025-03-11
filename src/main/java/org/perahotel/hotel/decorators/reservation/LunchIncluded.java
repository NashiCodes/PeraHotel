package org.perahotel.hotel.decorators.reservation;

import org.perahotel.models.Reservation;

public class LunchIncluded extends ReservationDecorator {
    public LunchIncluded(Reservation reservationDecorated) {
        super(reservationDecorated);
    }

    @Override
    public String Description() {
        return reservationDecorated.Description() + "with lunch included\n";
    }

    @Override
    public double Cost() {
        return reservationDecorated.Cost() + 15;
    }
}

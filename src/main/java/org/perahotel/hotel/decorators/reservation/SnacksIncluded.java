package org.perahotel.hotel.decorators.reservation;

import org.perahotel.models.Reservation;

public class SnacksIncluded extends ReservationDecorator {
    public SnacksIncluded(Reservation reservationDecorated) {
        super(reservationDecorated);
    }

    @Override
    public String Description() {
        return reservationDecorated.Description() + "with all snacks included\n";
    }

    @Override
    public double Cost() {
        return reservationDecorated.Cost() + 10;
    }
}

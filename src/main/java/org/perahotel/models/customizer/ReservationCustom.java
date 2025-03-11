package org.perahotel.models.customizer;

import org.perahotel.hotel.decorators.reservation.BreakfastIncluded;
import org.perahotel.hotel.decorators.reservation.DinnerIncluded;
import org.perahotel.hotel.decorators.reservation.LunchIncluded;
import org.perahotel.hotel.decorators.reservation.SnacksIncluded;
import org.perahotel.models.Reservation;

public class ReservationCustom {
    private Reservation reservation;

    public ReservationCustom(Reservation reservation) {
        this.reservation = reservation;
    }

    public ReservationCustom addBreakfast() {
        if (reservation instanceof BreakfastIncluded) return this;
        reservation = new BreakfastIncluded(reservation);
        return this;
    }

    public ReservationCustom addLunch() {
        if (reservation instanceof LunchIncluded) return this;
        reservation = new LunchIncluded(reservation);
        return this;
    }

    public ReservationCustom addDinner() {
        if (reservation instanceof DinnerIncluded) return this;
        reservation = new DinnerIncluded(reservation);
        return this;
    }

    public ReservationCustom addSnacks() {
        if (reservation instanceof SnacksIncluded) return this;
        reservation = new SnacksIncluded(reservation);
        return this;
    }
}

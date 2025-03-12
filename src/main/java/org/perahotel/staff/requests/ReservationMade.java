package org.perahotel.staff.requests;

import org.perahotel.shared.Request;

public class ReservationMade extends Request {
    private static final ReservationMade INSTANCE = new ReservationMade();

    private ReservationMade() {
        super("A new reservation has been made");
    }

    public static ReservationMade getInstance() {
        return INSTANCE;
    }

}

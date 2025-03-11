package org.perahotel.staff.requests;

import org.perahotel.shared.Request;

public class Robbery extends Request {
    private static final Robbery INSTANCE = new Robbery();

    private Robbery() {
        super("A robbery has been reported.");
    }

    public static Robbery getInstance() {
        return INSTANCE;
    }
}

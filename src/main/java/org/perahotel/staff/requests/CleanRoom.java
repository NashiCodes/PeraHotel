package org.perahotel.staff.requests;

import org.perahotel.shared.Request;

public class CleanRoom extends Request {
    private static final CleanRoom INSTANCE = new CleanRoom();

    private CleanRoom() {
        super("Water leakage in room 101");
    }

    public static CleanRoom getInstance() {
        return INSTANCE;
    }
}

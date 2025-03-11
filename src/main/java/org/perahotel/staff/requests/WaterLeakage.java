package org.perahotel.staff.requests;

import org.perahotel.shared.Request;

public class WaterLeakage extends Request {
    private static final WaterLeakage INSTANCE = new WaterLeakage();

    private WaterLeakage() {
        super("Water leakage in room 101");
    }

    public static WaterLeakage getInstance() {
        return INSTANCE;
    }
}

package org.perahotel.staff.requests;

import org.perahotel.shared.Request;

public class MaintenanceRequest extends Request {
    private static final MaintenanceRequest INSTANCE = new MaintenanceRequest();

    private MaintenanceRequest() {
        super("A room needs maintenance");
    }

    public static MaintenanceRequest getInstance() {
        return INSTANCE;
    }
}

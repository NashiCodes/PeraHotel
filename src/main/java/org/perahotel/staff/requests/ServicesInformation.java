package org.perahotel.staff.requests;

import org.perahotel.shared.Request;

public class ServicesInformation extends Request {
    private static final ServicesInformation INSTANCE = new ServicesInformation();

    private ServicesInformation() {
        super("Somebody is asking for information about the services.");
    }

    public static ServicesInformation getInstance() {
        return INSTANCE;
    }

}

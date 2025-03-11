package org.perahotel.staff.requests;

import org.perahotel.shared.Request;

public class NewFranchise extends Request {
    private static final NewFranchise INSTANCE = new NewFranchise();

    private NewFranchise() {
        super("Request to open a new franchise in the city");
    }

    public static Request getInstance() {
        return INSTANCE;
    }
}

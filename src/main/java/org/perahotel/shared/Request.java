package org.perahotel.shared;

public abstract class Request {
    protected String Message;

    public Request(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }
}

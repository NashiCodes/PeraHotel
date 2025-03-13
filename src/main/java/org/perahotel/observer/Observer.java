package org.perahotel.observer;

public class Observer {
    private String LastMessage = "";

    public void update(String message) {
        if (!message.equals(this.getLastMessage())) {
            setLastMessage("[ALERT] : " + message);
        }
    }

    public String getLastMessage() {
        return this.LastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.LastMessage = lastMessage;
    }
}

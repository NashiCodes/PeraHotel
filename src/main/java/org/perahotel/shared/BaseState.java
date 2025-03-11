package org.perahotel.shared;

public abstract class BaseState<O, S extends BaseState<O, S>> {
    protected final String StateMessage;

    protected BaseState(String stateMessage) {
        this.StateMessage = stateMessage;
    }

    protected abstract boolean transitionTo(O mutable, S next);

    public String getState() {
        return this.StateMessage;
    }
}

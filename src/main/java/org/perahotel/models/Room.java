package org.perahotel.models;

import org.perahotel.hotel.states.room.Available;
import org.perahotel.hotel.states.room.RoomState;

import java.util.UUID;


public class Room {
    private final UUID id;
    private double Price = 100;
    private String Number;
    private RoomState State;

    public Room() {
        this.State = Available.getInstance();
        this.id = UUID.randomUUID();
        this.Number = "";
    }

    public Room(String number) {
        this.id = UUID.randomUUID();
        this.Number = number;
        this.State = Available.getInstance();
    }

    public String Description() {
        return "Room " + Number + "\n";
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        this.Number = number;
    }

    public RoomState getState() {
        return State;
    }

    public void setState(RoomState state) {
        this.State = state;
    }

    public double Cost() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public UUID getId() {
        return id;
    }

    public void reserve() {
        State.reserve(this);
    }

    public boolean available() {
        return State.available(this);
    }

    public boolean maintenance() {
        return State.maintenance(this);
    }

    public boolean occupy() {
        return State.occupy(this);
    }
}

package org.perahotel.models;

import org.perahotel.hotel.states.reservation.InProgress;
import org.perahotel.hotel.states.reservation.ReservationState;

import java.util.UUID;


public class Reservation {
    private final UUID id = UUID.randomUUID();
    private UUID reservationValidatedBy;
    private Room room;
    private UUID guestId;
    private int days;
    private ReservationState state;


    public Reservation() {
    }

    public Reservation(Room room, UUID guestId, int days, UUID reservationValidatedBy) {
        this.room = room;
        this.guestId = guestId;
        this.days = days;
        this.setState(InProgress.getInstance());
        this.reservationValidatedBy = reservationValidatedBy;
    }

    public void CheckIn() {
        state.CheckIn(this);
        room.occupy();
    }

    public void CheckOut() {
        state.CheckOut(this);
        room.maintenance();
    }

    public boolean Cancel() {
        return state.Cancel(this);
    }

    public void Decline() {
        state.Decline(this);
    }

    public void Confirm() {
        state.Confirm(this);
        room.reserve();
    }

    public String Description() {
        return "Reservation for room " + room + " for " + days + " days\n";
    }

    public ReservationState getState() {
        return state;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public UUID getGuestId() {
        return guestId;
    }

    public void setGuestId(UUID guestId) {
        this.guestId = guestId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double Cost() {
        return room.Cost() * days;
    }

    public UUID getReservationValidatedBy() {
        return reservationValidatedBy;
    }
}

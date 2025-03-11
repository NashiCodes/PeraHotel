package org.perahotel.models;


import org.perahotel.hotel.states.room.*;
import org.perahotel.staff.Janitor;
import org.perahotel.staff.Manager;
import org.perahotel.staff.Secretary;
import org.perahotel.staff.factories.JanitorFactory;
import org.perahotel.staff.factories.ManagerFactory;
import org.perahotel.staff.factories.SecretaryFactory;

import java.util.*;

public class Hotel {
    private static final Hotel instance = new Hotel().initialize();
    private final List<Reservation> Reservations = new ArrayList<>();
    private final Map<RoomState, List<Room>> Rooms = new HashMap<>();
    private Janitor janitor;
    private Secretary secretary;
    private int MAX_ROOMS = 10;
    private Manager manager;

    private Hotel() {
    }

    public static Hotel getInstance() {
        return instance;
    }

    private Hotel initialize() {
        ConfigureHotelStorage();
        createRooms();
        initializeEmployees();
        return this;
    }

    public void increaseHotelCapacity(int rooms, UUID token) {
        if (token.equals(manager.getToken())) {
            MAX_ROOMS += rooms;
            for (int i = 0; i < rooms; i++) {
                var room = new Room(String.valueOf(MAX_ROOMS + i));
                this.Rooms.get(Available.getInstance()).add(room);
            }
        } else {
            throw new IllegalArgumentException("Unauthorized request");
        }
    }

    private void ConfigureHotelStorage() {

        this.Rooms.put(Available.getInstance(), new ArrayList<>());
        this.Rooms.put(Maintenance.getInstance(), new ArrayList<>());
        this.Rooms.put(Occupied.getInstance(), new ArrayList<>());
        this.Rooms.put(Reserved.getInstance(), new ArrayList<>());
    }

    private void createRooms() {
        for (int i = 0; i < MAX_ROOMS; i++) {
            var room = new Room(String.valueOf(i));
            this.Rooms.get(Available.getInstance()).add(room);
        }
    }

    private void initializeEmployees() {
        var janitorFactory = new JanitorFactory();
        var managerFactory = new ManagerFactory();
        var secretaryFactory = new SecretaryFactory();

        this.manager = managerFactory.create("Manager");
        this.manager.setNext(null);
        this.janitor = janitorFactory.create("Robiscreuza");
        this.janitor.setNext(manager);
        this.secretary = secretaryFactory.create("João");
        this.secretary.setNext(janitor);
    }

    private boolean hasRoomAvailable() {
        var AvailableRooms = Rooms.get(Available.getInstance());
        return !AvailableRooms.isEmpty();
    }

    public Room getAvailableRoom() {
        if (hasRoomAvailable()) {
            return Rooms.get(Available.getInstance()).getFirst();
        }
        throw new IllegalArgumentException("No rooms available");
    }

    public void checkIn(Reservation reservation) {
        reservation.CheckIn();
        this.Rooms.get(Available.getInstance()).remove(reservation.getRoom());
        this.Rooms.get(Occupied.getInstance()).add(reservation.getRoom());
    }

    public void processReservation(Reservation reservation) {
        if (this.Rooms.get(Available.getInstance()).contains(reservation.getRoom())) {
            reservation.Confirm();
            this.Rooms.get(Available.getInstance()).remove(reservation.getRoom());
            this.Rooms.get(Reserved.getInstance()).add(reservation.getRoom());
            this.Reservations.add(reservation);

            manager.setReservations(manager.getReservations() + 1);
            return;
        }

        throw new IllegalArgumentException("Room is not reserved, reservation declined");
    }

    public Secretary requestSecretary() {
        return secretary;
    }

    public void checkOut(Reservation reservation) {
        reservation.CheckOut();
        this.Rooms.get(Occupied.getInstance()).remove(reservation.getRoom());
        this.Rooms.get(Maintenance.getInstance()).add(reservation.getRoom());

        janitor.cleanRoom(reservation.getRoom());

        manager.setRoomsCleaned(manager.getRoomsCleaned() + 1);

        this.Rooms.get(Available.getInstance()).add(reservation.getRoom());

        this.Reservations.remove(reservation);
    }
}

package hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.perahotel.hotel.states.reservation.CheckedIn;
import org.perahotel.hotel.states.room.Occupied;
import org.perahotel.models.Client;
import org.perahotel.models.builders.ClientBuilder;
import org.perahotel.staff.Janitor;
import org.perahotel.staff.Manager;
import org.perahotel.staff.Secretary;
import org.perahotel.staff.factories.JanitorFactory;
import org.perahotel.staff.factories.ManagerFactory;
import org.perahotel.staff.factories.SecretaryFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CheckInTest {
    public Secretary secretary;
    public Janitor janitor;
    public Manager manager;
    public Client client;

    @BeforeEach
    public void setUp() {
        secretary = new SecretaryFactory().create("Secretary");
        janitor = new JanitorFactory().create("Janitor");
        manager = new ManagerFactory().create("Manager");
        client = new ClientBuilder("João", "teste@gmail", "123456").build();

        secretary.setNext(janitor);
        janitor.setNext(manager);

        secretary.createReservation(client, 10);
        var reservation = client.getReservation();
        var amount = reservation.Cost();
        client.addCredit(amount);
        secretary.reserve(reservation, client);
    }


    @Test
    public void deveRealizarCheckIn() {
        try {
            var reservation = client.getReservation();
            secretary.checkClientIn(client, reservation);
            assert reservation.getState() == CheckedIn.getInstance();
            assert reservation.getRoom().getState() == Occupied.getInstance();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deveFalharClienteErrado() {
        try {
            var reservation = client.getReservation();
            var client2 = new ClientBuilder("João", "teste@gmail", "123456").build();
            secretary.checkClientIn(client2, reservation);
            fail();
        } catch (Exception e) {
            assert e.getMessage().equals("Client must be the same as the one in the reservation");
        }
    }

    @Test
    public void deveFalharReservaCancelada() {
        try {
            var reservation = client.getReservation();
            secretary.cancelReservation(reservation, client);
            secretary.checkClientIn(client, reservation);
            fail();
        } catch (Exception e) {
            assertEquals("Reservation is canceled and cannot be checked in", e.getMessage());
        }
    }

    @Test
    public void deveFalharQuartoOcupado() {
        try {
            var reservation = client.getReservation();
            secretary.checkClientIn(client, reservation);
            var room = reservation.getRoom();
            var client2 = new ClientBuilder("João", "teste@gmail", "123456").build();
            secretary.createReservation(client2, 10);
            var reservation2 = client2.getReservation();
            reservation2.setRoom(room);
            client2.setCredit(reservation2.Cost());
            secretary.reserve(reservation2, client2);
            secretary.checkClientIn(client2, reservation2);
            fail();
        } catch (Exception e) {
            assertEquals("Room is occupied and cannot be reserved", e.getMessage());
        }
    }

    @Test
    public void deveFalharReservaDeclined() {
        try {
            var reservation = client.getReservation();
            reservation.Decline();
            secretary.checkClientIn(client, reservation);
            fail();
        } catch (Exception e) {
            assertEquals("Reservation is declined and cannot be checked in", e.getMessage());
        }
    }

    @Test
    public void deveFalharReservaCheckedOut() {
        try {
            var reservation = client.getReservation();
            secretary.checkClientIn(client, reservation);
            reservation.CheckOut();
            secretary.checkClientIn(client, reservation);
            fail();
        } catch (Exception e) {
            assertEquals("Reservation is checked out and cannot be checked in", e.getMessage());
        }
    }


}

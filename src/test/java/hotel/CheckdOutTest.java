package hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.perahotel.hotel.states.reservation.CheckedOut;
import org.perahotel.hotel.states.room.Maintenance;
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

public class CheckdOutTest {
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
        secretary.checkClientIn(client, reservation);
    }

    @Test
    public void deveRealizarCheckOut() {
        try {
            var reservation = client.getReservation();
            secretary.checkClientOut(client, reservation);
            assertEquals(CheckedOut.getInstance(), reservation.getState());
            assertEquals(Maintenance.getInstance(), reservation.getRoom().getState());

            assertEquals(1, janitor.getRoomsCleaned());
            assertEquals(1, manager.getRoomsCleaned());

            assertEquals(1520.0, janitor.getSalary());
            assertEquals(3120, manager.getSalary());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void naoDeveRealizarCheckOutClienteErrado() {
        try {
            var reservation = client.getReservation();
            var client2 = new ClientBuilder("João", "teste@gmail", "123456").build();
            secretary.checkClientOut(client2, reservation);
            fail();
        } catch (Exception e) {
            assertEquals("Client must be the same as the one in the reservation", e.getMessage());
        }
    }

    @Test
    public void naoDeveRealizarCheckOutReservation() {
        try {
            var reservation = client.getReservation();
            secretary.cancelReservation(reservation, client);
            secretary.checkClientOut(client, reservation);
            fail();
        } catch (Exception e) {
            assertEquals("Client must be checked in to check out", e.getMessage());
        }
    }

    @Test
    public void naoDeveRealizarCheckOutClienteSemCreditos() {
        try {
            var reservation = client.getReservation();
            client.setCredit(0);
            secretary.checkClientOut(client, reservation);
            fail();
        } catch (Exception e) {
            assertEquals("Client must have enough money to pay for the reservation", e.getMessage());
        }
    }
}

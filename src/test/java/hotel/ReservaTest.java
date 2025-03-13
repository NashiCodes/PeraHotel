package hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.perahotel.hotel.states.reservation.Confirmed;
import org.perahotel.hotel.states.room.Reserved;
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

public class ReservaTest {
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
    }


    @Test
    public void deveRetornarErroPagamento() {
        try {
            var reservation = client.getReservation();
            secretary.reserve(reservation, client);
            fail();
        } catch (Exception e) {
            assertEquals("Client must have enough money to pay for the reservation", e.getMessage());
        }
    }

    @Test
    public void deveRetornarErroPagamento2() {
        try {
            var reservation = client.getReservation();
            secretary.reserve(reservation, client);
            client.setCredit(100);
            fail();
        } catch (Exception e) {
            assertEquals("Client must have enough money to pay for the reservation", e.getMessage());
        }
    }

    @Test
    public void deveReservarComSuccesso() {
        try {
            var reservation = client.getReservation();
            var amount = reservation.Cost();
            client.addCredit(amount);
            secretary.reserve(reservation, client);
            assert reservation.getState() == Confirmed.getInstance();
            assert reservation.getRoom().getState() == Reserved.getInstance();
            assert secretary.getReservationsMade() == 1;
            assert manager.getReservations() == 1;
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
    public void deveReservarComSucessoReservaCustomizada() {
        try {
            secretary.createReservation(client, 10);
            secretary.addAllMeals(client);
            var reservation = client.getReservation();
            var amount = reservation.Cost();
            client.addCredit(amount);
            secretary.reserve(reservation, client);
            assert reservation.getState() == Confirmed.getInstance();
            assert reservation.getRoom().getState() == Reserved.getInstance();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deveReservarQuartoCustomizado() {
        try {
            secretary.createReservation(client, 10);
            secretary.addAllRoomFeatures(client);
            var reservation = client.getReservation();
            var amount = reservation.Cost();
            client.addCredit(amount);
            secretary.reserve(reservation, client);
            assert reservation.getState() == Confirmed.getInstance();
            assert reservation.getRoom().getState() == Reserved.getInstance();

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deveReservarQuartoEReservaCustomizado() {
        try {
            secretary.createReservation(client, 10);
            secretary.addAllRoomFeatures(client);
            secretary.addAllMeals(client);
            var reservation = client.getReservation();
            var amount = reservation.Cost();
            client.addCredit(amount);
            secretary.reserve(reservation, client);
            assert reservation.getState() == Confirmed.getInstance();
            assert reservation.getRoom().getState() == Reserved.getInstance();

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deveCalcularSalarioCorretamente() {
        try {
            var reservation = client.getReservation();
            var amount = reservation.Cost();
            client.addCredit(amount);
            secretary.reserve(reservation, client);
            assertEquals(2040.0, secretary.getSalary());
            assertEquals(1500.0, janitor.getSalary());
            assertEquals(3060.0, manager.getSalary());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deveRetornarErroReservaJaFeita() {
        try {
            var client2 = new ClientBuilder("João", "teste@gmail", "123456").build();
            var secretary2 = new SecretaryFactory().create("Secretary");
            secretary2.setNext(janitor);

            secretary.createReservation(client2, 10);
            var reservation = client.getReservation();
            var reservation2 = client2.getReservation();

            var amount = reservation.Cost();
            client.addCredit(amount);
            amount = reservation2.Cost();
            client2.addCredit(amount);

            secretary.reserve(reservation, client);
            secretary2.reserve(reservation2, client2);
        } catch (Exception e) {
            assertEquals("Someone already reserved this room", e.getMessage());
        }
    }

    @Test
    public void deveCalcularSalarioCorretamenteMultiplasReservas() {
        try {
            var reservation = client.getReservation();
            var amount = reservation.Cost();
            client.addCredit(amount);
            secretary.reserve(reservation, client);

            for (int i = 0; i < 9; i++) {
                var client2 = new ClientBuilder("João", "teste@gmail", "123456").build();
                var reservation2 = secretary.createReservation(client2, 10);
                var amount2 = reservation2.Cost();
                client2.addCredit(amount2);
                secretary.reserve(reservation2, client2);
            }
            assertEquals(10, secretary.getReservationsMade());

            assertEquals(2400.0, secretary.getSalary());
            assertEquals(1500.0, janitor.getSalary());
            assertEquals(3600.0, manager.getSalary());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deveCalcularSalarioCorretamenteMultiplasSecretarias() {
        try {
            var reservation = client.getReservation();
            var amount = reservation.Cost();
            client.addCredit(amount);
            secretary.reserve(reservation, client);

            for (int i = 0; i < 9; i++) {
                var clientI = new ClientBuilder("João", "teste@gmail", "123456").build();
                var secretaryI = new SecretaryFactory().create("Secretary");
                secretaryI.setNext(janitor);
                secretaryI.createReservation(clientI, 10);
                var reservationI = clientI.getReservation();
                var amountI = reservationI.Cost();
                clientI.addCredit(amountI);
                secretaryI.reserve(reservationI, clientI);

                assertEquals(1, secretaryI.getReservationsMade());
                assertEquals(2040.0, secretaryI.getSalary());

            }

            assertEquals(3600.0, manager.getSalary());
            assertEquals(10, manager.getReservations());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}

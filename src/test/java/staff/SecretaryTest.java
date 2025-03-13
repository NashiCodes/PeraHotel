package staff;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.perahotel.hotel.decorators.reservation.ReservationDecorator;
import org.perahotel.hotel.decorators.room.RoomDecorator;
import org.perahotel.hotel.states.reservation.InProgress;
import org.perahotel.models.Client;
import org.perahotel.models.builders.ClientBuilder;
import org.perahotel.staff.Janitor;
import org.perahotel.staff.Manager;
import org.perahotel.staff.Secretary;
import org.perahotel.staff.factories.JanitorFactory;
import org.perahotel.staff.factories.ManagerFactory;
import org.perahotel.staff.factories.SecretaryFactory;

import static org.junit.jupiter.api.Assertions.*;

public class SecretaryTest {
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
    }

    @Test
    public void DeveCriarReserva() {
        try {
            var reservation = secretary.createReservation(client, 10);
            assertFalse(reservation instanceof ReservationDecorator);
            assertEquals(client.getId(), reservation.getGuestId());
            assertEquals(client.getReservation(), reservation);
            reservation = client.getReservation();
            assertEquals(reservation.getState(), InProgress.getInstance());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void DeveCriarReservaCustomizada() {
        try {
            secretary.createReservation(client, 10);
            secretary.addAllMeals(client);
            assert client.getReservation() instanceof ReservationDecorator;
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void DeveCriarReservaComQuartoCustomizado() {
        try {
            secretary.createReservation(client, 10);
            secretary.addAllRoomFeatures(client);
            var room = client.getReservation().getRoom();
            assert room instanceof RoomDecorator;
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void DeveCriarReservaComQuartoCustomizadoEComidas() {
        try {
            secretary.createReservation(client, 10);
            secretary.addAllRoomFeatures(client);
            secretary.addAllMeals(client);
            var room = client.getReservation().getRoom();
            assertInstanceOf(RoomDecorator.class, room);
            assert client.getReservation() instanceof ReservationDecorator;
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void DeveCriarReservaCustomizada2() {
        try {
            secretary.createReservation(client, 10);
            secretary.addBreakfast(client);
            secretary.addLunch(client);
            secretary.addDinner(client);
            secretary.addSnacks(client);
            assert client.getReservation() instanceof ReservationDecorator;
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void DeveCriarReservaComQuartoCustomizado2() {
        try {
            secretary.createReservation(client, 10);
            secretary.addWifi(client);
            secretary.addKingSizeBed(client);
            secretary.addDoubleBathroom(client);
            secretary.addBalcony(client);
            secretary.addDoubleSingleBed(client);
            var room = client.getReservation().getRoom();
            assert room instanceof RoomDecorator;
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void DeveCriarReservaComQuartoCustomizadoEComidas2() {
        try {
            secretary.createReservation(client, 10);
            secretary.addBreakfast(client);
            secretary.addLunch(client);
            secretary.addDinner(client);
            secretary.addSnacks(client);
            secretary.addWifi(client);
            secretary.addKingSizeBed(client);
            secretary.addDoubleBathroom(client);
            secretary.addBalcony(client);
            secretary.addDoubleSingleBed(client);
            assert client.getReservation().getRoom() instanceof RoomDecorator;
            assert client.getReservation() instanceof ReservationDecorator;
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}

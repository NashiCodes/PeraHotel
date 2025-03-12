import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.perahotel.hotel.decorators.reservation.BreakfastIncluded;
import org.perahotel.hotel.decorators.reservation.DinnerIncluded;
import org.perahotel.hotel.decorators.reservation.LunchIncluded;
import org.perahotel.hotel.decorators.reservation.SnacksIncluded;
import org.perahotel.models.Client;
import org.perahotel.models.builders.ClientBuilder;
import org.perahotel.staff.Janitor;
import org.perahotel.staff.Manager;
import org.perahotel.staff.Secretary;
import org.perahotel.staff.factories.JanitorFactory;
import org.perahotel.staff.factories.ManagerFactory;
import org.perahotel.staff.factories.SecretaryFactory;

import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {
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
    }

    @Test
    public void deveRetornarReservaComum() {
        try {
            var reservation = secretary.createReservation(client, 10);
            assertEquals(client.getId(), reservation.getGuestId());
            assertFalse(reservation instanceof BreakfastIncluded);
            assertFalse(reservation instanceof DinnerIncluded);
            assertFalse(reservation instanceof LunchIncluded);
            assertFalse(reservation instanceof SnacksIncluded);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}

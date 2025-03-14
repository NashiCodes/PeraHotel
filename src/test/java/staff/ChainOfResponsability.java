package staff;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.perahotel.models.Client;
import org.perahotel.models.builders.ClientBuilder;
import org.perahotel.staff.Janitor;
import org.perahotel.staff.Manager;
import org.perahotel.staff.Secretary;
import org.perahotel.staff.factories.JanitorFactory;
import org.perahotel.staff.factories.ManagerFactory;
import org.perahotel.staff.factories.SecretaryFactory;
import org.perahotel.staff.requests.ReservationMade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Restante dos testes estão sendo feitos em conjunto com
 * outros testes, pois Padrão está sendo utilizado em
 * conjunto com outras classes.
 */
public class ChainOfResponsability {
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
    public void DeveAdicionarBonusSecretariaEManager() {
        try {
            secretary.requestHandler(ReservationMade.getInstance(), client.getReservation());
            assertEquals(2040.0, secretary.getSalary());
            assertEquals(3060.0, manager.getSalary());
            assertEquals(1, secretary.getReservationsMade());
            assertEquals(1, manager.getReservations());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void DeveAdicionarBonusJanitor() {
        try {
            client.setCredit(100000);
            secretary.reserve(client.getReservation(), client);
            secretary.checkClientIn(client, client.getReservation());
            secretary.checkClientOut(client, client.getReservation());
            assertEquals(1520.0, janitor.getSalary());
            assertEquals(1, janitor.getRoomsCleaned());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}

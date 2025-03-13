package hotel;

import org.junit.jupiter.api.BeforeEach;
import org.perahotel.models.Client;
import org.perahotel.models.builders.ClientBuilder;
import org.perahotel.staff.Janitor;
import org.perahotel.staff.Manager;
import org.perahotel.staff.Secretary;
import org.perahotel.staff.factories.JanitorFactory;
import org.perahotel.staff.factories.ManagerFactory;
import org.perahotel.staff.factories.SecretaryFactory;

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
}

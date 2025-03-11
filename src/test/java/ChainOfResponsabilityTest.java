import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.perahotel.staff.Janitor;
import org.perahotel.staff.Manager;
import org.perahotel.staff.Secretary;
import org.perahotel.staff.requests.NewFranchise;
import org.perahotel.staff.requests.Robbery;
import org.perahotel.staff.requests.ServicesInformation;
import org.perahotel.staff.requests.WaterLeakage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChainOfResponsabilityTest {
    private final Janitor janitor = new Janitor();
    private final Manager manager = new Manager();
    private final Secretary secretary = new Secretary();

    @BeforeEach
    public void setUp() {
        janitor.setNext(manager);
        secretary.setNext(janitor);
    }

    @Test
    public void DeveRetornarSecretaria() {
        var message = secretary.requestHandler(ServicesInformation.getInstance());
        assert message != null;
        assertEquals("Secretary will handle this request", message);
    }

    @Test
    public void DeveRetornarGerente() {
        var message = secretary.requestHandler(NewFranchise.getInstance());
        assert message != null;
        assertEquals("Manager will handle this request", message);
    }

    @Test
    public void DeveRetornarJanitor() {
        var message = secretary.requestHandler(WaterLeakage.getInstance());
        assert message != null;
        assertEquals("Janitor will handle this request", message);
    }

    @Test
    public void DeveRetornarException() {
        var message = secretary.requestHandler(Robbery.getInstance());
        assertEquals("No employee can handle this request", message);
    }
}

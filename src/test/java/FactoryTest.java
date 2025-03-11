import org.junit.jupiter.api.Test;
import org.perahotel.staff.factories.JanitorFactory;
import org.perahotel.staff.factories.ManagerFactory;
import org.perahotel.staff.factories.SecretaryFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class FactoryTest {
    public static JanitorFactory janitorFactory = new JanitorFactory();
    public static ManagerFactory managerFactory = new ManagerFactory();
    public static SecretaryFactory secretaryFactory = new SecretaryFactory();

    @Test
    public void DeveCriarJanitor() {
        try {
            var janitor = janitorFactory.create("Teste");
            assertEquals("Teste", janitor.getName());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void DeveCriarManager() {
        try {
            var manager = managerFactory.create("Teste");
            assertEquals("Teste", manager.getName());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void DeveCriarSecretary() {
        try {
            var secretary = secretaryFactory.create("Teste");
            assertEquals("Teste", secretary.getName());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void DeveRetornarExcecaoNomeVazioJanitor() {
        try {
            janitorFactory.create("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be empty", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExcecaoNomeVazioManager() {
        try {
            managerFactory.create("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be empty", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExcecaoNomeVazioSecretary() {
        try {
            secretaryFactory.create("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be empty", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExcecaoNomeBrancoJanitor() {
        try {
            janitorFactory.create("  ");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be empty", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExcecaoNomeBrancoManager() {
        try {
            managerFactory.create("  ");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be empty", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExcecaoNomeBrancoSecretary() {
        try {
            secretaryFactory.create("  ");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be empty", e.getMessage());
        }
    }

}

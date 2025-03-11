import org.junit.jupiter.api.Test;
import org.perahotel.models.builders.ClientBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ClientBuilderTest {


    @Test
    public void DeveRetornarCliente() {
        try {
            ClientBuilder clientBuilder = new ClientBuilder();
            var client = clientBuilder.setName("João").setEmail("teste@gmail").setPhone("123456").build();
            assertEquals("João", client.getName());
            assertEquals("teste@gmail", client.getEmail());
            assertEquals("123456", client.getPhone());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void DeveRetornarClienteSetAll() {
        try {
            var client = new ClientBuilder("João", "teste@gmail", "123456").build();
            assertEquals("João", client.getName());
            assertEquals("teste@gmail", client.getEmail());
            assertEquals("123456", client.getPhone());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void DeveRetornarExcessaoNomeVazio() {
        try {
            ClientBuilder clientBuilder = new ClientBuilder();
            var client = clientBuilder.setName("").setEmail("teste@gmail").setPhone("123456").build();
            fail("Deveria ter lançado uma exceção");
        } catch (Exception e) {
            assertEquals("Name is required", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExcessaoNomeBranco() {
        try {
            ClientBuilder clientBuilder = new ClientBuilder();
            var client = clientBuilder.setName(" ").setEmail("teste@gmail").setPhone("123456").build();
            fail("Deveria ter lançado uma exceção");
        } catch (Exception e) {
            assertEquals("Name is required", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExcessaoEmailVazio() {
        try {
            ClientBuilder clientBuilder = new ClientBuilder();
            var client = clientBuilder.setName("João").setEmail("").setPhone("123456").build();
            fail("Deveria ter lançado uma exceção");
        } catch (Exception e) {
            assertEquals("Email is required", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExcessaoEmailBranco() {
        try {
            ClientBuilder clientBuilder = new ClientBuilder();
            var client = clientBuilder.setName("João").setEmail(" ").setPhone("123456").build();
            fail("Deveria ter lançado uma exceção");
        } catch (Exception e) {
            assertEquals("Email is required", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExcessaoPhoneVazio() {
        try {
            ClientBuilder clientBuilder = new ClientBuilder();
            var client = clientBuilder.setName("João").setEmail("teste@gmail").setPhone("").build();
            fail("Deveria ter lançado uma exceção");
        } catch (Exception e) {
            assertEquals("Phone is required", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExcessaoPhoneBranco() {
        try {
            ClientBuilder clientBuilder = new ClientBuilder();
            var client = clientBuilder.setName("João").setEmail("teste@gmail").setPhone(" ").build();
            fail("Deveria ter lançado uma exceção");
        } catch (Exception e) {
            assertEquals("Phone is required", e.getMessage());
        }
    }

}

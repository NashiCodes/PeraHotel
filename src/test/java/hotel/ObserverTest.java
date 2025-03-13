package hotel;

import org.junit.jupiter.api.Test;
import org.perahotel.models.Hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObserverTest {

    @Test
    public void deveAtualizarPrecos() {
        var rooms = Hotel.getInstance().getRooms();
        var newPrice = 250.0;
        Hotel.getInstance().updateBaseRoomsPrice(newPrice);

        for (var room : rooms) {
            assertEquals(newPrice, room.Cost());
            assertEquals("[ALERT] : Price updated to 250.0", room.getLastMessage());
        }
    }
}

import org.junit.jupiter.api.Test;
import org.perahotel.hotel.states.room.Available;
import org.perahotel.hotel.states.room.Maintenance;
import org.perahotel.hotel.states.room.Occupied;
import org.perahotel.hotel.states.room.Reserved;
import org.perahotel.models.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RoomStateTest {
    private Room room;
    
    @Test
    public void DeveRetornarRoomComArgEPrecoEReservada() {
        try {
            room = new Room("101");
            room.setPrice(200);
            room.reserve();
            assertEquals("Room 101 - Price: 200.0\n", room.Description());
            assertEquals(Reserved.getInstance(), room.getState());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void DeveRetornarRExceçãoReservadaEReservar() {
        try {
            room = new Room("101");
            room.setPrice(200);
            room.reserve();
            room.reserve();

            fail();
        } catch (Exception e) {
            assertEquals("Room is reserved and cannot be reserved", e.getMessage());
            assertEquals(Reserved.getInstance(), room.getState());
        }
    }

    @Test
    public void DeveRetornarRExceçãoManutençãoEReservar() {
        try {
            room = new Room("101");
            room.setPrice(200);
            room.maintenance();
            room.reserve();

            fail();
        } catch (Exception e) {
            assertEquals("Room is under maintenance and cannot be reserved", e.getMessage());
            assertEquals(Maintenance.getInstance(), room.getState());
        }
    }

    @Test
    public void DeveRetornarRExceçãoOcupadaEReservar() {
        try {
            room = new Room("101");
            room.setPrice(200);
            room.occupy();
            room.reserve();

            fail();
        } catch (Exception e) {
            assertEquals("Room is occupied and cannot be reserved", e.getMessage());
            assertEquals(Occupied.getInstance(), room.getState());
        }
    }

    @Test
    public void DeveRetornarRExceçãoManutençãoEManutenção() {
        try {
            room = new Room("101");
            room.setPrice(200);
            room.maintenance();
            room.maintenance();

            fail();
        } catch (Exception e) {
            assertEquals("Room is under maintenance and cannot be maintenance", e.getMessage());
            assertEquals(Maintenance.getInstance(), room.getState());
        }
    }

    @Test
    public void DeveRetornarROcupadaEManutenção() {
        try {
            room = new Room("101");
            room.setPrice(200);
            room.occupy();
            room.maintenance();
            assertEquals(Maintenance.getInstance(), room.getState());

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void DeveRetornarOcupadaEDisponivel() {
        try {
            room = new Room("101");
            room.setPrice(200);
            room.occupy();
            room.available();

            assertEquals(Available.getInstance(), room.getState());

        } catch (Exception e) {
            fail();
        }
    }
}

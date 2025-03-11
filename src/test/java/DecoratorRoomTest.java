import org.junit.jupiter.api.Test;
import org.perahotel.hotel.decorators.room.*;
import org.perahotel.models.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DecoratorRoomTest {
    private Room room;

    @Test
    public void DeveRetornarRoom() {
        try {
            room = new Room();
            assertEquals("Room \n", room.Description());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void DeveRetornarRoomComArg() {
        try {
            room = new Room("101");
            assertEquals("Room 101\n", room.Description());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void DeveRetornarRoomComWiFi() {
        try {
            room = new Room("101");
            room = new WifiRoom(room);
            assertEquals("Room 101\nwith Wifi\n", room.Description());
            assertEquals(105, room.Cost());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void DeveRetornarRoomComKingSizeBed() {
        try {
            room = new Room("101");
            room = new KingSize(room);
            assertEquals("Room 101\nwith a king size bed\n", room.Description());
            assertEquals(120, room.Cost());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void DeveRetornarRoomComDubleSingleBed() {
        try {
            room = new Room("101");
            room = new DoubleSingleBed(room);
            assertEquals("Room 101\nwith double single bed\n", room.Description());
            assertEquals(110, room.Cost());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void DeveRetornarRoomComDubleBathroom() {
        try {
            room = new Room("101");
            room = new DoubleBathroom(room);
            assertEquals("Room 101\nwith two bathroom\n", room.Description());
            assertEquals(120, room.Cost());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void DeveRetornarRoomComBalcony() {
        try {
            room = new Room("101");
            room = new Balcony(room);
            assertEquals("Room 101\nwith balcony\n", room.Description());
            assertEquals(130, room.Cost());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void DeveRetornarRoomComWifiKingSizeBedDoubleSingleBedBalconyAndDoubleBathroom() {
        try {
            room = new Room("101");
            room = new WifiRoom(room);
            room = new KingSize(room);
            room = new DoubleSingleBed(room);
            room = new Balcony(room);
            room = new DoubleBathroom(room);
            assertEquals("""
                    Room 101
                    with Wifi
                    with a king size bed
                    with double single bed
                    with balcony
                    with two bathroom
                    """, room.Description());
            assertEquals(185, room.Cost());
        } catch (Exception e) {
            fail();
        }
    }

}

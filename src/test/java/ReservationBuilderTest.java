import org.junit.jupiter.api.Test;
import org.perahotel.models.Room;
import org.perahotel.models.builders.ReservationBuilder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReservationBuilderTest {

    @Test
    public void DeveRetornarReservation() {
        try {
            ReservationBuilder reservationBuilder = new ReservationBuilder();
            var room = new Room();
            var guestId = UUID.randomUUID();
            var days = 1;
            var reservation = reservationBuilder.setRoom(room)
                    .setGuestId(guestId)
                    .setDays(days)
                    .build();
            assertEquals(room, reservation.getRoom());
            assertEquals(guestId, reservation.getGuestId());
            assertEquals(days, reservation.getDays());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void DeveRetornarReservationSetAll() {
        try {
            var room = new Room();
            var guestId = UUID.randomUUID();
            var days = 1;
            var reservation = new ReservationBuilder(room, guestId, days).build();
            assertEquals(room, reservation.getRoom());
            assertEquals(guestId, reservation.getGuestId());
            assertEquals(days, reservation.getDays());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void DeveRetornarException() {
        try {
            ReservationBuilder reservationBuilder = new ReservationBuilder();
            reservationBuilder.build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Room is required", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExceptionGuest() {
        try {
            ReservationBuilder reservationBuilder = new ReservationBuilder();
            var room = new Room();
            reservationBuilder.setRoom(room).build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Guest is required", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExceptionDays() {
        try {
            ReservationBuilder reservationBuilder = new ReservationBuilder();
            var room = new Room();
            var guestId = UUID.randomUUID();
            reservationBuilder.setRoom(room).setGuestId(guestId).build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Days must be greater than 0", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExceptionDaysGreaterThan30() {
        try {
            ReservationBuilder reservationBuilder = new ReservationBuilder();
            var room = new Room();
            var guestId = UUID.randomUUID();
            var days = 31;
            reservationBuilder.setRoom(room).setGuestId(guestId).setDays(days).build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("You can't reserve a room for more than 30 days", e.getMessage());
        }
    }

    @Test
    public void DeveRetornarExceptionDaysLessThan0() {
        try {
            ReservationBuilder reservationBuilder = new ReservationBuilder();
            var room = new Room();
            var guestId = UUID.randomUUID();
            var days = -1;
            reservationBuilder.setRoom(room).setGuestId(guestId).setDays(days).build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Days must be greater than 0", e.getMessage());
        }
    }
}

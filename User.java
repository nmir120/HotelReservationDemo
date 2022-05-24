import java.time.LocalDate;
import java.util.ArrayList;

public abstract class User {
    protected String username;
    protected String name;
    protected int userId;

    public Room checkRoomAvailability(ArrayList<Room> rooms, String roomType, LocalDate startDate, int numDaysToStay) {
        // return roomNum of first available room of given type
        for (Room r : rooms) {
            if (r.getType().equals(roomType)) {
                if (r.isAvailable(startDate, numDaysToStay)) {
                    return r;
                }
            }
        }
        return null;
    }

    public boolean cancelReservation(int reservationId) {
        return true;
    }

}

import java.time.LocalDate;
import java.util.HashMap;

public class HotelAdmin extends User {
    public HotelAdmin(String username, String name, int userId) {
        this.username = username;
        this.name = name;
        this.userId = userId;
    }

    public void updateRoomAvailability(Reservation reservation) {
        // confirm pass by reference
        HashMap<LocalDate, Boolean> availability = reservation.getRoom().getAvailability();
        LocalDate startDate = reservation.getStartDate();
        for (int i = 0; i < reservation.getNumDaysToStay(); i++) {
            availability.put(startDate.plusDays(i), false);
        }
    }
}

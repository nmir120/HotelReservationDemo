import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class HotelAdmin extends User {
    private ArrayList<Customer> customersBooked;
    
    public HotelAdmin(String username, String name, int userId) {
        this.username = username;
        this.name = name;
        this.userId = userId;
        customersBooked = new ArrayList<Customer>();
    }

    public void updateRoomAvailability(Reservation reservation) {
        HashMap<LocalDate, Boolean> availability = reservation.getRoom().getAvailability();
        LocalDate startDate = reservation.getStartDate();
        for (int i = 0; i < reservation.getNumDaysToStay(); i++) {
            availability.put(startDate.plusDays(i), false);
        }
    }

    public void requestPayment() {
        return;
    }
}

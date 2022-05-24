import java.time.LocalDate;
import java.util.HashMap;

public class Room {
    private int roomId;
    private String roomType;
    private double dailyRate;
    private HashMap<LocalDate, Boolean> availability;

    public Room(int roomId, String roomType, float dailyRate) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.dailyRate = dailyRate;
        availability = new HashMap<LocalDate, Boolean>();
        // populate the remainder of 2022
        for (int i = 0; i <= 224; i++) {
            availability.put(LocalDate.now().plusDays(i), true);
        }
    }

    public Boolean isAvailable(LocalDate startDate, int numDaysToStay) {
        for (int i = 0; i <= numDaysToStay; i++) {
            if (availability.get(startDate.plusDays(i)) == false) return false;
        }
        return true;
    }

    public int getId() {
        return roomId;
    }

    public String getType() {
        return roomType;
    }

    public HashMap<LocalDate, Boolean> getAvailability() {
        return availability;
    }

    public double getDailyRate() {
        return dailyRate;
    }

}

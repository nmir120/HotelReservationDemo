import java.time.LocalDate;

public class Reservation {
    private int id;
    private String customerUsername;
    private Room room;
    private LocalDate startDate;
    private int numDaysToStay;
    private double totalCost;
    public Reservation(int id, String customerUsername, Room room, LocalDate startDate, int numDaysToStay) {
        this.id = id;
        this.customerUsername = customerUsername;
        this.room = room;
        this.startDate = startDate;
        this.numDaysToStay = numDaysToStay;
        totalCost =  room.getDailyRate() * numDaysToStay;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getNumDaysToStay() {
        return numDaysToStay;
    }

    public Room getRoom() {
        return room;
    }

    public double getTotalCost() {
        return totalCost;
    }
}

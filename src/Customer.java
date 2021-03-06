import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Reservation> reservations;
    private double balanceToPay;

    public Customer(String username, String name, int userId) {
        this.username = username;
        this.name = name;
        this.userId = userId;
        reservations = new ArrayList<Reservation>();
    }

    public Reservation makeReservation(Room room, LocalDate startDate, int numDaysToStay) {
        Reservation res = new Reservation(1, this.username, room, startDate, numDaysToStay);
        reservations.add(res);
        return res;
    }

    public Boolean makePayment() {
        return true;
    }

}

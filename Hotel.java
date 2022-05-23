import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Hotel {

    private String name;
    private int id;
    private ArrayList<Room> rooms;
    
    public Hotel(String name, int id, ArrayList<Room> rooms) {
        this.name = name;
        this.id = id;
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public static void main(String[] args) {
        // Login functionality would go here
        // Accounts would be stored and loaded from a database
        ArrayList<Room> rooms = new ArrayList<Room>();
        Collections.addAll(rooms, 
            new Room(1, "Single", 50),
            new Room(2, "Double", 70),
            new Room(3, "Triple", 90),
            new Room(4, "Quad", 100)
        );
        
        // Create a basic hotel object, with a few rooms
        Hotel hotel = new Hotel("Proofpoint Hotel", 1, rooms);
        // Accounts could be made with an AccountGenerator class - but for simplicity's sake I made them manually
        HotelAdmin admin = new HotelAdmin("admin123", "Amy Admin", 1);
        Customer c1 = new Customer("nmir120", "Numan Mir", 2);
        
        Scanner input = new Scanner(System.in);
        ArrayList<String> types = new ArrayList<String>(Arrays.asList("Single","Double","Triple","Quad"));
        while(true) {
            System.out.println("----Hotel Reservation System----");
            System.out.println("Please enter a number corresponding to one of the menu items:\n");
            System.out.println("1 - Check if a type of room is available for a specific date range");
            System.out.println("2 - Book a room for a specific date range");
            System.out.println("3 - See all rooms");
            System.out.println("(Provide any other input to quit the program)");
            String choice = input.nextLine();
            
            if (choice.equals("1") || choice.equals("2")) {
                System.out.println("Please enter the type of room from the following options:\n\tSingle, Double, Triple, or Quad");
                String type = input.nextLine();
                if (!types.contains(type)) {
                    System.err.println("Error: Invalid room type.");
                    break;
                }
                System.out.println("**Note: The date range must be between today and the end of 2022**");
                System.out.println("Please enter the beginning date in the following format: MM-DD");
                String[] date = input.nextLine().split("-");
                int month = Integer.parseInt(date[0]); 
                int day = Integer.parseInt(date[1]);

                System.out.println("Please enter the amount of days you would like to stay (beginning from the starting date you provided)");
                int numDaysToStay = input.nextInt();
                input.nextLine();
                LocalDate startDate = LocalDate.of(2022, month, day);
                Room room = c1.checkRoomAvailability(hotel.getRooms(), type, startDate, numDaysToStay);

                if (choice.equals("1")) {
                    if (room != null) {
                        System.out.println("Room " + room.getId() + " is of type " + room.getType() + " and is available from " + startDate + " to " + startDate.plusDays(numDaysToStay) + ".");
                        System.out.println("Would you like to book it? (y/n)");
                        choice = input.nextLine();
                        if (choice.equals("y")) {
                            Reservation res = c1.makeReservation(room, startDate, numDaysToStay);
                            // Admin will update room availability according to reservation
                            admin.updateRoomAvailability(res);
                            System.out.println("Success! Room " + res.getRoom().getId() + " has been booked from " + res.getStartDate() + " to " + res.getStartDate().plusDays(res.getNumDaysToStay()) + ".");
                            System.out.format("Total cost: $%.2f (daily rate: $%.2f)\n", res.getTotalCost(), room.getDailyRate());
                        }
                    } else {
                        System.out.println("No room of the given type and date range is available.");
                    }
                    System.out.println("Return to menu? (y/n)");
                    if (!input.nextLine().equals("y")) break;
                
                } else if (choice.equals("2")) {
                    Reservation res = c1.makeReservation(room, startDate, numDaysToStay);
                    if (res != null) {
                        // Admin will update room availability according to reservation
                        admin.updateRoomAvailability(res);
                        System.out.println("Success! Room " + res.getRoom().getId() + " has been booked from " + res.getStartDate() + " to " + res.getStartDate().plusDays(res.getNumDaysToStay()) + ".");
                        System.out.format("Total cost: $%.2f (daily rate: $%.2f)\n", res.getTotalCost(), room.getDailyRate());
                    } else {
                        System.out.println("The room you are trying to book is unavailable for the given date range.");
                    }
                    System.out.println("Return to menu? (y/n)");
                    if (!input.nextLine().equals("y")) break;
                }
            } else if (choice.equals("3")) {
                ArrayList<Room> allRooms = hotel.getRooms();
                for (Room r : allRooms) {
                    System.out.println("Room #" + r.getId() + ", type: " + r.getType() + ", daily rate: $" + String.format("%.2f", r.getDailyRate()));
                }
                System.out.println("Return to menu? (y/n)");
                if (!input.nextLine().equals("y")) break;
                
            } else {
                break;
            }
        }
    }
}
        // Code for debugging
        // Customer c2 = new Customer("jimbothy101", "Jim Bothy", 3);
        // LocalDate today = LocalDate.now();

        // Room availableRoom = c1.checkRoomAvailability(hotel.getRooms(), "Triple", today, 2);
        // if (availableRoom != null) {
        //     System.out.println("Room " + availableRoom.getId() + " is available to book for the provided date range.");
        // } else {
        //     System.out.println("The room you are searching for is not available for the given date range.");
        // }

        // // Book the available room
        // Reservation res = c1.makeReservation(availableRoom, today, 2);
        // if (res != null) {
        //     // Admin will update room availability according to reservation
        //     admin.updateRoomAvailability(res);
        //     System.out.println("Room " + res.getRoom().getId() + " has successfully been booked from " + res.getStartDate() + " to " + res.getStartDate().plusDays(res.getNumDaysToStay()) + ".");
        // } else {
        //     System.out.println("The room you are trying to book is unavailable for the given date range.");
        // }
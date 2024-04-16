package flight;

import model.Flight;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BookingController {
    private Map<Integer, User> users;
    private BookingInventory bookingInventory;
    private FlightInventory flightInventory;

    public BookingController(BookingInventory bookingInventory, FlightInventory flightInventory) {
        this.bookingInventory = bookingInventory;
        this.flightInventory = flightInventory;
        this.users = new HashMap<>();
        PaymentInfo paymentInfoJohn = new PaymentInfo("Visa", "4111111111111111", "12/23", "123");
        PaymentInfo paymentInfoJane = new PaymentInfo("MasterCard", "5500000000000004", "01/24", "321");
        users.put(1, new User(1, "John Doe", "john.doe@example.com", paymentInfoJohn, new ArrayList<>()));
        users.put(2, new User(2, "Jane Doe", "jane.doe@example.com", paymentInfoJane, new ArrayList<>()));
    }

    public void createBooking(int userID, List<Integer> flightIDs) {
        List<Flight> flightsToBook = flightIDs.stream()
                .map(id -> flightInventory.searchFlightByID(id))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (flightsToBook.isEmpty()) {
            System.out.println("No flights found with the given flight numbers.");
            return;
        }

        LocalDate bookingDate = LocalDate.now(); // Current date as booking date
        int bookingID = bookingInventory.generateBookingID(); // Generate a unique booking ID

        Booking newBooking = new Booking(bookingID, userID, bookingDate, flightsToBook);
        bookingInventory.addBooking(newBooking);

        // Decrement the available seats for each booked flight
        flightsToBook.forEach(flight -> {
            boolean seatBooked = flight.bookSeat();
            if (!seatBooked) {
                System.out.println("Unable to book seat on flight ID: " + flight.getFlightID() + " due to no available seats.");
            }
        });

        System.out.println("Booking created with Booking ID: " + bookingID);
    }


    public void listAllBookings() {
        List<Booking> allBookings = bookingInventory.getAllBookings();
        if (allBookings.isEmpty()) {
            System.out.println("Engar bókanir fundust.");
        } else {
            allBookings.forEach(System.out::println);
        }
    }

    public List<Booking> searchBookingsByUserID(int userID) {
        List<Booking> userBookings = bookingInventory.getBookingsByUserID(userID);
        if (userBookings.isEmpty()) {
            System.out.println("Engar bókanir fundust fyrir notanda með ID: " + userID);
            return userBookings;
        } else {
            return userBookings;
        }
    }

    public void cancelBooking(int bookingID) {
        boolean wasCancelled = bookingInventory.removeBooking(bookingID);
        if (wasCancelled) {
            System.out.println("Bókun með ID " + bookingID + " var aflýst.");
        } else {
            System.out.println("Engin bókun fannst með ID " + bookingID);
        }
    }

    public void showBookingDetails(int bookingID) {
        Booking booking = bookingInventory.getBookingByID(bookingID);
        if (booking != null) {
            System.out.println(booking);
        } else {
            System.out.println("Engin bókun fannst með ID " + bookingID);
        }
    }
}


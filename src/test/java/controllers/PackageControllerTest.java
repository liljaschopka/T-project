package controllers;

import Mock_objects.Hotel;
import Mock_objects.HotelControllerEmptyMock;
import Mock_objects.HotelControllerInterface;
import Mock_objects.HotelControllerListMock;
import model.PaymentInfo;
import model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PackageControllerTest {


    @Test
    public void testHotelListEmpty() {
        HotelControllerInterface hotelControllerMockEmpty = new HotelControllerEmptyMock();
        User john = new User("John Doe", "john@hi.is", new PaymentInfo("12345678", "12345678", "12345678", "12345678"), null);
        LocalDateTime checkIn = LocalDateTime.of(2024, 6, 30, 12, 00);
        LocalDateTime checkOut = LocalDateTime.of(2024, 7, 5, 12, 00);
        PackageController packageController = new PackageController(john, "Reykjavík", "Akureyri", checkIn, checkOut, 4, hotelControllerMockEmpty, null, null, null);

        assertThrows(IllegalArgumentException.class, packageController::findAvailableHotels);

    }

    @Test
    public void testHotelListNotEmpty() {
        HotelControllerInterface hotelControllerMockNotEmpty = new HotelControllerListMock();
        User john = new User("John Doe", "john@hi.is", new PaymentInfo("12345678", "12345678", "12345678", "12345678"), null);
        // these dates are valid
        LocalDateTime checkIn = LocalDateTime.of(2024, 6, 30, 12, 00);
        LocalDateTime checkOut = LocalDateTime.of(2024, 7, 5, 12, 00);
        PackageController packageController = new PackageController(john, "Reykjavík", "Akureyri", checkIn, checkOut, 4, hotelControllerMockNotEmpty, null, null, null);
        List<Hotel> hotels = packageController.findAvailableHotels();

        assertEquals(4, hotels.size(), "Hotel list size should be 4");
    }


    @Test
    public void testIncorrectDates() {
        HotelControllerInterface hotelControllerMock = new HotelControllerListMock();
        User john = new User("John Doe", "john@hi.is", new PaymentInfo("12345678", "12345678", "12345678", "12345678"), null);
        // these dates are not valid
        LocalDate checkIn = LocalDate.of(2024, 6, 30);
        LocalDate checkOut = LocalDate.of(2024, 6, 29);
        PackageController packageController = new PackageController(john, "Reykjavík", "Akureyri", checkIn, checkOut, 4);
        List<Hotel> hotels = packageController.findAvailableHotels(hotelControllerMock); // should throw an exception with availableHotels

        // assertThrows(IllegalArgumentException.class, packageController.findAvailableHotels(hotelControllerMock));
    }

    @Test
    public void testIncorrectODP() {
        HotelControllerInterface hotelControllerMock = new HotelControllerListMock();
        User john = new User("John Doe", "john@hi.is", new PaymentInfo("12345678", "12345678", "12345678", "12345678"), null);
        LocalDateTime checkIn = LocalDateTime.of(2024, 6, 30, 12, 00);
        LocalDateTime checkOut = LocalDateTime.of(2024, 7, 5, 12, 00);
        // persons = 0 -> IllegalArgumentException
        PackageController packageController = new PackageController(john, "Reykjavík", "Akureyri", checkIn, checkOut, 0, hotelControllerMock, null, null, null);

        assertThrows(IllegalArgumentException.class, packageController::findAvailableHotels);
    }

    @Test
    public void testCorrectODP() {
        HotelControllerInterface hotelControllerMock = new HotelControllerListMock();
        User john = new User("John Doe", "john@hi.is", new PaymentInfo("12345678", "12345678", "12345678", "12345678"), null);
        LocalDateTime checkIn = LocalDateTime.of(2024, 6, 30, 12, 00);
        LocalDateTime checkOut = LocalDateTime.of(2024, 7, 5, 12, 00);
        PackageController packageController = new PackageController(john, "Reykjavík", "Akureyri", checkIn, checkOut, 5, hotelControllerMock, null, null, null);
        int persons = packageController.getPersons();

        assertEquals(5, persons, "Amount of persons should be 5");
    }
}

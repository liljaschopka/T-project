package controllers;

import model.Hotel;
import model.PaymentInfo;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PackageControllerTest {

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testHotelListEmpty() {
        HotelControllerInterface hotelControllerMock = new HotelControllerEmptyMock();
        User john = new User("John Doe", "john@hi.is", new PaymentInfo("12345678", "12345678", "12345678", "12345678"), null);
        LocalDateTime checkIn = LocalDateTime.of(2024, 6, 30, 12, 00);
        LocalDateTime checkOut = LocalDateTime.of(2024, 7, 5, 12, 00);
        PackageController packageController = new PackageController(john, "Reykjavík", "Akureyri", checkIn, checkOut, 4, hotelControllerMock, null, null, null);

        assertThrows(IllegalArgumentException.class, () -> {
            packageController.findAvailableHotels();
        });

    }

    @Test
    public void testHotelListNotEmpty() {
        HotelControllerInterface hotelControllerMock = new HotelControllerListMock();
        User john = new User("John Doe", "john@hi.is", new PaymentInfo("12345678", "12345678", "12345678", "12345678"), null);
        // these dates are valid
        LocalDateTime checkIn = LocalDateTime.of(2024, 6, 30, 12, 00);
        LocalDateTime checkOut = LocalDateTime.of(2024, 7, 5, 12, 00);
        PackageController packageController = new PackageController(john, "Reykjavík", "Akureyri", checkIn, checkOut, 4, hotelControllerMock, null, null, null);
        List<Hotel> hotels = packageController.findAvailableHotels();
        
        assertEquals(4, hotels.size(), "Hotel list size should be 4");
        assertTrue(hotels.get(0).getPrice() <= hotels.get(1).getPrice(), "Hotels should be sorted by price in ascending order");
        assertTrue(hotels.get(1).getPrice() <= hotels.get(2).getPrice(), "Hotels should be sorted by price in ascending order");
        assertTrue(hotels.get(2).getPrice() <= hotels.get(3).getPrice(), "Hotels should be sorted by price in ascending order");
    }


    @Test
    public void testIncorrectDates() {
        HotelControllerInterface hotelControllerMock = new HotelControllerListMock();
        User john = new User("John Doe", "john@hi.is", new PaymentInfo("12345678", "12345678", "12345678", "12345678"), null);
        // these dates are not valid
        LocalDateTime checkIn = LocalDateTime.of(2024, 6, 30, 12, 00);
        LocalDateTime checkOut = LocalDateTime.of(2024, 6, 29, 12, 00);
        PackageController packageController = new PackageController(john, "Reykjavík", "Akureyri", checkIn, checkOut, 4, hotelControllerMock, null, null, null);

        assertThrows(IllegalArgumentException.class, () -> {
            packageController.findAvailableHotels();
        });
    }

    @Test
    public void testCorrectODP() {
        HotelControllerInterface hotelControllerMock = new HotelControllerListMock();
        User john = new User("John Doe", "john@hi.is", new PaymentInfo("12345678", "12345678", "12345678", "12345678"), null);
        LocalDateTime checkIn = LocalDateTime.of(2024, 6, 30, 12, 00);
        LocalDateTime checkOut = LocalDateTime.of(2024, 7, 5, 12, 00);
        // persons = 0 -> IllegalArgumentException
        PackageController packageController = new PackageController(john, "Reykjavík", "Akureyri", checkIn, checkOut, 0, hotelControllerMock, null, null, null);

        assertThrows(IllegalArgumentException.class, () -> {
            packageController.findAvailableHotels();
        });
    }


}

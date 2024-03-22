package controllers;

import model.Booking;
import model.Cart;
import model.User;

import java.util.List;

/******************************************************************************
 *  Nafn    : Lilja Kolbrún Schopka
 *  T-póstur: lks17@hi.is
 *
 *  Lýsing  :
 *
 *
 *
 *
 *****************************************************************************/
public class BookingController {

    private User user;
    private Cart cart;

    public BookingController() {
        // this.user = PackageController.getUser();
        // this.cart = PackageController.getCart();
    }

    public void createBooking(User user, Cart cart) {

    }

    public List<Booking> findBookings(User user) {
        return null;
    }

    public void cancelBooking(String bookingID) {

    }

    public static void main(String[] args) {

    }
}

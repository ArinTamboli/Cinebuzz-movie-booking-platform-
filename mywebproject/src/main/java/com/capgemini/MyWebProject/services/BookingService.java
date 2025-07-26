package com.capgemini.MyWebProject.services;

import java.util.List;
import com.capgemini.MyWebProject.entities.Bookings;

public interface BookingService {
	List<Bookings> getAllBookings();

	Bookings getBookingById(Long id);

	Bookings createBooking(Bookings booking);

	Bookings updateBooking(Long id, Bookings booking);

	void deleteBooking(Long id);
}

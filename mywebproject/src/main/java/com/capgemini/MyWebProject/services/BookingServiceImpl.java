package com.capgemini.MyWebProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.MyWebProject.entities.Bookings;
import com.capgemini.MyWebProject.exceptions.BookingNotFoundException;
import com.capgemini.MyWebProject.repositories.BookingRepository;

import jakarta.validation.Valid;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

	private final BookingRepository repository;

	@Autowired
	public BookingServiceImpl(BookingRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Bookings> getAllBookings() {
		return repository.findAll();
	}

	@Override
	public Bookings getBookingById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));
	}

	@Override
	public Bookings createBooking(@Valid Bookings booking) {
		return repository.save(booking);
	}

	@Override
	public Bookings updateBooking(Long id, @Valid Bookings updated) {
		Bookings existing = repository.findById(id)
				.orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));
		existing.setUserID(updated.getUserID());
		existing.setShowID(updated.getShowID());
		existing.setSeatsBooked(updated.getSeatsBooked());
		existing.setBookingDate(updated.getBookingDate());
		existing.setAmount(updated.getAmount());
		return repository.save(existing);
	}

	@Override
	public void deleteBooking(Long id) {
		if (!repository.existsById(id)) {
			throw new BookingNotFoundException("Cannot delete. Booking not found with ID: " + id);
		}
		repository.deleteById(id);
	}
}


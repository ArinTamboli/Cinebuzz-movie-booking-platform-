package com.capgemini.MyWebProject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.MyWebProject.entities.Showtimes;
import com.capgemini.MyWebProject.exceptions.ShowtimeNotFoundException;
import com.capgemini.MyWebProject.repositories.ShowtimeRepository;

import jakarta.validation.Valid;
import java.util.List;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {

	private final ShowtimeRepository repository;

	@Autowired
	public ShowtimeServiceImpl(ShowtimeRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Showtimes> getAllShowtimes() {
		return repository.findAll();
	}

	@Override
	public Showtimes getShowtimeById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ShowtimeNotFoundException("Showtime not found with ID: " + id));
	}

	@Override
	public Showtimes createShowtime(@Valid Showtimes showtime) {
		return repository.save(showtime);
	}

	@Override
	public Showtimes updateShowtime(Long id, @Valid Showtimes updated) {
		Showtimes existing = repository.findById(id)
				.orElseThrow(() -> new ShowtimeNotFoundException("Showtime not found with ID: " + id));
		existing.setMovieID(updated.getMovieID());
		existing.setShowDate(updated.getShowDate());
		existing.setTime(updated.getTime());
		existing.setTheater(updated.getTheater());
		existing.setAvailableSeats(updated.getAvailableSeats());
		return repository.save(existing);
	}

	@Override
	public void deleteShowtime(Long id) {
		if (!repository.existsById(id)) {
			throw new ShowtimeNotFoundException("Cannot delete. Showtime not found with ID: " + id);
		}
		repository.deleteById(id);
	}
}


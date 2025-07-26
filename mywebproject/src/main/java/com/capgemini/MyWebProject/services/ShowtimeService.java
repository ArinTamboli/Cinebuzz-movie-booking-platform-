package com.capgemini.MyWebProject.services;

import java.util.List;
import com.capgemini.MyWebProject.entities.Showtimes;

public interface ShowtimeService {
	List<Showtimes> getAllShowtimes();

	Showtimes getShowtimeById(Long id);

	Showtimes createShowtime(Showtimes showtime);

	Showtimes updateShowtime(Long id, Showtimes showtime);

	void deleteShowtime(Long id);
}


package com.capgemini.MyWebProject.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.MyWebProject.dto.DateRevenueReportDTO;
import com.capgemini.MyWebProject.dto.MovieBookingReportDTO;
import com.capgemini.MyWebProject.entities.Bookings;
import com.capgemini.MyWebProject.entities.Movies;
import com.capgemini.MyWebProject.entities.Showtimes;
import com.capgemini.MyWebProject.repositories.BookingRepository;
import com.capgemini.MyWebProject.repositories.MovieRepository;
import com.capgemini.MyWebProject.repositories.ShowtimeRepository;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private ShowtimeRepository showtimeRepo;

    @Autowired
    private MovieRepository movieRepo;

    @GetMapping("/movie-bookings")
    @PreAuthorize("hasRole('admin')")
    public List<MovieBookingReportDTO> getMovieWiseBookings() {
        List<Bookings> bookings = bookingRepo.findAll();
        List<Showtimes> showtimes = showtimeRepo.findAll();
        List<Movies> movies = movieRepo.findAll();

        Map<Long, Integer> movieSeatMap = new HashMap<>();

        for (Bookings booking : bookings) {
            Showtimes show = showtimes.stream()
                    .filter(s -> s.getId().equals(booking.getShowID()))
                    .findFirst().orElse(null);
            if (show != null) {
                Long movieId = show.getMovieID();
                movieSeatMap.put(movieId, movieSeatMap.getOrDefault(movieId, 0) + booking.getSeatsBooked());
            }
        }

        List<MovieBookingReportDTO> result = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : movieSeatMap.entrySet()) {
            Long movieId = entry.getKey();
            String movieName = movies.stream()
                    .filter(m -> m.getId().equals(movieId))
                    .map(Movies::getName)
                    .findFirst()
                    .orElse("Unknown Movie");

            result.add(new MovieBookingReportDTO(movieName, entry.getValue()));
        }

        return result;
    }

    @GetMapping("/date-revenue")
    @PreAuthorize("hasRole('admin')")
    public List<DateRevenueReportDTO> getDateWiseRevenue() {
        List<Bookings> bookings = bookingRepo.findAll();

        Map<LocalDate, DateRevenueReportDTO> dateMap = new TreeMap<>();

        for (Bookings booking : bookings) {
            LocalDate date = booking.getBookingDate();
            dateMap.putIfAbsent(date, new DateRevenueReportDTO(date, 0.0, 0));
            DateRevenueReportDTO report = dateMap.get(date);
            report.setTotalRevenue(report.getTotalRevenue() + booking.getAmount());
            report.setTotalTickets(report.getTotalTickets() + booking.getSeatsBooked());
        }

        return new ArrayList<>(dateMap.values());
    }
}


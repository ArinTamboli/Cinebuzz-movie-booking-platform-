package com.capgemini.MyWebProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capgemini.MyWebProject.entities.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long> {
}


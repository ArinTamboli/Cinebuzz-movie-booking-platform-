package com.capgemini.MyWebProject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.capgemini.MyWebProject.entities.Showtimes;

public interface ShowtimeRepository extends JpaRepository<Showtimes, Long> {
}


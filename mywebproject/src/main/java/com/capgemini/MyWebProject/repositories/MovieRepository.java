package com.capgemini.MyWebProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capgemini.MyWebProject.entities.Movies;

public interface MovieRepository extends JpaRepository<Movies, Long> {
}

package com.capgemini.MyWebProject.services;

import java.util.List;

import com.capgemini.MyWebProject.entities.Movies;

public interface MovieService {
	List<Movies> getAllMovies();

	Movies getMovieById(Long id);

	Movies createMovie(Movies movie);

	Movies updateMovie(Long id, Movies movie);

	void deleteMovie(Long id);
}
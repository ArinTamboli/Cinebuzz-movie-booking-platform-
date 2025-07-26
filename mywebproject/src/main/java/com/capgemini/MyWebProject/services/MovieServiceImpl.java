package com.capgemini.MyWebProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.MyWebProject.entities.Movies;
import com.capgemini.MyWebProject.exceptions.MovieNotFoundException;
import com.capgemini.MyWebProject.repositories.MovieRepository;

import jakarta.validation.Valid;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

	private final MovieRepository repository;

	@Autowired
	public MovieServiceImpl(MovieRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Movies> getAllMovies() {
		return repository.findAll();
	}

	@Override
	public Movies getMovieById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + id));
	}

	@Override
	public Movies createMovie(@Valid Movies movie) {
		return repository.save(movie);
	}

	@Override
	public Movies updateMovie(Long id, @Valid Movies updated) {
		Movies existing = repository.findById(id)
				.orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + id));
		existing.setName(updated.getName());
		existing.setGenre(updated.getGenre());
		existing.setLanguage(updated.getLanguage());
		existing.setDuration(updated.getDuration());
		return repository.save(existing);
	}


	@Override
	public void deleteMovie(Long id) {
		if (!repository.existsById(id)) {
			throw new MovieNotFoundException("Cannot delete. Movie not found with ID: " + id);
		}
		repository.deleteById(id);
	}
}


package com.capgemini.MyWebProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.MyWebProject.entities.Users;
import com.capgemini.MyWebProject.exceptions.UserNotFoundException;
import com.capgemini.MyWebProject.repositories.UserRepository;

import jakarta.validation.Valid;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Users> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public Users getUserById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
	}

	@Override
	public Users createUser(@Valid Users user) {
		return repository.save(user);
	}

	@Override
	public Users updateUser(Long id, @Valid Users updated) {
		Users existing = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
		existing.setName(updated.getName());
		existing.setEmail(updated.getEmail());
		existing.setPassword(updated.getPassword());
		existing.setUsertype(updated.getUsertype());
		existing.setPhone(updated.getPhone());
		return repository.save(existing);
	}

	@Override
	public void deleteUser(Long id) {
		if (!repository.existsById(id)) {
			throw new UserNotFoundException("Cannot delete. User not found with ID: " + id);
		}
		repository.deleteById(id);
	}

	@Override
	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	public boolean existsByName(String username) {
		return repository.existsByName(username);
	}

	@Override
	public Users findByNameOrEmail(String username, String email) {
		return repository.findByNameOrEmail(username, email)
				.orElseThrow(()->new UserNotFoundException("Username or Email not Found !"));
	}
}


package com.capgemini.MyWebProject.services;

import java.util.List;
import com.capgemini.MyWebProject.entities.Users;

public interface UserService {
	List<Users> getAllUsers();

	Users getUserById(Long id);

	Users createUser(Users user);

	Users updateUser(Long id, Users user);

	void deleteUser(Long id);
	
	boolean existsByEmail(String email);

	boolean existsByName(String username);


	Users findByNameOrEmail(String username, String email);
}


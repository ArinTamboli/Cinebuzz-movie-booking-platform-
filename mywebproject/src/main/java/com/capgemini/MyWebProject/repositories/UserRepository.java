package com.capgemini.MyWebProject.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capgemini.MyWebProject.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	
	Optional<Users> findByEmail(String email);

	Optional<Users> findByNameOrEmail(String username, String email);

	Optional<Users> findByName(String username);

	boolean existsByName(String username);

	boolean existsByEmail(String email);
}


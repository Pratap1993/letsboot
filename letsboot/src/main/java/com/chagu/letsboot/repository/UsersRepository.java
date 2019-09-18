package com.chagu.letsboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.chagu.letsboot.model.Users;

/**
 * @author Pratap Bhanu
 *
 */
@Service
public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query("SELECT u FROM Users u WHERE u.isActive = 1 and u.username = ?1")
	Optional<Users> findByUsername(String username);

	Optional<Users> findByEmailAndActivationCode(String email, String activationCode);
}

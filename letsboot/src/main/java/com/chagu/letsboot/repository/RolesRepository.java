package com.chagu.letsboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chagu.letsboot.model.Roles;

/**
 * @author Pratap Bhanu
 *
 */
public interface RolesRepository extends JpaRepository<Roles, Long> {

	@Query("SELECT r FROM Roles r WHERE r.isActive = 1 and r.name = ?1")
	Optional<Roles> findByName(String name);
}

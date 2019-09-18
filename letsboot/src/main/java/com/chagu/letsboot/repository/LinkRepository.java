package com.chagu.letsboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chagu.letsboot.model.Link;

/**
 * @author Pratap Bhanu
 *
 */
public interface LinkRepository extends JpaRepository<Link, Long> {

	Link findByTitle(String title);

}

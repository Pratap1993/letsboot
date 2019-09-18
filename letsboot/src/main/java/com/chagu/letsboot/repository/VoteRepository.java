package com.chagu.letsboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chagu.letsboot.model.Vote;

/**
 * @author Pratap Bhanu
 *
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {

}

package com.chagu.letsboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chagu.letsboot.model.Vote;
import com.chagu.letsboot.repository.VoteRepository;

/**
 * @author Pratap Bhanu
 *
 */
@Service
public class VoteService {

	private final Logger logger = LoggerFactory.getLogger(VoteService.class);
	private final VoteRepository voteRepository;

	public VoteService(VoteRepository voteRepository) {
		this.voteRepository = voteRepository;
	}

	public Vote saveVote(Vote vote) {
		logger.info("Saving Vote !!!");
		Vote entity = voteRepository.save(vote);
		return entity;
	}

}

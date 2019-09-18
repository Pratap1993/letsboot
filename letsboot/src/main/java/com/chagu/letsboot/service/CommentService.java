package com.chagu.letsboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chagu.letsboot.model.Comment;
import com.chagu.letsboot.repository.CommentRepository;

/**
 * @author Pratap Bhanu
 *
 */
@Service
public class CommentService {

	private final Logger logger = LoggerFactory.getLogger(CommentService.class);
	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public Comment saveComment(Comment comment) {
		logger.info("Saving Comment !!!");
		return commentRepository.save(comment);
	}

}

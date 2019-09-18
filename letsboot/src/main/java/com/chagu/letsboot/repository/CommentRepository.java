package com.chagu.letsboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chagu.letsboot.model.Comment;

/**
 * @author Pratap Bhanu
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}

package com.chagu.letsboot.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chagu.letsboot.model.Link;
import com.chagu.letsboot.repository.LinkRepository;

/**
 * @author Pratap Bhanu
 *
 */
@Service
public class LinkService {

	private final Logger logger = LoggerFactory.getLogger(LinkService.class);
	private final LinkRepository linkRepository;

	public LinkService(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	public List<Link> findAllLinks() {
		logger.info("Returning List of Links !!!");
		return linkRepository.findAll();
	}

	public Link findById(Long linkId) {
		logger.info("Returning Link using linkId :{}", linkId);
		Link link = null;
		Optional<Link> entity = linkRepository.findById(linkId);
		if (entity.isPresent()) {
			link = entity.get();
		}
		return link;
	}

	public Link saveLink(Link link) {
		logger.info("Saving Link !!!");
		Link entity = linkRepository.save(link);
		return entity;
	}

}

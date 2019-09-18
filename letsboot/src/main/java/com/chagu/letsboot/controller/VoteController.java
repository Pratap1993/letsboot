package com.chagu.letsboot.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chagu.letsboot.model.Link;
import com.chagu.letsboot.model.Vote;
import com.chagu.letsboot.service.LinkService;
import com.chagu.letsboot.service.VoteService;

/**
 * @author Pratap Bhanu
 *
 */
@RestController
public class VoteController {

	private VoteService voteService;

	private LinkService linkService;

	public VoteController(VoteService voteService, LinkService linkService) {
		this.voteService = voteService;
		this.linkService = linkService;
	}

	@Secured("ROLE_USER")
	@GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
	public int vote(@PathVariable Long linkID, @PathVariable short direction, @PathVariable int voteCount) {
		Link link = linkService.findById(linkID);
		if (link != null) {
			Vote vote = new Vote();
			vote.setDirection(direction);
			vote.setLink(link);
			voteService.saveVote(vote);

			int updatedVoteCount = voteCount + direction;
			link.setVoteCount(updatedVoteCount);
			linkService.saveLink(link);
			return updatedVoteCount;
		}
		return voteCount;
	}

}

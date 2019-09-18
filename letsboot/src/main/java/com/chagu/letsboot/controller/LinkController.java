package com.chagu.letsboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chagu.letsboot.model.Comment;
import com.chagu.letsboot.model.Link;
import com.chagu.letsboot.service.CommentService;
import com.chagu.letsboot.service.LinkService;

@Controller
@RequestMapping("/link")
public class LinkController {

	private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

	private LinkService linkService;

	private CommentService commentService;

	public LinkController(LinkService linkService, CommentService commentService) {
		this.linkService = linkService;
		this.commentService = commentService;
	}

	@GetMapping("/getFoo")
	public String fooPage(Model model) {
		model.addAttribute("pageTitle", "This is Foo !!!");
		return "foo";
	}

	@GetMapping("/getLinkList")
	public String getLinkList(Model model) {
		List<Link> linkList = linkService.findAllLinks();
		model.addAttribute("links", linkList);
		return "link/linkList";
	}

	@GetMapping("/createLink")
	public String newLinkForm(Model model) {
		model.addAttribute("link", new Link());
		return "link/submit";
	}

	@PostMapping("/createLink")
	public String createLink(@Valid Link link, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			logger.info("VAlidation Errors were found while submitting new link.");
			model.addAttribute("link", link);
			return "link/submit";
		} else {
			linkService.saveLink(link);
			logger.info("New Link Saved Successfully !!!");
			redirectAttributes.addAttribute("linkId", link.getId()).addFlashAttribute("success", true);
			return "redirect:/link/readLink-{linkId}";
		}
	}

	@GetMapping("/readLink-{linkId}")
	public String readLink(@PathVariable Long linkId, Model model) {
		Link link = linkService.findById(linkId);
		if (link != null) {
			Comment comment = new Comment();
			comment.setLink(link);
			model.addAttribute("comment", comment);
			model.addAttribute("link", link);
			model.addAttribute("success", model.containsAttribute("success"));
			return "link/view";
		} else {
			return "link/getLinkList";
		}
	}

	@Secured("ROLE_USER")
	@PostMapping("/comments")
	public String addComment(@Valid Comment comment, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			logger.info("Something went wrong.");
		} else {
			logger.info("New Comment Saved!");
			commentService.saveComment(comment);
		}
		return "redirect:/link/readLink-" + comment.getLink().getId();
	}

	@PutMapping("/updateLink-{linkId}")
	public void updateLink(@PathVariable Long linkId, @ModelAttribute Link link) {
		linkService.saveLink(link);
	}

}

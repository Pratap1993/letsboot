package com.chagu.letsboot.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.chagu.letsboot.model.Users;

/**
 * @author Pratap Bhanu
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
			return Optional.of("Chagulu");
		}
		return Optional.of(((Users) auth.getPrincipal()).getFullName());
	}

}

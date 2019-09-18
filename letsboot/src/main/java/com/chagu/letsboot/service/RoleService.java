package com.chagu.letsboot.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chagu.letsboot.model.Roles;
import com.chagu.letsboot.repository.RolesRepository;

/**
 * @author Pratap Bhanu
 *
 */
@Service
public class RoleService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final RolesRepository rolesRepository;

	public RoleService(RolesRepository rolesRepository) {
		super();
		this.rolesRepository = rolesRepository;
	}

	Roles findByName(String roleName) {
		logger.info("Returning Role using :{}", roleName);
		Roles role = null;
		Optional<Roles> roleEntity = rolesRepository.findByName(roleName);
		if (roleEntity.isPresent()) {
			role = roleEntity.get();
		}
		return role;
	}

}

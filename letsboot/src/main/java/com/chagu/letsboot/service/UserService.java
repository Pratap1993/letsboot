package com.chagu.letsboot.service;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chagu.letsboot.model.Roles;
import com.chagu.letsboot.model.Users;
import com.chagu.letsboot.repository.UsersRepository;

/**
 * @author Pratap Bhanu
 *
 */
@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UsersRepository usersRepository;
	private final RoleService roleService;
	private final PasswordEncoder encoder;
	private final MailService mailService;

	public UserService(UsersRepository usersRepository, RoleService roleService, PasswordEncoder encoder,
			MailService mailService) {
		this.usersRepository = usersRepository;
		this.roleService = roleService;
		this.encoder = encoder;
		this.mailService = mailService;
	}

	public Users register(Users user) {
		logger.info("Registering New User !!!");
		String secret = encoder.encode(user.getPassword());
		Roles role = roleService.findByName("ROLE_USER");
		user.setActive(false);
		user.setPassword(secret);
		user.setConfirmPassword(secret);
		user.addRole(role);
		user.setActivationCode(UUID.randomUUID().toString());
		save(user);
		sendActivationEmail(user);
		return user;
	}

	public Users save(Users user) {
		logger.info("Saving User !!!");
		return usersRepository.save(user);
	}

	@Transactional
	public void saveUsers(Users... users) {
		for (Users user : users) {
			logger.info("Saving User with UserName : {}" + user.getUsername());
			usersRepository.save(user);
		}
	}

	private void sendActivationEmail(Users user) {
		mailService.sendActivationEmail(user);
	}

	public void sendWelcomeEmail(Users user) {
		mailService.sendWelcomeEmail(user);
	}

	public Optional<Users> findByEmailAndActivationCode(String email, String activationCode) {
		return usersRepository.findByEmailAndActivationCode(email, activationCode);
	}

	public void activateUser(Users user) {
		user.setActive(true);
		user.setConfirmPassword(user.getPassword());
		saveUsers(user);
		sendWelcomeEmail(user);
	}
}

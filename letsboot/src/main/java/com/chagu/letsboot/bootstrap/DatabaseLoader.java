package com.chagu.letsboot.bootstrap;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.chagu.letsboot.model.Roles;
import com.chagu.letsboot.model.Users;
import com.chagu.letsboot.repository.RolesRepository;
import com.chagu.letsboot.repository.UsersRepository;

/**
 * @author Pratap Bhanu
 *
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

	private final PasswordEncoder encoder;

	private RolesRepository roleRepository;

	private UsersRepository userRepository;

	public DatabaseLoader(PasswordEncoder encoder, RolesRepository roleRepository, UsersRepository userRepository) {
		this.encoder = encoder;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) {
		addUsersAndRoles();
	}

	private void addUsersAndRoles() {
		String secret = encoder.encode("Pulsar150");

		Roles userRole = new Roles();
		userRole.setName("ROLE_USER");
		userRole.setActive(true);
		userRole.setCreatedBy("Chagulu");
		userRole.setCreationDate(LocalDateTime.now());
		roleRepository.save(userRole);

		Roles adminRole = new Roles();
		adminRole.setName("ROLE_ADMIN");
		adminRole.setActive(true);
		adminRole.setCreatedBy("Chagulu");
		adminRole.setCreationDate(LocalDateTime.now());
		roleRepository.save(adminRole);

		Users user = new Users();
		user.setActivationCode("DemoCode1");
		user.setActive(true);
		user.setFirstName("Pratap");
		user.setLastName("Bhanu");
		user.setEmail("bhanu@gmail.com");
		user.setUsername("pratap93");
		user.addRole(adminRole);
		user.setPassword(secret);
		user.setConfirmPassword(secret);
		userRepository.save(user);

		Users admin = new Users();
		admin.setActivationCode("DemoCode2");
		admin.setActive(true);
		admin.setFirstName("Priyanka");
		admin.setLastName("Priyadarshini");
		admin.setEmail("priyanka@gmail.com");
		admin.setUsername("liju93");
		admin.addRole(userRole);
		admin.setPassword(secret);
		admin.setConfirmPassword(secret);
		userRepository.save(admin);

		Users master = new Users();
		master.addRoles(new HashSet<>(Arrays.asList(userRole, adminRole)));
		master.setActivationCode("DemoCode23");
		master.setActive(true);
		master.setFirstName("Chagulu");
		master.setLastName("The Champ");
		master.setEmail("chagu@gmail.com");
		master.setUsername("chagulu93");
		master.setPassword(secret);
		master.setConfirmPassword(secret);
		userRepository.save(master);
	}

}
package com.chagu.letsboot;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import com.chagu.letsboot.model.Comment;
import com.chagu.letsboot.model.Link;
import com.chagu.letsboot.repository.CommentRepository;
import com.chagu.letsboot.repository.LinkRepository;

/**
 * @author Pratap Bhanu
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.chagu.letsboot" })
@EnableTransactionManagement
public class LetsbootApplication {

	private static final Logger log = LoggerFactory.getLogger(LetsbootApplication.class);

	public static void main(String[] args) {
		log.info("Starting up the application !!!");
		SpringApplication.run(LetsbootApplication.class, args);
	}

//	@Bean
	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
		return args -> {
			Link link = new Link();
			link.setCreatedBy("Chagulu");
			link.setCreationDate(LocalDateTime.now());
			link.setTitle("First Link");
			link.setUrl("https://chagu.co.in");

			Comment comment = new Comment();
			comment.setBody("Chagulu is a Champ !!!");
			comment.setCreatedBy("Chagulu");
			comment.setCreationDate(LocalDateTime.now());
			comment.setLink(link);

			link.setComments(Arrays.asList(comment));
			linkRepository.save(link);

			Link l = linkRepository.findByTitle("First Link");
			System.out.println("Link is created by : " + l.getCreatedBy());
		};
	}

	@Bean
	PrettyTime prettyTime() {
		return new PrettyTime();
	}

	// TODO * Configuring this bean should not be needed once Spring Boot's
	// Thymeleaf starter includes configuration
	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

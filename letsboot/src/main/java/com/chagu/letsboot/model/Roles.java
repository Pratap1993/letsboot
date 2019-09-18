package com.chagu.letsboot.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pratap Bhanu
 *
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Roles extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	private boolean isActive;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	private Collection<Users> users;

}
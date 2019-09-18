package com.chagu.letsboot.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chagu.letsboot.validator.PasswordsMatch;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Pratap Bhanu
 *
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@PasswordsMatch
public class Users extends Auditable implements UserDetails {

	private static final long serialVersionUID = 6474523098016113017L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@NotEmpty(message = "You must enter First Name.")
	private String firstName;

	@NonNull
	@NotEmpty(message = "You must enter Last Name.")
	private String lastName;

	@Transient
	@Setter(AccessLevel.NONE)
	private String fullName;

	@Size(min = 5, max = 20)
	@Column(nullable = false, unique = true)
	private String email;

	@Size(min = 3, max = 20)
	@Column(name = "user_name", nullable = false, unique = true)
	private String username;

	@Column(length = 100)
	private String password;

	@Column(nullable = false)
	private boolean isActive;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
	private Set<Roles> roles = new HashSet<>();
	
	@Column(nullable = false, unique = true)
	private String activationCode;

	@Transient
	@NotEmpty(message = "Please enter Password Confirmation.")
	private String confirmPassword;

	public String getFullName() {
		return firstName + " " + lastName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	public void addRole(Roles role) {
		roles.add(role);
	}

	public void addRoles(Set<Roles> roles) {
		roles.forEach(this::addRole);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

	@Override
	public String getPassword() {
		return password;
	}
}

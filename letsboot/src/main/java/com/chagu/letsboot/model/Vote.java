package com.chagu.letsboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author Pratap Bhanu
 *
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Vote extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private Short direction;

	@NonNull
	@ManyToOne
	private Link link;

}

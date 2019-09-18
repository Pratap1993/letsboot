package com.chagu.letsboot.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.ocpsoft.prettytime.PrettyTime;

import com.chagu.letsboot.service.BeanUtilService;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Pratap Bhanu
 *
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Comment extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Exclude
	private Long id;

	@NotNull
	@ToString.Exclude
	private String body;

	@ManyToOne
	@NotNull
	@ToString.Exclude
	private Link link;

	public String getPrettyTime() {
		PrettyTime pt = BeanUtilService.getBean(PrettyTime.class);
		return pt.format(convertToDateViaInstant(getCreationDate()));
	}

	private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

}

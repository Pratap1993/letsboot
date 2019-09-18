package com.chagu.letsboot.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
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
public class Link extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Exclude
	private Long id;

	@NotNull
	@NotEmpty(message = "Please Enter A Title !!!")
	@ToString.Exclude
	private String title;

	@NotNull
	@NotEmpty(message = "Please Enter An URL !!!")
	@URL(message = "Please Enter A Valid URL !!!")
	@ToString.Exclude
	private String Url;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "link", orphanRemoval = true)
	@ToString.Exclude
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "link")
	@ToString.Exclude
	private List<Vote> votes = new ArrayList<>();

	private Integer voteCount = 0;

	public String getDomainName() throws URISyntaxException {
		URI uri = new URI(this.Url);
		String domain = uri.getHost();
		return domain.startsWith("www.") ? domain.substring(4) : domain;
	}

	public String getPrettyTime() {
		PrettyTime pt = BeanUtilService.getBean(PrettyTime.class);
		return pt.format(convertToDateViaInstant(getCreationDate()));
	}

	private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

}

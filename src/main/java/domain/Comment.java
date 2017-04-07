
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	
	private int numberOfStars;
	private String text;
	private Date moment;
	
	public Comment() {
		super();
	}
	
	@Range(min = 0, max = 5)
	public int getNumberOfStars() {
		return numberOfStars;
	}
	public void setNumberOfStars(int numberOfStars) {
		this.numberOfStars = numberOfStars;
	}
	@NotBlank
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	//Relationships
	
	private Painter painter;
	

	@Valid
	@ManyToOne(optional=false)
	public Painter getPainter() {
		return painter;
	}

	public void setPainter(Painter painter) {
		this.painter = painter;
	}
	

	

	
}

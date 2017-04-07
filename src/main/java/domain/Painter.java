
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Painter extends Actor {

	private String	codeSS;
	private double		averageStart;


	public Painter() {
		super();
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp="\\d{11}")
	public String getCodeSS() {
		return this.codeSS;
	}

	public void setCodeSS(final String codeSS) {
		this.codeSS = codeSS;
		
	}
	
	@NotNull
	@Range(min = 0, max = 5)
	public double getAverageStart() {
		return averageStart;
	}

	public void setAverageStart(double averageStart) {
		this.averageStart = averageStart;
	}

	
	//RELATIONSHIPS
	
	Collection<Budget> budgets;
	Collection<Comment> comments;
	private Curriculum curriculum;

	@Valid
	@OneToMany(mappedBy="painter")
	public Collection<Budget> getBudgets() {
		return budgets;
	}

	public void setBudgets(Collection<Budget> budgets) {
		this.budgets = budgets;
	}

	@Valid
	@OneToMany(mappedBy="painter")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Valid
	@OneToOne(optional=true)
	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	
	
	
	
	
	
	
}

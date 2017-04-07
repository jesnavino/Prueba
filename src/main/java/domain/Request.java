
package domain;

import java.net.URL;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity {

	//Constructors====================================================================================

	public Request() {
		super();
	}


	//Attributes=====================================================================================
	private String  schedule;
	private String	measures;
	private String description;
	private String address;
	private Date moment;
	private Date work;
	private Collection<URL> photos;
	private CreditCard creditCard;
	
	
	//Getters & setters================================================================================
	
	@NotBlank
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	@NotBlank
	public String getMeasures() {
		return measures;
	}
	public void setMeasures(String measures) {
		this.measures = measures;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getWork() {
		return work;
	}
	public void setWork(Date work) {
		this.work = work;
	}
	
	@ElementCollection
	public Collection<URL> getPhotos() {
		return photos;
	}
	public void setPhotos(Collection<URL> photos) {
		this.photos = photos;
	}
	
	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	

	//Relationships ====================================================================================
	

	private Customer customer;
	private Collection<Budget> budgets;

	@Valid
	@ManyToOne(optional=false)
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Valid
	@OneToMany(mappedBy="request")
	public Collection<Budget> getBudgets() {
		return budgets;
	}
	public void setBudgets(Collection<Budget> budgets) {
		this.budgets = budgets;
	}

}

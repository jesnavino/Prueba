
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;



@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {


	private String dni;

	public Customer() {
		super();
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp="([0-9]{8})([A-Z])")
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	//RELATIONSHIPS
	
	private Collection<Request> requests;

	@Valid
	@OneToMany(mappedBy="customer")
	public Collection<Request> getRequests() {
		return requests;
	}


	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}
	
	

	
}
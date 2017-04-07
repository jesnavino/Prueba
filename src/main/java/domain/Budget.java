
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Budget extends DomainEntity {

	//Constructors====================================================================================

	public Budget() {
		super();
	}


	//Attributes=====================================================================================
	private String	status;
	private double	amount;
	private String	description;


	//Getters & setters================================================================================
	@Pattern(regexp = "^ACCEPTED$|^PENDING$|^REJECTED$")
	@NotBlank
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Min(1)
	@Digits(integer = 99, fraction = 2)
	public double getAmount() {
		return this.amount;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	//Relationships ====================================================================================

	private Painter painter;
	private Request request;
	private Collection<Material> materials;

	@Valid
	@ManyToOne(optional=false)
	public Painter getPainter() {
		return painter;
	}

	public void setPainter(Painter painter) {
		this.painter = painter;
	}

	@Valid
	@ManyToOne(optional=false)
	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Valid
	@OneToMany(mappedBy="budget")
	public Collection<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(Collection<Material> materials) {
		this.materials = materials;
	}

	
	
	
}


package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Material extends DomainEntity {

	//Constructors====================================================================================

	public Material() {
		super();
	}


	//Attributes=====================================================================================

	private String	description;
	private int		quantity;
	private double	price;


	//Getters & setters================================================================================

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Min(1)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	@Min(1)
	@Digits(integer = 99, fraction = 2)
	public double getPrice() {
		return this.price;
	}

	public void setPrice(final double price) {
		this.price = price;
	}


	//Relationships ====================================================================================

	private Budget budget;


	@Valid
	@ManyToOne(optional = false)
	public Budget getBudget() {
		return this.budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}
}

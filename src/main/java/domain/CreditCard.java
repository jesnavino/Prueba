
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard {

	// Constructors -----------------------------------------------------------

	public CreditCard() {
		super();

	}


	// Attributes -------------------------------------------------------------
	private String	holderName;
	private String	brandName;
	private String	number;
	private Integer	expirationMonth;
	private Integer	expirationYear;
	private Integer	cvvCode;


	@NotBlank
	public String getHolderName() {
		return this.holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	@NotBlank
	public String getBrandName() {
		return this.brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@NotBlank
	@CreditCardNumber
	public String getNumber() {
		return this.number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	@Range(min = 1, max = 12)
	public Integer getExpirationMonth() {
		return this.expirationMonth;
	}
	public void setExpirationMonth(Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@Range(min = 2016)
	@Digits(fraction=0,integer=4)
	public Integer getExpirationYear() {
		return this.expirationYear;
	}
	public void setExpirationYear(Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	@Range(min = 100, max = 999)
	public Integer getCvvCode() {
		return cvvCode;
	}
	public void setCvvCode(Integer cvvCode) {
		this.cvvCode = cvvCode;
	}
}

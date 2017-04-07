package forms;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class CustomerForm {

	private String username,password,email,phone,repeatedPassword,name,surname,postalCode,dni;
	private Boolean hasAccepted; 
	
	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	@Size(min = 5, max = 32)
	@Column(unique = true)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank
	@Email
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp="^[9|6|7][0-9]{8}$")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@NotNull
	public Boolean getHasAccepted() {
		return hasAccepted;
	}

	public void setHasAccepted(Boolean hasAccepted) {
		this.hasAccepted = hasAccepted;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp="^([1-9]{2}|[0-9][1-9]|[1-9][0-9])[0-9]{3}$")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	
}

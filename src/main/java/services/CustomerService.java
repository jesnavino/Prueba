package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Customer;
import forms.CustomerForm;
import repositories.CurriculumRepository;
import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;


	public CustomerService() {
		super();
	}
	
	
	public Customer create() {
		
		UserAccount userAccount = new UserAccount();
		Authority aut = new Authority();
		aut.setAuthority(Authority.CUSTOMER);
		Collection<Authority> authorities = userAccount.getAuthorities();
		authorities.add(aut);
		userAccount.setAuthorities(authorities);
		
		Customer result;
		result = new Customer();
		
		result.setUserAccount(userAccount);
		return result;

	}
	
	public void save(Customer customer) {
		
		customerRepository.save(customer);
		
	}
	


	public Customer getByUsername(String username) {
		return customerRepository.getByUsername(username);
	}
	
	public Customer getLogged() {
		Customer result;
		UserAccount user;
		user = LoginService.getPrincipal();
		result = getByUsername(user.getUsername());
		return result;
	}


	public Customer reconstruct(CustomerForm customerForm) {
		Assert.isTrue(customerForm.getRepeatedPassword().equals(customerForm.getPassword()));
		Assert.isTrue(customerForm.getHasAccepted());
		
		Customer customer = this.create();
		customer.setEmail(customerForm.getEmail());
		customer.setPhone(customerForm.getPhone());
		customer.setName(customerForm.getName());
		customer.setSurname(customerForm.getSurname());
		customer.setPostalCode(customerForm.getPostalCode());
		customer.setDni(customerForm.getDni());
		customer.getUserAccount().setUsername(customerForm.getUsername());
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		customer.getUserAccount().setPassword(encoder.encodePassword(customerForm.getPassword(), null));
		
		return customer;
	}
	

}

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Customer;
import forms.CustomerForm;
import services.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController{
	
	@Autowired
	private CustomerService customerService;
	
	//Constructor
	
	public CustomerController(){
		super();
	}
	
	//REGISTER
			@RequestMapping(value = "/create",method=RequestMethod.GET)
			public ModelAndView create() {
				ModelAndView result;			
				
				CustomerForm customerForm = new CustomerForm();
			
				result = createEditModelAndView(customerForm);
				
				return result;
			}
			
			@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid CustomerForm customerForm, BindingResult binding) {
				ModelAndView result;
				Customer customer;
				if (binding.hasErrors()) {
					result = createEditModelAndView(customerForm);
					System.out.println(binding.getAllErrors());
				} else {
					try {
						customer = customerService.reconstruct(customerForm);
						customerService.save(customer);
						result = new ModelAndView("redirect:/");
						
					}catch (DataIntegrityViolationException oops) {
						result = createEditModelAndView(customerForm,
								"customer.error.alreadyexists");
					

					}catch(IllegalArgumentException oops){
						result = createEditModelAndView(customerForm,
								"customer.error.passOrAccepted");
							
						
					}catch (Throwable oops) {

						result = createEditModelAndView(customerForm,
								"customer.error.operation");
						System.out.println(oops.toString());
					}

						}
				return result;
			}
			
			
			//Auxiliary methods-----------------------------------------------------
			
			protected ModelAndView createEditModelAndView(CustomerForm customerForm) {
				ModelAndView result;

				result = createEditModelAndView(customerForm, null);

				return result;
			}

			protected ModelAndView createEditModelAndView(CustomerForm customerForm,String message) {
				ModelAndView result;
				result = new ModelAndView("customer/edit");
				result.addObject("customerForm", customerForm);
				result.addObject("message", message);
				return result;
			}


}

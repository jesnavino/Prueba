package controllers.customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.RequestService;


import controllers.AbstractController;
import domain.CreditCard;
import domain.Request;


@Controller
@RequestMapping("/request/customer")

public class RequestCustomerController extends AbstractController {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private CustomerService customerService;
	
	
	// Constructors -----------------------------------------------------------

	public RequestCustomerController() {
			super();
	}
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;			
		result = new ModelAndView("request/list");
			
		result.addObject("requests",requestService.findLoggedRequest());
		result.addObject("requestUri","request/customer/list.do");
			

		return result;
	}
	
		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Request request;
	
	
		request = requestService.create();
		
		List<Request> requests = (List<Request>) requestService.findLoggedRequest();					
		if(!requests.isEmpty() && request.getId()==0){
			
			request.setCreditCard(requests.get(requests.size()-1).getCreditCard());
			
		}
		
		result = createEditModelAndView(request);
			
		return result;
	}

	// edicion -----------------------------------------
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int id) {
			ModelAndView result;
			Request request;
			
			request=requestService.findOne(id);
			Assert.notNull(request); 
			result = createEditModelAndView(request);

			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params="save")
		public ModelAndView save(@Valid Request request, BindingResult binding){
			ModelAndView result;
				
			if(binding.hasErrors()){
				result = createEditModelAndView(request);
			}else{
				try{	
						requestService.save(request);
						result = new ModelAndView("redirect:list.do");					
		
				}catch(Throwable oops){
					result = createEditModelAndView(request, "request.commit.error");
				}
			}
			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(@Valid Request Request, BindingResult binding) {
			ModelAndView result;
			try {
				requestService.delete(Request);
				result = new ModelAndView("redirect:list.do");		
			} catch (Throwable oops) {
				System.out.println(oops.getMessage());
				result = createEditModelAndView(Request, "request.commit.error");
			}

			return result;
		}

		// auxiliary------------------------------------------------------------

		protected ModelAndView createEditModelAndView(Request request){
			ModelAndView result;
			
			result = createEditModelAndView(request, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Request request, String message){
			ModelAndView result;
			
			Collection<String> priorities = new ArrayList<String>();
			
			priorities.add("LOW");
			priorities.add("MID");
			priorities.add("HIGH");
				
			result = new ModelAndView("request/edit");
			
			
			result.addObject("priorities", priorities);
			result.addObject("request", request);
			result.addObject("message", message);
			
			return result;
		}
}
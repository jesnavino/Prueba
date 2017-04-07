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
import domain.Painter;
import forms.CustomerForm;
import forms.PainterForm;
import services.CustomerService;
import services.PainterService;

@Controller
@RequestMapping("/painter")
public class PainterController extends AbstractController{
	
	@Autowired
	private PainterService painterService;
	
	//Constructor
	
	public PainterController(){
		super();
	}
	
	//REGISTER
			@RequestMapping(value = "/create",method=RequestMethod.GET)
			public ModelAndView create() {
				ModelAndView result;			
				
				PainterForm painterForm = new PainterForm();
			
				result = createEditModelAndView(painterForm);
				
				return result;
			}
			
			@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid PainterForm painterForm, BindingResult binding) {
				ModelAndView result;
				Painter painter;
				if (binding.hasErrors()) {
					result = createEditModelAndView(painterForm);
					System.out.println(binding.getAllErrors());
				} else {
					try {
						painter = painterService.reconstruct(painterForm);
						painterService.save(painter);
						result = new ModelAndView("redirect:/");
						
					}catch (DataIntegrityViolationException oops) {
						result = createEditModelAndView(painterForm,
								"customer.error.alreadyexists");
					

					}catch(IllegalArgumentException oops){
						result = createEditModelAndView(painterForm,
								"customer.error.passOrAccepted");
							
						
					}catch (Throwable oops) {

						result = createEditModelAndView(painterForm,
								"customer.error.operation");
						System.out.println(oops.toString());
					}

						}
				return result;
			}
			
			@RequestMapping(value = "/list",method=RequestMethod.GET)
			public ModelAndView list() {
				ModelAndView result;			
				result = new ModelAndView("painter/list");
					
				result.addObject("painters",painterService.findAll());
				result.addObject("requestUri","painter/list.do");
					

				return result;
			}
			
			
			
			//Auxiliary methods-----------------------------------------------------
			
			protected ModelAndView createEditModelAndView(PainterForm painterForm) {
				ModelAndView result;

				result = createEditModelAndView(painterForm, null);

				return result;
			}

			protected ModelAndView createEditModelAndView(PainterForm painterForm,String message) {
				ModelAndView result;
				result = new ModelAndView("painter/edit");
				result.addObject("painterForm", painterForm);
				result.addObject("message", message);
				return result;
			}


}

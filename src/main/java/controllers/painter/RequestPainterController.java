package controllers.painter;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Request;
import domain.Request;
import services.RequestService;

@Controller
@RequestMapping("/request/painter")
public class RequestPainterController extends AbstractController{
	
	@Autowired
	private RequestService requestService;
	
	
	// Constructors -----------------------------------------------------------

	public RequestPainterController() {
			super();
	}
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;			
		result = new ModelAndView("request/list");
			
		result.addObject("requests",requestService.findRequestToBudget());
		result.addObject("requestUri","request/painter/list.do");
			

		return result;
	}

}

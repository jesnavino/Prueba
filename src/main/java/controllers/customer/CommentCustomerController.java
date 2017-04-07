package controllers.customer;



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
import controllers.CustomerController;
import domain.Comment;
import domain.Request;
import services.ActorService;
import services.CommentService;
import services.PainterService;

@Controller
@RequestMapping("/comment/customer")
public class CommentCustomerController extends AbstractController{
	
	//Supporting Service--------------------------------------
	
			@Autowired
			private CommentService commentService;
			
			@Autowired
			private PainterService painterService;
	
			//Constructor--------------------------------------------
			
			public CommentCustomerController(){
				super();
			}

			
			//List comments to a painter
			@RequestMapping(value = "/list",method=RequestMethod.GET)
			public ModelAndView list(@RequestParam int id) {
				ModelAndView result;			
				result = new ModelAndView("comment/list");
				
				result.addObject("comments",commentService.listCommentPainterId(id));
				result.addObject("requestUri","comment/customer/list.do");
				
				return result;
			}
			
			@RequestMapping(value = "/create", method = RequestMethod.GET)
			public ModelAndView create(@RequestParam int id) {
				ModelAndView result;
				Comment comment;
				
				comment = commentService.create(id);
			
				result = createEditModelAndView(comment);
					
				return result;
			}
			
			// edicion -----------------------------------------
			@RequestMapping(value = "/edit", method = RequestMethod.GET)
			public ModelAndView edit(@RequestParam int id) {
				ModelAndView result;
				Comment comment;
				
				comment=commentService.findOne(id);
				Assert.notNull(comment); 
				result = createEditModelAndView(comment);

				return result;
			}
			
			@RequestMapping(value = "/edit", method = RequestMethod.POST, params="save")
			public ModelAndView save(@Valid Comment comment, BindingResult binding){
				ModelAndView result;
					
				if(binding.hasErrors()){
					result = createEditModelAndView(comment);
				}else{
					try{	
							commentService.save(comment);
							painterService.calculateAverageStarPainter(comment.getPainter());
							result = new ModelAndView("redirect:list.do?id="+comment.getPainter().getId());					
			
					}catch(Throwable oops){
						result = createEditModelAndView(comment, "comment.commit.error");
					}
				}
				return result;
			}
			

			protected ModelAndView createEditModelAndView(Comment comment){
				ModelAndView result;
				
				result = createEditModelAndView(comment, null);
				
				return result;
			}
			
			protected ModelAndView createEditModelAndView(Comment comment, String message){
				ModelAndView result;
					
				result = new ModelAndView("comment/edit");
				
				result.addObject("comment", comment);
				result.addObject("message", message);
				
				return result;
			}
			
}
		




package controllers.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Invoice;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

import controllers.AbstractController;
import domain.Budget;
import services.BudgetService;
import services.RequestService;


@Controller
@RequestMapping("/budget/customer")
public class BudgetCustomerController extends AbstractController{
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private BudgetService budgetService;
	
	// Constructors -----------------------------------------------------------

		public BudgetCustomerController() {
				super();
		}
		
		
		@RequestMapping(value = "/list",method=RequestMethod.GET)
		public ModelAndView list(@RequestParam int id) {
			ModelAndView result;			
			result = new ModelAndView("budget/list");
				
			result.addObject("budgets",budgetService.findBudgetsForRequestId(id));
			result.addObject("requestUri","budget/customer/list.do");
				

			return result;
		}
		
	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	 public ModelAndView accept(@RequestParam int id) {
		  ModelAndView result; 
		  
		  Budget budget;
		  
		  if( id < 0)
		 		new Throwable("Bad id for budget");
		  
		  budget= budgetService.findOne(id);
		  Assert.notNull(budget);
		 
		  
		  budget.setStatus("ACCEPTED");
		  
		  budgetService.save(budget);
		  
		  result = new ModelAndView("redirect:display.do?id="+budget.getId());
		  
		  return result;
		
 }
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int id) {
		ModelAndView result;
		Budget budget;
	
		budget = budgetService.findOne(id);
		result = createEditModelAndView(budget);
			
		return result;
	}
	
	@RequestMapping("/makePayPaypal")
	public ModelAndView makePayPaypal(@RequestParam int id) throws IOException, PayPalRESTException
	{
		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");

		String accessToken = new OAuthTokenCredential("ASEA9iFHqo5dyXBCszPMOEcO7MqbddtDY_CCKdNvWWVqHKYVstl_zISqOT9ZhdPv_8WLHble8L4PQ5H6", "EOIJoOYXdZXCBo4LPXepLL1KursXAFRzaAWqFov0H9nOP_u14UHpZe5lqXPMtY9sZWhyLzOOmb4NoVkP", sdkConfig).getAccessToken();
		APIContext apiContext = new APIContext(accessToken);
		apiContext.setConfigurationMap(sdkConfig);

		Amount amount = new Amount();
		amount.setCurrency("EUR");
		amount.setTotal("450.5");

		Transaction transaction = new Transaction();
		transaction.setDescription("Pictame Service paid");
		transaction.setAmount(amount);
		

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("https://devtools-paypal.com/guide/pay_paypal?cancel=true");
		redirectUrls.setReturnUrl("http://sportsmate.cf/SportsMate/customer/executePayment.do");
		payment.setRedirectUrls(redirectUrls);
		
		Payment createdPayment = payment.create(apiContext);	
		
		ModelAndView redirect=new ModelAndView("redirect:"+createdPayment.getLinks().get(1).getHref()+"&useraction=commit");
		
			return redirect;
		
		
		}

	@RequestMapping("/executePayment")
	public ModelAndView makePayPaypal(@RequestParam String PayerID, @RequestParam String paymentId) throws IOException, PayPalRESTException
	{
		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		String accessToken = new OAuthTokenCredential("ASEA9iFHqo5dyXBCszPMOEcO7MqbddtDY_CCKdNvWWVqHKYVstl_zISqOT9ZhdPv_8WLHble8L4PQ5H6", "EOIJoOYXdZXCBo4LPXepLL1KursXAFRzaAWqFov0H9nOP_u14UHpZe5lqXPMtY9sZWhyLzOOmb4NoVkP", sdkConfig).getAccessToken();
		APIContext apiContext = new APIContext(accessToken);
		
		apiContext.setConfigurationMap(sdkConfig);
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		Payment payment = new Payment();
		payment.setId(paymentId);
		
		
		PaymentExecution paymentExecute = new PaymentExecution();
		paymentExecute.setPayerId(PayerID);
		payment.execute(apiContext, paymentExecute);
		
		
		//Collection<Budget> invoices=budgetService.findBudgetsByCustomerId();
		
		/*Invoice invoice=null;
		
		
		for (Invoice c :invoices){
			
			
			if(c.getDatePay()==null){
				
				invoice=c;
			}
		}
		
		invoice.setDatePay(new Date());
		
		invoiceService.save(invoice);
		
		Customer e =customerService.findByPrincipal();
		
		e.setDebtor(false);
		
		ModelAndView result =seeInvoices();
		return result;
		
		*/
		
		return null;
		
	}
	 
	 @RequestMapping(value = "/reject", method = RequestMethod.GET)
	 public ModelAndView reject(@RequestParam int id) {
	  ModelAndView result; 
	  
	  Budget budget;
	  int idRequest;
	  
	  budget= budgetService.findOne(id);
	  Assert.notNull(budget);
	  idRequest=budget.getRequest().getId();
	  
	  budget.setStatus("REJECTED");
	  
	  budgetService.save(budget);
	  
	  result = new ModelAndView("redirect:list.do?id="+idRequest);
	  
	  return result;
	 }
	 
	 protected ModelAndView createEditModelAndView(Budget budget){
			ModelAndView result;
			
			result = createEditModelAndView(budget, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Budget budget, String message){
			ModelAndView result;
			
			Collection<String> priorities = new ArrayList<String>();
			
			priorities.add("ACCEPTED");
			priorities.add("PENDING");
			priorities.add("REJECTED");
				
			result = new ModelAndView("budget/edit");
			
			
			result.addObject("priorities", priorities);
			result.addObject("budget", budget);
			result.addObject("message", message);
			
			return result;
		}

}

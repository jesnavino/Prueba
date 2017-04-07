
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Budget;
import domain.Customer;
import domain.Material;
import domain.Painter;
import domain.Request;
import repositories.BudgetRepository;

@Service
@Transactional
public class BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;
	
	@Autowired
	private PainterService painterService;
	
	@Autowired
	private RequestService requestService;


	public BudgetService() {
		super();
	}

	public Budget findOne(final int budgetId) {
		Assert.notNull(budgetId);

		return this.budgetRepository.findOne(budgetId);

	}
	
	public Budget create() {
		Budget budget = new Budget();
		Collection <Material> materials = new ArrayList<Material>();
		
		Painter painter = painterService.getLogged();
		
		budget.setPainter(painter);
		budget.setMaterials(materials);
		budget.setStatus("PENDING");
		
		return budget;
	}
	
	public Budget save(final Budget budget) {
		Assert.notNull(budget);

		return this.budgetRepository.saveAndFlush(budget);
	}
	
	public void delete(Budget budget){
		Assert.notNull(budget);
		budgetRepository.delete(budget);
	}

	public Collection<Budget> findLoggedBudget() {
		Collection<Budget> result = new ArrayList<Budget>();
		Painter painter= painterService.getLogged();
		result = budgetRepository.findLoggedBudget(painter.getId());
		return result;
	}

	public Collection<Budget> findAll() {
		Collection<Budget> result = new ArrayList<Budget>();
		result = budgetRepository.findAll();
		return result;
	}

	public Budget create(int id) {
		Budget result = new Budget();
		
		Request request= requestService.findOne(id);
		
		Collection <Material> materials = new ArrayList<Material>();
		
		Painter painter = painterService.getLogged();
		
		result.setPainter(painter);
		result.setMaterials(materials);
		result.setStatus("PENDING");
		result.setRequest(request);
		return result;
	}

	public Collection<Budget> findBudgetsForRequestId(int id) {
		Collection<Budget> result;
		
		result = budgetRepository.findBudgetsForRequestId(id);
		
		return result;
	}

	public Budget acceptBudgetByCustomer(int id) {
		  
		  Budget budget;
		  budget= findOne(id);
		  System.out.println("Budget id principal:"+budget.getId());
		  Assert.notNull(budget);
		  
		  Collection<Budget> budgets;
		  budgets= findBudgetsForRequestId(budget.getRequest().getId());
		  System.out.println("Request id principal:"+budget.getRequest().getId());
		  System.out.println("Lista de budgets:"+budgets);
		  Assert.notNull(budgets);
		
		  for(Budget b:budgets){
			  System.out.println("Budget recorre:"+b.getId());
			  if(b.getId() == budget.getId()){
				  b.setStatus("ACCEPTED");
				  System.out.println("Budget id:"+b.getId()+" aceptado");
				  save(b); 
			  }else{
				  System.out.println("Budget id:"+b.getId()+" rechazado");
				  b.setStatus("REJECTED");
				  save(b);
			  }
		  }
		  return budget;
	}
	
}

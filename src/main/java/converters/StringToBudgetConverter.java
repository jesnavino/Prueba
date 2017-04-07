package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Actor;
import domain.Budget;
import repositories.BudgetRepository;

@Component
@Transactional
public class StringToBudgetConverter implements Converter<String, Budget> {

	@Autowired
	private BudgetRepository budgetRepository;
	
	@Override
	public Budget convert(String arg0) {
		Budget result;
		int id;
		try{
			if(StringUtils.isEmpty(arg0))
				result = null;
			else{
				id=Integer.valueOf(arg0);
				result = budgetRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

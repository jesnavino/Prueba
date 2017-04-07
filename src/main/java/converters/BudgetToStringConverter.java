package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Budget;

@Component
@Transactional
public class BudgetToStringConverter implements Converter<Budget, String>{

	@Override
	public String convert(Budget arg0) {
String result;
		
		if(arg0 == null){
			result = null;
		}else{
			result = String.valueOf(arg0.getId());
		}
		return result;
		
	}

}

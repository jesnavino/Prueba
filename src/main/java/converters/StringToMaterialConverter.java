package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Budget;
import domain.Material;
import repositories.BudgetRepository;
import repositories.MaterialRepository;

@Component
@Transactional
public class StringToMaterialConverter implements Converter<String, Material> {

	@Autowired
	private MaterialRepository materialRepository;
	
	@Override
	public Material convert(String arg0) {
		Material result;
		int id;
		try{
			if(StringUtils.isEmpty(arg0))
				result = null;
			else{
				id=Integer.valueOf(arg0);
				result = materialRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}

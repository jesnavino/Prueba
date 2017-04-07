package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Budget;
import domain.Curriculum;
import repositories.BudgetRepository;
import repositories.CommentRepository;
import repositories.CurriculumRepository;

@Component
@Transactional
public class StringToCurriculumConverter implements Converter<String, Curriculum> {
	
	@Autowired
	private CurriculumRepository curriculumRepository;
	
	@Override
	public Curriculum convert(String arg0) {
		Curriculum result;
		int id;
		try{
			if(StringUtils.isEmpty(arg0))
				result = null;
			else{
				id=Integer.valueOf(arg0);
				result = curriculumRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

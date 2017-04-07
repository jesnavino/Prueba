package converters;



import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Comment;
import domain.Curriculum;

@Component
@Transactional
public class CurriculumToStringConverter implements Converter<Curriculum, String> {

	@Override
	public String convert(Curriculum arg0) {
		String result;
		
		if(arg0 == null){
			result = null;
		}else{
			result = String.valueOf(arg0.getId());
		}
		return result;
	}

}

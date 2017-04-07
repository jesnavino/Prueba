package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import domain.Comment;

@Component
@Transactional
public class CommentToStringConverter implements Converter<Comment, String> {

	@Override
	public String convert(Comment arg0) {
		String result;
		
		if(arg0 == null){
			result = null;
		}else{
			result = String.valueOf(arg0.getId());
		}
		return result;
	}

}

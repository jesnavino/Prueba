package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Comment;
import repositories.CommentRepository;

@Component
@Transactional
public class StringToCommentConverter implements Converter<String, Comment>{

	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public Comment convert(String arg0) {
		Comment result;
		int id;
		try{
			if(StringUtils.isEmpty(arg0))
				result = null;
			else{
				id=Integer.valueOf(arg0);
				result = commentRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

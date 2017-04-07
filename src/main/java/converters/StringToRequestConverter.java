package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import domain.Request;

import repositories.RequestRepository;

@Component
@Transactional
public class StringToRequestConverter implements Converter<String,Request>{
	
	@Autowired RequestRepository RequestRepository;
	
	@Override
	public Request convert(String text) {
		Request  result;
		int id;
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id=Integer.valueOf(text);
				result =RequestRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
		
	}
}

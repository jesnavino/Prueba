package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Material;
import domain.Painter;
import repositories.PainterRepository;

@Component
@Transactional
public class StringToPainterConverter implements Converter<String, Painter>{

	@Autowired
	private PainterRepository painterRepository;
	
	@Override
	public Painter convert(String arg0) {
		Painter result;
		int id;
		try{
			if(StringUtils.isEmpty(arg0))
				result = null;
			else{
				id=Integer.valueOf(arg0);
				result = painterRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

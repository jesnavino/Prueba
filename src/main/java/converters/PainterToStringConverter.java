package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import domain.Painter;

@Component
@Transactional
public class PainterToStringConverter implements Converter<Painter, String>  {

	@Override
	public String convert(Painter arg0) {
		String result;
		
		if(arg0 == null){
			result = null;
		}else{
			result = String.valueOf(arg0.getId());
		}
		return result;
	}

}

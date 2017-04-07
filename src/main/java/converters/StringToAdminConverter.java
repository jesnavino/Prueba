package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Actor;
import domain.Administrator;
import repositories.AdminRepository;

@Component
@Transactional
public class StringToAdminConverter implements Converter<String, Administrator> {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public Administrator convert(String arg0) {
		Administrator result;
		int id;
		try{
			if(StringUtils.isEmpty(arg0))
				result = null;
			else{
				id=Integer.valueOf(arg0);
				result = adminRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

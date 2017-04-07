package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Customer;
import domain.Painter;
import forms.PainterForm;
import repositories.PainterRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class PainterService {

	@Autowired
	private PainterRepository painterRepository;

	public PainterService() {
		super();
	}
	
	public Painter create(){
		
		UserAccount userAccount = new UserAccount();
		Authority aut = new Authority();
		aut.setAuthority(Authority.PAINTER);
		Collection<Authority> authorities = userAccount.getAuthorities();
		authorities.add(aut);
		userAccount.setAuthorities(authorities);
		
		Painter result;
		result = new Painter();
		
		result.setUserAccount(userAccount);
		
		return result;
		
	}
	
	public void save(Painter painter){
		
		painterRepository.save(painter);
		
	}
	
	public Painter getPainterByUsername(String username) {
		return painterRepository.getPainterByUsername(username);
	}
	
	public Painter getLogged() {
		Painter result;
		UserAccount user;
		user = LoginService.getPrincipal();
		result = getPainterByUsername(user.getUsername());
		return result;
	}

	public Painter reconstruct(PainterForm painterForm) {
		Assert.isTrue(painterForm.getRepeatedPassword().equals(painterForm.getPassword()));
		Assert.isTrue(painterForm.getHasAccepted());
		
		Painter painter = this.create();
		painter.setEmail(painterForm.getEmail());
		painter.setPhone(painterForm.getPhone());
		painter.setName(painterForm.getName());
		painter.setSurname(painterForm.getSurname());
		painter.setPostalCode(painterForm.getPostalCode());
		painter.setCodeSS(painterForm.getCodeSS());
		painter.setAverageStart(painterForm.getAverageStar());
		painter.getUserAccount().setUsername(painterForm.getUsername());
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		painter.getUserAccount().setPassword(encoder.encodePassword(painterForm.getPassword(), null));
		
		return painter;
	}

	public Collection<Painter> findAll() {
		Collection<Painter> res;
		
		res = painterRepository.findAll();
		
		return res;
	}

	public Painter findOne(int id) {
		Painter result; 
		
		result = painterRepository.findOne(id);
		
		return result;
	}

	public void calculateAverageStarPainter(Painter painter) {
		Collection<Comment> comments = painter.getComments();
		System.out.println("Tamaño del array de comments es: "+comments.size());
		Double res = 0.0;
		for(Comment c:comments){
			res = res+c.getNumberOfStars();
			System.out.println("Puntuacion de este comentario es "+ c.getNumberOfStars());
		}
		
		painter.setAverageStart(res/comments.size());
		
		System.out.println("El nuevo calculo de la media es: "+painter.getAverageStart());
		save(painter);
	}

}

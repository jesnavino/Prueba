package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Painter;
import domain.Request;
import repositories.CommentRepository;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PainterService painterService;


	public CommentService() {
		super();
	}
	
	public Collection<Comment> listCommentPainterId(int id){
		Collection<Comment> res = new ArrayList<Comment>();
		
		res = commentRepository.findCommentsToPainterId(id);
		
		return res;
	}

	public Comment create(int id) {
		Comment result = new Comment();
		
		Painter painter = painterService.findOne(id);
		Date now = new Date(System.currentTimeMillis()-1000);
		
		result.setMoment(now);
		result.setPainter(painter);
		
		return result;
	}

	public Comment save(Comment comment) {
		Assert.notNull(comment);

		return commentRepository.saveAndFlush(comment);

	}

	public Comment findOne(int id) {
		Comment result;
		
		result = commentRepository.findOne(id);
		
		return result;
	}
}

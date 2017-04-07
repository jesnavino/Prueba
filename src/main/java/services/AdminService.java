package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AdminRepository;
import repositories.CommentRepository;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;


	public AdminService() {
		super();
	}
}

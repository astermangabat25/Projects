package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.User;
import app.repositories.UserRepository;

@Component
public class UserInitializer {
	@Autowired
	private UserRepository repo;
	
	@PostConstruct
	public void init() {
		if(repo.count()==0) {
			User u1 = new User();
			u1.setName("Mama Mia");
			u1.setCellphoneNumber("+639452066029");
			repo.save(u1);
			
			User u2 = new User();
			u2.setName("Mamamama Miaiaiaia");
			u2.setCellphoneNumber("+639452066029");
			repo.save(u2);
		}
	}
}

package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Message;
import app.repositories.MessageRepository;

@Component
public class QuoteInitializer {
	@Autowired
	private MessageRepository repo;
	
	@PostConstruct
	public void init() {
		if(repo.count()==0) {
			Message m1 = new Message();
			m1.setMessage("If you fail to plan, you are planning to fail.");
			m1.setCategory("Encouraging");
			repo.save(m1);
			
			Message m2 = new Message();
			m2.setMessage("The greatest pain that comes from love is loving someone you can never have.");
			m2.setCategory("Sad");
			repo.save(m2);
			
			Message m3 = new Message();
			m3.setMessage("The greatest joy in life is the experience of being alive.");
			m3.setCategory("Happy");
			repo.save(m3);
			
			Message m4 = new Message();
			m4.setMessage("No pain, no gain");
			m4.setCategory("Generic");
			repo.save(m4);
			
			Message m5 = new Message();
			m5.setMessage("Either you run the day or the day runs you");
			m5.setCategory("Encouraging");
			repo.save(m5);
			
		}
	}
}

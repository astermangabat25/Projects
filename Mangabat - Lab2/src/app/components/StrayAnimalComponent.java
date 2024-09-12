package app.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.StrayAnimal;
import app.repositories.AnimalRepository;

@Component
public class StrayAnimalComponent {
	@Autowired
	private AnimalRepository repo;
	
	public Long add(String type, String color, Boolean neutered, String description) {
		StrayAnimal animal = new StrayAnimal();
		animal.setType(type);
		animal.setColor(color);
		animal.setNeutered(neutered);
		animal.setDescription(description);
		
		animal = repo.save(animal);
		
		return animal.getId();
	}
}

package app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.StrayAnimal;

@Repository
public interface AnimalRepository extends JpaRepository<StrayAnimal, Long>{

}

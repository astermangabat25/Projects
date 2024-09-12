package app.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Location;
import app.repositories.LocationRepository;

@Component
public class LocationInitializer {
	@Autowired
	private LocationRepository repo;
	
	@PostConstruct
	public void init() {
		if(repo.count()==0) {
			Location l1 = new Location();
            l1.setLocationName("Faura");
            l1.setLatitude(14.63950059950765);
            l1.setLongtitude(121.07692562007148);
            repo.save(l1);

            Location l2 = new Location();
            l2.setLocationName("Covered Courts");
            l2.setLatitude(14.63678147190153);
            l2.setLongtitude(121.07877436324628);
            repo.save(l2);

            Location l3 = new Location();
            l3.setLocationName("Arete");
            l3.setLatitude(14.641447526589785);
            l3.setLongtitude(121.07591512854832);
            repo.save(l3);

            Location l4 = new Location();
            l4.setLocationName("Bellarmine Hall");
            l4.setLatitude(14.64187605408955);
            l4.setLongtitude(121.0798876469515);
            repo.save(l4);

            Location l5 = new Location();
            l5.setLocationName("Church of Gesu");
            l5.setLatitude(14.640318986539798);
            l5.setLongtitude(121.08000566413632);
            repo.save(l5);

            Location l6 = new Location();
            l6.setLocationName("Blue Eagle Gym");
            l6.setLatitude(14.635855331636769);
            l6.setLongtitude(121.07552101111207);
            repo.save(l6);

            Location l7 = new Location();
            l7.setLocationName("Henry Lee Irwin Theater");
            l7.setLatitude(14.635346676825268);
            l7.setLongtitude(121.07652952160078);
            repo.save(l7);

            Location l8 = new Location();
            l8.setLocationName("Manila Observatory");
            l8.setLatitude(14.636291320536628);
            l8.setLongtitude(121.07753803208949);
            repo.save(l8);

            Location l9 = new Location();
            l9.setLocationName("Escaler Hall");
            l9.setLatitude(14.638273448702805);
            l9.setLongtitude(121.07741106225103);
            repo.save(l9);

            Location l10 = new Location();
            l10.setLocationName("Moro Lorenzo Field");
            l10.setLatitude(14.637102776586033);
            l10.setLongtitude(121.07553283547304);
            repo.save(l10);
		}
		else {
			List<Location> list = repo.findAll();
		}
	}

}

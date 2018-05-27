package musify.collection.repositories.db.seeder;

import musify.collection.models.entities.Artist;
import musify.collection.models.entities.People;
import musify.collection.models.entities.Style;
import musify.collection.repositories.ArtistRepository;
import musify.collection.repositories.PeopleRepository;
import musify.collection.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbSeeder implements CommandLineRunner {

	private final PeopleRepository peopleRepository;
	private final ArtistRepository artistRepository;
	private final StyleRepository styleRepository;

	@Autowired
	public DbSeeder(PeopleRepository peopleRepository, ArtistRepository artistRepository, StyleRepository styleRepository) {
		this.peopleRepository = peopleRepository;
		this.artistRepository = artistRepository;
		this.styleRepository = styleRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		styleRepository.saveAll(getStyles());
		peopleRepository.saveAll(getPeoples());

		Artist artist = new Artist();
		artist.setName("Adele");
		artist.setYear(1980);
		artistRepository.save(artist);
	}

	private Set<People> getPeoples() {
		Set<People> people = new HashSet<>();

		People john = new People();
		john.setName("John");
		john.setYear(1994);
		people.add(john);

		People david = new People();
		david.setName("David");
		david.setYear(1990);
		people.add(david);

		People kara = new People();
		kara.setName("Kara");
		kara.setYear(1994);
		people.add(kara);

		return people;
	}

	private Collection<Style> getStyles() {
		Set<Style> styles = new HashSet<>();

		Style folk = new Style();
		folk.setName("Folk");
		styles.add(folk);

		Style hardRock = new Style();
		hardRock.setName("HardRock");
		styles.add(hardRock);

		return styles;
	}
}

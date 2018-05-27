package musify.collection.repositories;

import musify.collection.models.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
	List<Artist> findAllByStylesNameIgnoreCase(String style);
}

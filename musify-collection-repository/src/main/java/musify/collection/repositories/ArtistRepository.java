package musify.collection.repositories;

import musify.collection.models.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long>, QuerydslPredicateExecutor<Artist> {
	List<Artist> findAllByStylesNameIgnoreCaseContains(String style);
}

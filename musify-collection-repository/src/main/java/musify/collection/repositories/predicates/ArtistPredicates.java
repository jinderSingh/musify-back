package musify.collection.repositories.predicates;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import musify.collection.models.entities.QArtist;
import musify.collection.models.filters.ArtistFilter;
import org.springframework.util.CollectionUtils;

import java.util.List;

public final class ArtistPredicates {

	private ArtistPredicates() {
	}

	public static Predicate artistIdsIn(List<Long> artistIds) {
		return QArtist.artist.id.in(artistIds);
	}

	public static Predicate artistStyleNameContains(String names) {
		return QArtist.artist.styles.any().name.containsIgnoreCase(names);
	}

	public static BooleanBuilder buildArtistBooleanBuilder(ArtistFilter artistFilter) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (null != artistFilter.getStyle()){
			booleanBuilder.and(artistStyleNameContains(artistFilter.getStyle()));
		}

		if (!CollectionUtils.isEmpty(artistFilter.getArtistIds())) {
			booleanBuilder.and(artistIdsIn(artistFilter.getArtistIds()));
		}

		if (artistFilter.isRevertFilter()) {
			booleanBuilder.not();
		}

		return booleanBuilder;
	}
}

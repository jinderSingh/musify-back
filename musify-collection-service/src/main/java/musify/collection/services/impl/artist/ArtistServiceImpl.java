package musify.collection.services.impl.artist;

import musify.collection.models.dtos.ArtistDto;
import musify.collection.models.dtos.PeopleDto;
import musify.collection.models.dtos.StyleDto;
import musify.collection.models.entities.Artist;
import musify.collection.models.entities.People;
import musify.collection.models.entities.Style;
import musify.collection.models.entities.common.BaseEntity;
import musify.collection.models.filters.ArtistFilter;
import musify.collection.repositories.ArtistRepository;
import musify.collection.repositories.PeopleRepository;
import musify.collection.repositories.StyleRepository;
import musify.collection.repositories.predicates.ArtistPredicates;
import musify.collection.services.ArtistService;
import musify.collection.services.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

	private final ArtistRepository artistRepository;
	private final StyleRepository styleRepository;
	private final PeopleRepository peopleRepository;

	private final Converter<Artist, ArtistDto> converter;

	@Autowired
	public ArtistServiceImpl(ArtistRepository artistRepository, StyleRepository styleRepository, PeopleRepository peopleRepository, Converter<Artist, ArtistDto> converter) {
		this.artistRepository = artistRepository;
		this.styleRepository = styleRepository;
		this.peopleRepository = peopleRepository;
		this.converter = converter;
	}

	@Override
	public ArtistDto add(ArtistDto data) {
		Artist artist = converter.toDomainModel(data, Artist.class);

		artist.getStyles().addAll(getStyles(data.getStyles()));

		artist.getRelatedArtists().addAll(getRelatedArtist(data.getRelatedArtists()));

		List<People> people = getPeople(data.getPeople());
		people.forEach(artist::addPeople);

		return converter.toApiModelDeep(artistRepository.save(artist), ArtistDto.class);
	}

	@Override
	public ArtistDto update(ArtistDto data) {
		Artist artist = artistRepository.findById(data.getId()).orElseThrow(EntityNotFoundException::new);

		if (artist.getName() != null && !artist.getName().equals(data.getName())) {
			artist.setName(data.getName());
		}
		if (artist.getYear() != null && !artist.getYear().equals(data.getYear())) {
			artist.setYear(data.getYear());
		}

		if (CollectionUtils.isEmpty(data.getPeople())) {
			artist.removeAllPeople();
		} else {
			List<People> peopleToAdd = getPeople(data.getPeople());
			Set<People> peopleToRemove = artist.getPeople().stream()
					.filter(people -> peopleToAdd.stream().noneMatch(person -> person.getId().equals(people.getId())))
					.collect(Collectors.toSet());

			peopleToRemove.forEach(artist::removePeople);

			peopleToAdd.forEach(artist::addPeople);
		}

		if (CollectionUtils.isEmpty(data.getStyles())) {
			artist.getStyles().clear();
		} else {
			List<Style> styles = getStyles(data.getStyles());
			artist.getStyles().addAll(styles);
		}

		if (CollectionUtils.isEmpty(data.getRelatedArtists())) {
			artist.getRelatedArtists().clear();
		} else {
			List<Artist> relatedArtist = getRelatedArtist(data.getRelatedArtists());
			artist.getRelatedArtists().addAll(relatedArtist);
		}


		return converter.toApiModelDeep(artistRepository.save(artist), ArtistDto.class);
	}

	@Override
	public void delete(Long id) {
		artistRepository.deleteById(id);
	}

	@Override
	public ArtistDto findOne(Long id) {
		return converter.toApiModelDeep(
				artistRepository.findById(id)
						.orElseThrow(EntityNotFoundException::new),
				ArtistDto.class
		);
	}

	@Override
	public List<ArtistDto> findAll() {
		return new ArrayList<>(
				converter.toApiModel(artistRepository.findAll(), ArtistDto.class)
		);
	}

	@Override
	public List<ArtistDto> filter(ArtistFilter artistFilter) {
//		List<Artist> allByStylesNameIgnoreCase = artistRepository.findAllByStylesNameIgnoreCaseContains(artistFilter.getStyle());

		Iterable<Artist> artists = artistRepository.findAll(ArtistPredicates.buildArtistBooleanBuilder(artistFilter));

		return new ArrayList<>(
				converter.toApiModel(
						iterableToList(artists),
						ArtistDto.class
				)
		);
	}

	private List<People> getPeople(List<PeopleDto> data) {
		if (CollectionUtils.isEmpty(data)) {
			return new ArrayList<>();
		}
		List<Long> peopleIds = data.stream().map(PeopleDto::getId).collect(Collectors.toList());
		return getEntitiesByIds(peopleIds, peopleRepository);
	}

	private List<Style> getStyles(List<StyleDto> data) {
		if (CollectionUtils.isEmpty(data)) {
			return new ArrayList<>();
		}
		List<Long> styleIds = data.stream().map(StyleDto::getId).collect(Collectors.toList());
		return getEntitiesByIds(styleIds, styleRepository);
	}

	private List<Artist> getRelatedArtist(List<ArtistDto> data) {
		if (CollectionUtils.isEmpty(data)) {
			return new ArrayList<>();
		}
		List<Long> artistIds = data.stream().map(ArtistDto::getId).collect(Collectors.toList());
		return getEntitiesByIds(artistIds, artistRepository);
	}

	private <T extends BaseEntity, U extends JpaRepository<T, Long>> List<T> getEntitiesByIds(List<Long> ids, U repository) {
		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<>();
		}
		return repository.findAllById(ids);
	}

	private <T> List<T> iterableToList(Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false)
				.collect(Collectors.toList());
	}
}

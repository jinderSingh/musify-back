package musify.collection.services.impl.people;


import musify.collection.models.dtos.PeopleDto;
import musify.collection.models.entities.People;
import musify.collection.repositories.PeopleRepository;
import musify.collection.services.PeopleService;
import musify.collection.services.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {

	private final PeopleRepository peopleRepository;
	private final Converter<People, PeopleDto> converter;

	@Autowired
	public PeopleServiceImpl(PeopleRepository peopleRepository, Converter<People, PeopleDto> converter) {
		this.peopleRepository = peopleRepository;
		this.converter = converter;
	}

	@Override
	public PeopleDto add(PeopleDto data) {
		People people = converter.toDomainModel(data, People.class);
		return converter.toApiModelDeep(peopleRepository.save(people), PeopleDto.class);
	}

	@Override
	public PeopleDto update(PeopleDto data) {
		People people = converter.toDomainModel(data, People.class);
		return converter.toApiModelDeep(peopleRepository.save(people), PeopleDto.class);
	}

	@Override
	public void delete(Long id) {
		peopleRepository.deleteById(id);
	}

	@Override
	public PeopleDto findOne(Long id) {
		Optional<People> people = peopleRepository.findById(id);
		return converter.toApiModelDeep(
				people.orElseThrow(EntityNotFoundException::new),
				PeopleDto.class
		);
	}

	@Override
	public List<PeopleDto> findAll() {
		return new ArrayList<>(converter.toApiModelDeep(peopleRepository.findAll(), PeopleDto.class));
	}
}

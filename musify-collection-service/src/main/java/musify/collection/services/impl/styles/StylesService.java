package musify.collection.services.impl.styles;

import musify.collection.models.dtos.StyleDto;
import musify.collection.models.entities.Style;
import musify.collection.repositories.StyleRepository;
import musify.collection.services.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StylesService implements musify.collection.services.StylesService {

	private final StyleRepository styleRepository;
	private final Converter<Style, StyleDto> converter;

	@Autowired
	public StylesService(StyleRepository styleRepository, Converter<Style, StyleDto> converter) {
		this.styleRepository = styleRepository;
		this.converter = converter;
	}

	@Override
	public StyleDto findOne(Long id) {
		return converter.toApiModel(styleRepository.findById(id)
				.orElseThrow(EntityNotFoundException::new), StyleDto.class);
	}

	@Override
	public List<StyleDto> findAll() {
		return new ArrayList<>(converter.toApiModel(styleRepository.findAll(), StyleDto.class));
	}
}

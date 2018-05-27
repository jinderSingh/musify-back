package musify.collection.services.converter;


import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Converter<S, D> {

	public D toApiModelDeep(S source, Class<D> destinationClazz) {
		return deepModelMapper().map(source, destinationClazz);
	}

	public Collection<D> toApiModelDeep(Collection<S> source, Class<D> destinationClazz) {
		return source.stream()
				.map(s -> this.toApiModelDeep(s, destinationClazz))
				.collect(Collectors.toSet());
	}

	public S toDomainModelDeep(D source, Class<S> destinationClazz) {
		return deepModelMapper().map(source, destinationClazz);
	}

	public Set<S> toDomainModelDeep(Collection<D> source, Class<S> destinationClazz) {
		return source.stream()
				.map(d -> this.toDomainModelDeep(d, destinationClazz))
				.collect(Collectors.toSet());
	}

	public D toApiModel(S source, Class<D> destinationClazz) {
		return modelMapper().map(source, destinationClazz);
	}

	public Collection<D> toApiModel(Collection<S> source, Class<D> destinationClazz) {
		return source.stream()
				.map(s -> this.toApiModel(s, destinationClazz))
				.collect(Collectors.toSet());
	}

	public S toDomainModel(D source, Class<S> destinationClazz) {
		return modelMapper().map(source, destinationClazz);
	}

	public Set<S> toDomainModel(Collection<D> source, Class<S> destinationClazz) {
		return source.stream()
				.map(d -> this.toDomainModel(d, destinationClazz))
				.collect(Collectors.toSet());
	}


	private ModelMapper deepModelMapper() {
		ModelMapper deepModelMapper = new ModelMapper();
		deepModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return deepModelMapper;
	}

	private ModelMapper modelMapper() {

		Condition nullAndSimpleProperties = mappingContext -> mappingContext.getSource() != null &&
				!Collection.class.isAssignableFrom(mappingContext.getDestinationType());

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.getConfiguration().setPropertyCondition(nullAndSimpleProperties);
		return modelMapper;
	}
}

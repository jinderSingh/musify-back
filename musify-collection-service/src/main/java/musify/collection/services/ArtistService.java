package musify.collection.services;

import musify.collection.models.dtos.ArtistDto;
import musify.collection.models.filters.ArtistFilter;
import musify.collection.services.common.services.CrudService;

import java.util.List;

public interface ArtistService extends CrudService<Long, ArtistDto> {
	List<ArtistDto> filter(ArtistFilter artistFilter);
}

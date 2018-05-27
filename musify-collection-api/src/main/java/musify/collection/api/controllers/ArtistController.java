package musify.collection.api.controllers;

import musify.collection.api.constants.ApiConstants;
import musify.collection.models.dtos.ArtistDto;
import musify.collection.models.filters.ArtistFilter;
import musify.collection.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ArtistController.API_URI)
public class ArtistController {

	public final static String API_URI = ApiConstants.API_BASE_URI + "/artists";
	private static final String ID_MAPPING = "/{id}";
	private static final String FILTER = "/filter";

	private final ArtistService artistService;

	@Autowired
	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<ArtistDto> findAllArtists() {
		return artistService.findAll();
	}

	@GetMapping(value = ID_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ArtistDto findById(@PathVariable Long id) {
		return artistService.findOne(id);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ArtistDto addArtist(@RequestBody ArtistDto artistDto) {
		return artistService.add(artistDto);
	}

	@PutMapping(value = ID_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ArtistDto updateArtist(@RequestBody ArtistDto artistDto) {
		return artistService.update(artistDto);
	}

	@DeleteMapping(value = ID_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteArtist(@PathVariable Long id) {
		artistService.delete(id);
	}

	@GetMapping(value = FILTER, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<ArtistDto> filterByStyle(@ModelAttribute ArtistFilter artistFilter) {
		return artistService.filter(artistFilter);
	}
}

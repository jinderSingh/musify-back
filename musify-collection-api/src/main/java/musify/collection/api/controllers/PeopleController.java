package musify.collection.api.controllers;

import musify.collection.api.constants.ApiConstants;
import musify.collection.models.dtos.PeopleDto;
import musify.collection.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PeopleController.API_URI)
public class PeopleController {

	public final static String API_URI = ApiConstants.API_BASE_URI + "/people";
	private static final String ID_MAPPING = "/{id}";

	private final PeopleService peopleService;

	@Autowired
	public PeopleController(PeopleService peopleService) {
		this.peopleService = peopleService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<PeopleDto> findAllPeople() {
		return peopleService.findAll();
	}

	@GetMapping(value = ID_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public PeopleDto findById(@PathVariable Long personId) {
		return peopleService.findOne(personId);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public PeopleDto addPeople(@RequestBody PeopleDto peopleDto) {
		return peopleService.add(peopleDto);
	}

	@PutMapping(value = ID_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public PeopleDto updatePeople(@RequestBody PeopleDto peopleDto) {
		return peopleService.update(peopleDto);
	}

	@DeleteMapping(value = ID_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePeople(@PathVariable Long personId) {
		peopleService.delete(personId);
	}

}

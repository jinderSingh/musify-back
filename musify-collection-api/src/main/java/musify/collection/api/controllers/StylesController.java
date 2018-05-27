package musify.collection.api.controllers;


import musify.collection.api.constants.ApiConstants;
import musify.collection.models.dtos.StyleDto;
import musify.collection.services.StylesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(StylesController.API_URI)
public class StylesController {

	public final static String API_URI = ApiConstants.API_BASE_URI + "/styles";
	private static final String FILTER = "/filter";

	private final StylesService stylesService;

	@Autowired
	public StylesController(StylesService stylesService) {
		this.stylesService = stylesService;
	}

	@GetMapping
	public List<StyleDto> getAll() {
		return stylesService.findAll();
	}
}

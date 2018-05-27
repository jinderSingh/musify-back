package musify.collection.models.dtos;

import java.io.Serializable;
import java.util.List;

public class ArtistDto implements Serializable {

	private Long id;

	private String name;

	private Integer year;

	private List<PeopleDto> people;

	private List<StyleDto> styles;

	private List<ArtistDto> relatedArtists;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<PeopleDto> getPeople() {
		return people;
	}

	public void setPeople(List<PeopleDto> people) {
		this.people = people;
	}

	public List<StyleDto> getStyles() {
		return styles;
	}

	public void setStyles(List<StyleDto> styles) {
		this.styles = styles;
	}

	public List<ArtistDto> getRelatedArtists() {
		return relatedArtists;
	}

	public void setRelatedArtists(List<ArtistDto> relatedArtists) {
		this.relatedArtists = relatedArtists;
	}
}

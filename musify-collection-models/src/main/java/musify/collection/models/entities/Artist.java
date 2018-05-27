package musify.collection.models.entities;

import musify.collection.models.entities.common.BaseEntity;
import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Artist extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String name;

	@Column
	private Integer year;

	@OneToMany(mappedBy = "artist", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	private Set<People> people;

	@ManyToMany
	private Set<Style> styles;

	@ManyToMany
	@JoinTable(name = "related_artist",
			joinColumns = {@JoinColumn(name = "artist_id")},
			inverseJoinColumns = {@JoinColumn(name = "related_artist_id")})
	private Set<Artist> relatedArtists;

	@ManyToMany(mappedBy = "relatedArtists")
	private Set<Artist> artists;

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

	public Set<People> getPeople() {
		people = CollectionUtils.isEmpty(people) ? new HashSet<>() : people;
		return people;
	}

	public void setPeople(Set<People> people) {
		this.people = people;
	}

	public Set<Style> getStyles() {
		styles = CollectionUtils.isEmpty(styles) ? new HashSet<>() : styles;
		return styles;
	}

	public void setStyles(Set<Style> styles) {
		this.styles = styles;
	}

	public Set<Artist> getRelatedArtists() {
		relatedArtists = CollectionUtils.isEmpty(relatedArtists) ? new HashSet<>() : relatedArtists;
		return relatedArtists;
	}

	public void setRelatedArtists(Set<Artist> relatedArtists) {

		this.relatedArtists = relatedArtists;
	}

	public Set<Artist> getArtists() {
		artists = CollectionUtils.isEmpty(artists) ? new HashSet<>() : artists;
		return artists;
	}

	public void setArtists(Set<Artist> artists) {
		this.artists = artists;
	}

	public void addPeople(People person) {
		if (getPeople().contains(person)) {
			return;
		}
		getPeople().add(person);
		person.setArtist(this);
	}

	public void removePeople(People person) {
		if (!getPeople().contains(person))
			return;
		person.setArtist(null);
		getPeople().remove(person);
	}

	public void removeAllPeople() {
		getPeople().forEach(this::removePeople);
	}
}

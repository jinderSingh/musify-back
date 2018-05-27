package musify.collection.models.filters;

import java.util.List;

public class ArtistFilter {
	private List<Long> artistIds;
	private String style;
	private String name;
	private Integer year;
	private boolean revertFilter;

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
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

	public boolean isRevertFilter() {
		return revertFilter;
	}

	public void setRevertFilter(boolean revertFilter) {
		this.revertFilter = revertFilter;
	}

	public List<Long> getArtistIds() {
		return artistIds;
	}

	public void setArtistIds(List<Long> artistIds) {
		this.artistIds = artistIds;
	}
}

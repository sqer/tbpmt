package pl.sqer.dto.resolution;

/**
 * The enum ResolutionEnum.
 */
public enum ResolutionEnum {

	UNRESOLVED("Unresolved"), RESOLVED("Resolved"), INCOMPLETE("Incomplete"), FIXED(
			"Fixed"), DUPLICATE("Duplicate"), WONT_FIX("Wont Fix"), CANNOT_REPRODUCE(
			"Cannot reproduce"), WORKS_FINE("Works fine"), IN_PROGRESS(
			"In progress");

	private String name;

	private ResolutionEnum(final String name) {
		this.name = name;
	}

	private ResolutionEnum() {

	}

	public String getName() {
		return this.name;
	}
}

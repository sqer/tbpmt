package pl.sqer.dto.type;

/**
 * The enum TypeEnum.
 */
public enum TypeEnum {

	BUG("Bug"), CHANGE_REQUEST("Change Request"), TEST("Test"), NEW_ITEM(
			"New Item"), TASK("Task");

	private String name;

	private TypeEnum(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}

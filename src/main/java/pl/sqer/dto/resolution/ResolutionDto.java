package pl.sqer.dto.resolution;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.sqer.dto.common.TBPMTDto;

/**
 * The Class ResolutionDto.
 */
public class ResolutionDto implements TBPMTDto {

	/** Serial UID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof ResolutionDto)) {
			return false;
		}
		final ResolutionDto rhs = (ResolutionDto) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.name, rhs.name)
				.append(this.description, rhs.description)
				.append(this.id, rhs.id).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(694758811, -440022377)
				.appendSuper(super.hashCode()).append(this.name)
				.append(this.description).append(this.id).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
				.append("name", this.name).append("id", this.id)
				.append("description", this.description).toString();
	}

}

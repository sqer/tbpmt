package pl.sqer.dao.entity.resolution;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ResolutionEntity.
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "ResolutionEntity.selectAll", query = "SELECT t FROM ResolutionEntity t"),
		@NamedQuery(name = "ResolutionEntity.findById", query = "SELECT t FROM ResolutionEntity t WHERE t.id= :id"),
		@NamedQuery(name = "ResolutionEntity.findByName", query = "SELECT t FROM ResolutionEntity t WHERE t.name= :name") })
@Table(name = "resolution")
public class ResolutionEntity implements Serializable {

	/** Serial UID. */
	private static final long serialVersionUID = 2681936994683932647L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	/** The name. */
	@Column(name = "name", nullable = false, unique = false)
	private String name;

	/** The description. */
	@Column(name = "description", nullable = false, unique = false)
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-992158373, 1830349053)
				.appendSuper(super.hashCode()).append(this.name)
				.append(this.description).append(this.id).toHashCode();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof ResolutionEntity)) {
			return false;
		}
		final ResolutionEntity rhs = (ResolutionEntity) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.name, rhs.name)
				.append(this.description, rhs.description)
				.append(this.id, rhs.id).isEquals();
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

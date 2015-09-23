package pl.sqer.dao.entity.type;

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
 * The Class TypeEntity.
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "TypeEntity.selectAll", query = "SELECT t FROM TypeEntity t"),
		@NamedQuery(name = "TypeEntity.countAll", query = "SELECT count(t.id) FROM TypeEntity t"),
		@NamedQuery(name = "TypeEntity.findById", query = "SELECT t FROM TypeEntity t WHERE t.id= :id"),
		@NamedQuery(name = "TypeEntity.findByName", query = "SELECT t FROM TypeEntity t WHERE t.name= :name") })
@Table(name = "type")
public class TypeEntity implements Serializable {

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

	/** The icon. */
	@Column(name = "icon", nullable = true, unique = false)
	private String icon;

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
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(final String icon) {
		this.icon = icon;
	}

	/**
	 * Equals.
	 *
	 * @param object
	 *            the object
	 * @return true, if successful
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof TypeEntity)) {
			return false;
		}
		final TypeEntity rhs = (TypeEntity) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.name, rhs.name).append(this.icon, rhs.icon)
				.append(this.description, rhs.description)
				.append(this.id, rhs.id).isEquals();
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1216182001, -1331710573)
				.appendSuper(super.hashCode()).append(this.name)
				.append(this.icon).append(this.description).append(this.id)
				.toHashCode();
	}

	/**
	 * To string.
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
				.append("name", this.name).append("id", this.id)
				.append("description", this.description)
				.append("icon", this.icon).toString();
	}

}

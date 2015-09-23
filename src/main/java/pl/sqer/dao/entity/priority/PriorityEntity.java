package pl.sqer.dao.entity.priority;

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
 * The Class PriorityEntity.
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "PriorityEntity.selectAll", query = "SELECT t FROM PriorityEntity t"),
		@NamedQuery(name = "PriorityEntity.countAll", query = "SELECT count(t.id) FROM PriorityEntity t"),
		@NamedQuery(name = "PriorityEntity.findById", query = "SELECT t FROM PriorityEntity t WHERE t.id= :id"),
		@NamedQuery(name = "PriorityEntity.findByName", query = "SELECT t FROM PriorityEntity t WHERE t.name= :name") })
@Table(name = "priority")
public class PriorityEntity implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 2681936994683932647L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	/** The title. */
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
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof PriorityEntity)) {
			return false;
		}
		final PriorityEntity rhs = (PriorityEntity) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.name, rhs.name).append(this.icon, rhs.icon)
				.append(this.description, rhs.description)
				.append(this.id, rhs.id).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1084846101, 1677653027)
				.appendSuper(super.hashCode()).append(this.name)
				.append(this.icon).append(this.description).append(this.id)
				.toHashCode();
	}

	/**
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

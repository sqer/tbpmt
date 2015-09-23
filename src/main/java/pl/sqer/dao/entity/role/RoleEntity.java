package pl.sqer.dao.entity.role;

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

import pl.sqer.dto.common.TBPMTDto;

/**
 * The Class RoleEntity.
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "RoleEntity.selectAll", query = "SELECT t FROM RoleEntity t ORDER BY t.name"),
		@NamedQuery(name = "RoleEntity.countAll", query = "SELECT count(t.id) FROM RoleEntity t"),
		@NamedQuery(name = "RoleEntity.findById", query = "SELECT t FROM RoleEntity t WHERE t.id= :id") })
@Table(name = "role")
public class RoleEntity implements TBPMTDto {

	/** Serial UID. */
	private static final long serialVersionUID = 2681936994683932647L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	/** The name. */
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	/** The description. */
	@Column(name = "description", nullable = false, unique = true)
	private String description;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof RoleEntity)) {
			return false;
		}
		RoleEntity rhs = (RoleEntity) object;
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
		return new HashCodeBuilder(-1550030045, -1656443705)
				.appendSuper(super.hashCode()).append(this.name)
				.append(this.description).append(this.id).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name)
				.append("id", this.id).append("description", this.description)
				.toString();
	}

}

package pl.sqer.dao.entity.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pl.sqer.dao.entity.role.RoleEntity;

/**
 * The Class UserEntity.
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "UserEntity.selectAll", query = "SELECT t FROM UserEntity t"),
		@NamedQuery(name = "UserEntity.countAll", query = "SELECT count(t.id) FROM UserEntity t"),
		@NamedQuery(name = "UserEntity.findById", query = "SELECT t FROM UserEntity t WHERE t.id= :id"),
		@NamedQuery(name = "UserEntity.findByUsername", query = "SELECT t FROM UserEntity t WHERE t.username like :text"),
		@NamedQuery(name = "UserEntity.findByUsenameUnique", query = "SELECT t FROM UserEntity t WHERE t.username= :text"),
		@NamedQuery(name = "UserEntity.selectUserEncodedPassword", query = "SELECT t.password FROM UserEntity t WHERE t.id= :id"),
		@NamedQuery(name = "UserEntity.authenticate", query = "SELECT 1 FROM UserEntity t WHERE t.username= :login and t.password= :password") })
@Table(name = "users")
public class UserEntity implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 2681936994683932647L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	/** The username. */
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	/** The password. */
	@Column(name = "password", nullable = false, unique = false)
	private String password;

	/** The name. */
	@Column(name = "name", nullable = false, unique = false)
	private String name;

	/** The surename. */
	@Column(name = "surename", nullable = false, unique = false)
	private String surename;

	/** The dateOfBirth. */
	@Column(name = "date_of_birth", nullable = false, unique = false)
	private Date dateOfBirth;

	/** The email. */
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	/** The about. */
	@Column(name = "about", nullable = true, unique = false)
	private String about;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "mapping_user_role", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, updatable = false) })
	private Set<RoleEntity> roles;

	public UserEntity() {

	}

	public UserEntity(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
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
	 * @return the surename
	 */
	public String getSurename() {
		return surename;
	}

	/**
	 * @param surename
	 *            the surename to set
	 */
	public void setSurename(final String surename) {
		this.surename = surename;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(final Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(final Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(final String about) {
		this.about = about;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserEntity other = (UserEntity) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", email="
				+ email + "]";
	}

}

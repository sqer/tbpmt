package pl.sqer.dao.entity.comment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import pl.sqer.dao.entity.task.TaskEntity;
import pl.sqer.dao.entity.user.UserEntity;

/**
 * The Class CommentEntity.
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "CommentEntity.selectAll", query = "SELECT t FROM CommentEntity t"),
		@NamedQuery(name = "CommentEntity.countAll", query = "SELECT count(t.id) FROM CommentEntity t"),
		@NamedQuery(name = "CommentEntity.findByUser", query = "SELECT t FROM CommentEntity t WHERE t.creator.id= :userId"),
		@NamedQuery(name = "CommentEntity.findByTask", query = "SELECT t FROM CommentEntity t WHERE t.task.id= :taskId") })
@Table(name = "comments")
public class CommentEntity implements Serializable {

	/** Serial UID. */
	private static final long serialVersionUID = 2681936994683932647L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	/** The content. */
	@Column(name = "content")
	private String content;

	/** The creator. */
	@ManyToOne
	@JoinColumn(name = "creator_id", nullable = false, unique = false)
	private UserEntity creator;

	/** The task. */
	@ManyToOne
	@JoinColumn(name = "task_id", nullable = false, unique = false)
	private TaskEntity task;

	/** The creation date. */
	@Column(name = "creation_date", nullable = false, unique = false)
	private Date creationDate;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Gets the task.
	 *
	 * @return the task
	 */
	public TaskEntity getTask() {
		return task;
	}

	/**
	 * Sets the task.
	 *
	 * @param task
	 *            the new task
	 */
	public void setTask(final TaskEntity task) {
		this.task = task;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content
	 *            the new content
	 */
	public void setContent(final String content) {
		this.content = content;
	}

	/**
	 * Gets the creator.
	 *
	 * @return the creator
	 */
	public UserEntity getCreator() {
		return creator;
	}

	/**
	 * Sets the creator.
	 *
	 * @param creator
	 *            the new creator
	 */
	public void setCreator(final UserEntity creator) {
		this.creator = creator;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate
	 *            the new creation date
	 */
	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		hashCodeBuilder.append(creationDate);
		hashCodeBuilder.append(creator);
		hashCodeBuilder.append(content);
		hashCodeBuilder.append(task);
		hashCodeBuilder.append(id);
		return hashCodeBuilder.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CommentEntity)) {
			return false;
		}
		final CommentEntity other = (CommentEntity) obj;
		final EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(id, other.id);
		equalsBuilder.append(content, other.content);
		equalsBuilder.append(creationDate, other.creationDate);
		equalsBuilder.append(creator, other.creator);
		equalsBuilder.append(task, other.task);
		return equalsBuilder.isEquals();
	}
}

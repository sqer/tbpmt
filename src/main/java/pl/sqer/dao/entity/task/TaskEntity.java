package pl.sqer.dao.entity.task;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import pl.sqer.dao.entity.comment.CommentEntity;
import pl.sqer.dao.entity.priority.PriorityEntity;
import pl.sqer.dao.entity.resolution.ResolutionEntity;
import pl.sqer.dao.entity.taskTime.TaskTimeEntity;
import pl.sqer.dao.entity.type.TypeEntity;
import pl.sqer.dao.entity.user.UserEntity;

/**
 * The Class TaskEntity.
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "TaskEntity.selectAll", query = "SELECT t FROM TaskEntity t"),
		@NamedQuery(name = "TaskEntity.countAll", query = "SELECT count(t.id) FROM TaskEntity t"),
		@NamedQuery(name = "TaskEntity.findById", query = "SELECT t FROM TaskEntity t WHERE t.id= :id"),
		@NamedQuery(name = "TaskEntity.findByUser", query = "SELECT t FROM TaskEntity t WHERE t.assignee.id= :userId"),
		@NamedQuery(name = "TaskEntity.findByResolution", query = "SELECT t FROM TaskEntity t WHERE t.resolution.name IN (:resolutions)"),
		@NamedQuery(name = "TaskEntity.findByUserAndResolution", query = "SELECT t FROM TaskEntity t WHERE t.assignee.id= :userId AND t.resolution.name= :resolution"),
		@NamedQuery(name = "TaskEntity.countByResolution", query = "SELECT COUNT(t.id) FROM TaskEntity t WHERE t.resolution.name= :resolution"),
		@NamedQuery(name = "TaskEntity.countByType", query = "SELECT COUNT(t.id) FROM TaskEntity t WHERE t.type.name= :type"),
		@NamedQuery(name = "TaskEntity.selectTaskNumber", query = "SELECT max(t.number) FROM TaskEntity t") })
@Table(name = "tasks")
public class TaskEntity implements Serializable {

	/** Serial UID. */
	private static final long serialVersionUID = 2681936994683932647L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	/** The title. */
	@Column(name = "title", nullable = false, unique = false)
	private String title;

	/** The description. */
	@Column(name = "description", nullable = false, unique = false)
	private String description;

	/** The number. */
	@Column(name = "number", nullable = false, unique = true)
	private Long number;

	/** The creation date. */
	@Column(name = "creation_date", nullable = false, unique = false)
	private Date creationDate;

	/** The last update date. */
	@Column(name = "last_update_date", nullable = false, unique = false)
	private Date lastUpdateDate = new Date();

	/** The priority. */
	@ManyToOne
	@JoinColumn(name = "priority_id")
	private PriorityEntity priority;

	/** The type. */
	@ManyToOne
	@JoinColumn(name = "type_id")
	private TypeEntity type;

	/** The creator. */
	@ManyToOne
	@JoinColumn(name = "creator_id", nullable = false, unique = false)
	private UserEntity creator;

	/** The assignee. */
	@ManyToOne
	@JoinColumn(name = "assignee_id", nullable = false, unique = false)
	private UserEntity assignee;

	/** The resolution. */
	@ManyToOne
	@JoinColumn(name = "resolution_id")
	private ResolutionEntity resolution;

	@Column(name = "estimated", nullable = true, unique = false)
	private BigDecimal estimated;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	private List<TaskTimeEntity> taskTimes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	private List<CommentEntity> comments;

	public TaskEntity() {

	}

	public TaskEntity(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(final Long number) {
		this.number = number;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @param lastUpdateDate
	 *            the lastUpdateDate to set
	 */
	public void setLastUpdateDate(final Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public UserEntity getCreator() {
		return creator;
	}

	public void setCreator(final UserEntity creator) {
		this.creator = creator;
	}

	public UserEntity getAssignee() {
		return assignee;
	}

	public void setAssignee(final UserEntity assignee) {
		this.assignee = assignee;
	}

	/**
	 * @return the priority
	 */
	public PriorityEntity getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(final PriorityEntity priority) {
		this.priority = priority;
	}

	/**
	 * @return the type
	 */
	public TypeEntity getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final TypeEntity type) {
		this.type = type;
	}

	/**
	 * @return the resolution
	 */
	public ResolutionEntity getResolution() {
		return resolution;
	}

	/**
	 * @param resolution
	 *            the resolution to set
	 */
	public void setResolution(final ResolutionEntity resolution) {
		this.resolution = resolution;
	}

	public BigDecimal getEstimated() {
		return estimated;
	}

	public void setEstimated(final BigDecimal estimated) {
		this.estimated = estimated;
	}

	public List<TaskTimeEntity> getTaskTimes() {
		return taskTimes;
	}

	public void setTaskTimes(final List<TaskTimeEntity> taskTimes) {
		this.taskTimes = taskTimes;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(final List<CommentEntity> comments) {
		this.comments = comments;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof TaskEntity)) {
			return false;
		}
		final TaskEntity rhs = (TaskEntity) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.creator, rhs.creator)
				.append(this.lastUpdateDate, rhs.lastUpdateDate)
				.append(this.estimated, rhs.estimated)
				.append(this.description, rhs.description)
				.append(this.title, rhs.title)
				.append(this.creationDate, rhs.creationDate)
				.append(this.priority, rhs.priority)
				.append(this.type, rhs.type)
				.append(this.resolution, rhs.resolution)
				.append(this.number, rhs.number).append(this.id, rhs.id)
				.append(this.comments, rhs.comments)
				.append(this.assignee, rhs.assignee).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(377841931, -1702710771)
				.appendSuper(super.hashCode()).append(this.creator)
				.append(this.lastUpdateDate).append(this.estimated)
				.append(this.description).append(this.title)
				.append(this.creationDate).append(this.priority)
				.append(this.comments).append(this.type)
				.append(this.resolution).append(this.number).append(this.id)
				.append(this.assignee).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("lastUpdateDate", this.lastUpdateDate)
				.append("resolution", this.resolution)
				.append("estimated", this.estimated).append("id", this.id)
				.append("assignee", this.assignee)
				.append("description", this.description)
				.append("creator", this.creator).append("number", this.number)
				.append("creationDate", this.creationDate)
				.append("title", this.title).append("type", this.type)
				.append("comments", this.comments)
				.append("priority", this.priority).toString();
	}

}

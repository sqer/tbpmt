package pl.sqer.dto.tasks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import pl.sqer.dto.comment.CommentDto;
import pl.sqer.dto.common.TBPMTDto;
import pl.sqer.dto.priority.PriorityDto;
import pl.sqer.dto.resolution.ResolutionDto;
import pl.sqer.dto.type.TypeDto;
import pl.sqer.dto.users.UserDto;

/**
 * The Class TasksDto.
 */
public class TaskDto implements TBPMTDto {

	/** Serial UID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Integer id;

	/** The title. */
	private String title;

	/** The description. */
	private String description;

	/** The number. */
	private Long number;

	/** The creation date. */
	private Date creationDate;

	/** The last update date. */
	private Date lastUpdateDate;

	/** The creator. */
	private UserDto creator;

	/** The assignee. */
	private UserDto assignee;

	/** The priority. */
	private PriorityDto priority;

	/** The type. */
	private TypeDto type;

	/** The resolution. */
	private ResolutionDto resolution;

	/** The estimated. */
	private BigDecimal estimated;

	/** The taskTimes. */
	private List<TaskTimeDto> taskTimes;

	/** The comments. */
	private List<CommentDto> comments;

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

	public UserDto getCreator() {
		return creator;
	}

	public void setCreator(final UserDto creator) {
		this.creator = creator;
	}

	public UserDto getAssignee() {
		return assignee;
	}

	public void setAssignee(final UserDto assignee) {
		this.assignee = assignee;
	}

	/**
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
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

	/**
	 * @return the priority
	 */
	public PriorityDto getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(final PriorityDto priority) {
		this.priority = priority;
	}

	/**
	 * @return the type
	 */
	public TypeDto getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final TypeDto type) {
		this.type = type;
	}

	/**
	 * @return the resolution
	 */
	public ResolutionDto getResolution() {
		return resolution;
	}

	/**
	 * @param resolution
	 *            the resolution to set
	 */
	public void setResolution(final ResolutionDto resolution) {
		this.resolution = resolution;
	}

	public BigDecimal getEstimated() {
		return estimated;
	}

	public void setEstimated(final BigDecimal estimated) {
		this.estimated = estimated;
	}

	public List<TaskTimeDto> getTaskTimes() {
		return taskTimes;
	}

	public void setTaskTimes(final List<TaskTimeDto> taskTimes) {
		this.taskTimes = taskTimes;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(final List<CommentDto> comments) {
		this.comments = comments;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof TaskDto)) {
			return false;
		}
		final TaskDto rhs = (TaskDto) object;
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
				.append(this.assignee, rhs.assignee).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(788126081, 2120783817)
				.appendSuper(super.hashCode()).append(this.creator)
				.append(this.lastUpdateDate).append(this.estimated)
				.append(this.description).append(this.title)
				.append(this.creationDate).append(this.priority)
				.append(this.type).append(this.resolution).append(this.number)
				.append(this.id).append(this.assignee).toHashCode();
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
				.append("priority", this.priority).toString();
	}

}

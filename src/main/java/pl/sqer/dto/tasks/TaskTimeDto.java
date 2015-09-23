package pl.sqer.dto.tasks;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import pl.sqer.dto.common.TBPMTDto;
import pl.sqer.dto.users.UserDto;

/**
 * The Class TaskTimeDto.
 */
public class TaskTimeDto implements TBPMTDto {

	/** Serial UID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Integer id;

	/** The user. */
	private UserDto user;

	/** The task. */
	private TaskDto task;

	/** The time. */
	private BigDecimal time;

	/** The date. */
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(final UserDto user) {
		this.user = user;
	}

	public TaskDto getTask() {
		return task;
	}

	public void setTask(final TaskDto task) {
		this.task = task;
	}

	public BigDecimal getTime() {
		return time;
	}

	public void setTime(final BigDecimal time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof TaskTimeDto)) {
			return false;
		}
		final TaskTimeDto rhs = (TaskTimeDto) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.task, rhs.task).append(this.id, rhs.id)
				.append(this.time, rhs.time).append(this.user, rhs.user)
				.append(this.date, rhs.date).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(606548483, -874121693)
				.appendSuper(super.hashCode()).append(this.task)
				.append(this.id).append(this.time).append(this.user)
				.append(this.date).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("time", this.time)
				.append("user", this.user).append("id", this.id)
				.append("task", this.task).append("date", this.date).toString();
	}

}

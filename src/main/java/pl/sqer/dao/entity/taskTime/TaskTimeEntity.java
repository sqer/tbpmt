package pl.sqer.dao.entity.taskTime;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.apache.commons.lang3.builder.ToStringBuilder;

import pl.sqer.dao.entity.task.TaskEntity;
import pl.sqer.dao.entity.user.UserEntity;

/**
 * The Class TaskTimeEntity.
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "TaskTimeEntity.selectAll", query = "SELECT t FROM TaskTimeEntity t"),
		@NamedQuery(name = "TaskTimeEntity.countAll", query = "SELECT count(t.id) FROM TaskTimeEntity t"),
		@NamedQuery(name = "TaskTimeEntity.findByUser", query = "SELECT t FROM TaskTimeEntity t WHERE t.user.id= :userId"),
		@NamedQuery(name = "TaskTimeEntity.findByTask", query = "SELECT t FROM TaskTimeEntity t WHERE t.task.id= :taskId") })
@Table(name = "task_time")
public class TaskTimeEntity implements Serializable {

	/** Serial UID. */
	private static final long serialVersionUID = 2681936994683932647L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	/** The user. */
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	/** The task. */
	@ManyToOne
	@JoinColumn(name = "task_id")
	private TaskEntity task;

	@Column(name = "time", nullable = false, unique = false)
	private BigDecimal time;

	@Column(name = "date", nullable = false, unique = false)
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(final UserEntity user) {
		this.user = user;
	}

	public TaskEntity getTask() {
		return task;
	}

	public void setTask(final TaskEntity task) {
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
		if (!(object instanceof TaskTimeEntity)) {
			return false;
		}
		final TaskTimeEntity rhs = (TaskTimeEntity) object;
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
		return new HashCodeBuilder(2007730347, 582346125)
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

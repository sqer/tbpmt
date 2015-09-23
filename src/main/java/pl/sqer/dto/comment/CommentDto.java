package pl.sqer.dto.comment;

import java.util.Date;

import pl.sqer.dto.common.TBPMTDto;
import pl.sqer.dto.tasks.TaskDto;
import pl.sqer.dto.users.UserDto;

/**
 * The Class CommentDto.
 */
public class CommentDto implements TBPMTDto, Comparable<CommentDto> {

	/** Serial UID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Integer id;

	/** The content. */
	private String content;

	/** The creator. */
	private UserDto creator;

	private Date creationDate;

	private TaskDto task;

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

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public UserDto getCreator() {
		return creator;
	}

	public void setCreator(final UserDto creator) {
		this.creator = creator;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	public TaskDto getTask() {
		return task;
	}

	public void setTask(final TaskDto task) {
		this.task = task;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		final CommentDto other = (CommentDto) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", content=" + content + ", creator="
				+ creator + ", creationDate=" + creationDate + ", task=" + task
				+ "]";
	}

	@Override
	public int compareTo(final CommentDto o) {
		if (this.getCreationDate() == null && o.getCreationDate() == null) {
			return 0;
		} else if (this.getCreationDate() == null) {
			return -1;
		} else if (o.getCreationDate() == null) {
			return 1;
		}

		final long t1 = this.getCreationDate().getTime();
		final long t2 = o.getCreationDate().getTime();
		if (t2 > t1)
			return 1;
		else if (t1 > t2)
			return -1;
		else
			return 0;
	}
}

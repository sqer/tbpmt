package pl.sqer.controller.task;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import pl.sqer.controller.common.Identity;
import pl.sqer.dto.comment.CommentDto;

/**
 * The Class TasksRoleManager.
 */
@ManagedBean(name = "tasksRoleManager")
@RequestScoped
public class TasksRoleManager {

	/** The identity. */
	@ManagedProperty(value = "#{identity}")
	private Identity identity;

	/**
	 * Checks if user can delete given comment
	 *
	 * @param comment
	 *            the comment dto
	 * @return true if can delete
	 */
	public boolean canDeleteComment(final CommentDto comment) {
		if (comment != null && identity.getUser() != null) {
			return identity.getUser().getId()
					.equals(comment.getCreator().getId());
		} else {
			return false;
		}
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(final Identity identity) {
		this.identity = identity;
	}

}

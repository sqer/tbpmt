package pl.sqer.service.comment;

import javax.ejb.Local;

import pl.sqer.dto.comment.CommentDto;

/**
 * The Interface CommentService.
 */
@Local
public interface CommentService {

	/**
	 * Adds comment to task
	 *
	 * @param comment
	 *            the comment to add
	 * @param taskId
	 *            the task id
	 * @param userId
	 *            the user id
	 * @return the added comment dto
	 */
	CommentDto addComment(String comment, Integer taskId, Integer userId);

	/**
	 * Deletes comment based on id
	 * 
	 * @param id
	 *            the comment id
	 */
	void deleteComment(Integer id);
}

package pl.sqer.dao.comment;

import java.util.List;

import javax.ejb.Local;

import pl.sqer.dao.common.BaseDAO;
import pl.sqer.dao.entity.comment.CommentEntity;

/**
 * The Interface CommentDAO.
 */
@Local
public interface CommentDAO extends BaseDAO<CommentEntity> {

	/**
	 * Find by user id
	 *
	 * @param userId
	 *            the user id
	 * @return list of comments
	 */
	List<CommentEntity> findByUser(final Integer userId);

	/**
	 * Find by task id
	 *
	 * @param taskId
	 *            the task id
	 *
	 * @return list of comments time
	 */
	List<CommentEntity> findByTask(final Integer taskId);

}

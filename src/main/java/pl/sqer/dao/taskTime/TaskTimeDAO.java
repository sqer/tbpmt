package pl.sqer.dao.taskTime;

import java.util.List;

import javax.ejb.Local;

import pl.sqer.dao.common.BaseDAO;
import pl.sqer.dao.entity.taskTime.TaskTimeEntity;

/**
 * The Interface TaskTimeDAO.
 */
@Local
public interface TaskTimeDAO extends BaseDAO<TaskTimeEntity> {

	/**
	 * Find by user id
	 *
	 * @param userId
	 *            the user id
	 * @return list of task time
	 */
	List<TaskTimeEntity> findByUser(final Integer userId);

	/**
	 * Find by task id
	 *
	 * @param taskId
	 *            the task id
	 *
	 * @return list of task time
	 */
	List<TaskTimeEntity> findByTask(final Integer taskId);

}

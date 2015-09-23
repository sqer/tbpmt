package pl.sqer.dao.task;

import java.util.List;

import javax.ejb.Local;

import pl.sqer.dao.common.BaseDAO;
import pl.sqer.dao.entity.task.TaskEntity;
import pl.sqer.dto.resolution.ResolutionEnum;
import pl.sqer.dto.type.TypeEnum;

/**
 * The Interface TaskDAO.
 */
@Local
public interface TaskDAO extends BaseDAO<TaskEntity> {

	/**
	 * Find by user
	 *
	 * @param userId
	 *            the user id
	 * @return list of tasks
	 */
	List<TaskEntity> findByUser(final Integer userId);

	/**
	 * Find by user and resolution type
	 *
	 * @param userId
	 *            the user id
	 * @param resolution
	 *            the resolution type
	 * @return list of tasks
	 */
	List<TaskEntity> findByUserAndResolution(final Integer userId,
			final ResolutionEnum resolution);

	/**
	 * Find by resolution type
	 *
	 * @param resolutions
	 *            the resolutions types
	 * @return list of tasks
	 */
	List<TaskEntity> findByResolution(List<ResolutionEnum> resolutions);

	/**
	 * Selects next task number
	 *
	 * @return the next task numbe
	 */
	Long selectNextTaskNumber();

	/**
	 * Counts tasks by resolution type
	 *
	 * @param resolution
	 *            the resolution type enum
	 * @return the count
	 */
	Long countByResolution(ResolutionEnum resolution);

	/**
	 * Counts tasks by type
	 *
	 * @param type
	 *            the type enum
	 * @return the count
	 */
	Long countByType(TypeEnum type);

}

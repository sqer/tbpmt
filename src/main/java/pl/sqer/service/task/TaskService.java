package pl.sqer.service.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pl.sqer.dto.resolution.ResolutionEnum;
import pl.sqer.dto.tasks.TaskDto;
import pl.sqer.dto.tasks.TaskTimeDto;

/**
 * The Interface TaskService.
 */
@Local
public interface TaskService {

	/**
	 * Select all.
	 *
	 * @return the list
	 */
	List<TaskDto> selectAll();

	/**
	 * Find by id
	 *
	 * @param id
	 *            the task id
	 *
	 * @return task dto
	 */
	TaskDto findById(Integer id);

	/**
	 * Find by user
	 *
	 * @param userId
	 *            the user id
	 * @return list of tasks
	 */
	List<TaskDto> findByUser(final Integer userId);

	/**
	 * Find by user and resolution type
	 *
	 * @param userId
	 *            the user id
	 * @param resolution
	 *            the resolution type
	 * @return list of tasks
	 */
	List<TaskDto> findByUserAndResolution(final Integer userId,
			ResolutionEnum resolution);

	/**
	 * Find by resolution type
	 *
	 * @param resolutions
	 *            the resolutions types
	 * @return list of tasks
	 */
	List<TaskDto> findByResolution(List<ResolutionEnum> resolutions);

	/**
	 * Saves task
	 *
	 * @param task
	 *            the task to save
	 */
	TaskDto save(TaskDto task);

	/**
	 * Delete task entry
	 *
	 * @param task
	 *            the task to delete
	 */
	void delete(TaskDto task);

	/**
	 * Updates task
	 *
	 * @param task
	 *            the task to update
	 */
	TaskDto update(TaskDto task);

	/**
	 * Select next task number
	 *
	 * @return next task number
	 */
	Long selectNextTaskNumber();

	/**
	 * Select data for resolution chart
	 *
	 * @return map of data
	 */
	Map<String, String> selectResolutionChartData();

	/**
	 * Select data for type chart
	 *
	 * @return map of data
	 */
	Map<String, String> selectTypeChartData();

	/**
	 * Changes resolution type
	 *
	 * @param taskDto
	 *            the task dto
	 * @param resolution
	 *            the new resolution type
	 */
	TaskDto changeResolution(TaskDto taskDto, ResolutionEnum resolution);

	/**
	 * Report work time with given time and date
	 *
	 * @param time
	 *            the real time of work
	 * @param date
	 *            the day of report
	 * @param userId
	 *            the user reported id
	 * @param taskId
	 *            the task for report
	 */
	TaskTimeDto reportWorkTime(BigDecimal time, Date date, Integer userId,
			Integer taskId);

	/**
	 * Saves estimation for task
	 *
	 * @param estimation
	 *            the estimation
	 * @param taskId
	 *            the task id
	 */
	void saveEstimation(BigDecimal estimation, Integer taskId);

}

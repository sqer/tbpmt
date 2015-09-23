package pl.sqer.service.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.sqer.dao.comment.CommentDAO;
import pl.sqer.dao.common.Messages;
import pl.sqer.dao.entity.resolution.ResolutionEntity;
import pl.sqer.dao.entity.task.TaskEntity;
import pl.sqer.dao.entity.taskTime.TaskTimeEntity;
import pl.sqer.dao.entity.user.UserEntity;
import pl.sqer.dao.resolution.ResolutionDAO;
import pl.sqer.dao.task.TaskDAO;
import pl.sqer.dao.taskTime.TaskTimeDAO;
import pl.sqer.dto.resolution.ResolutionEnum;
import pl.sqer.dto.tasks.TaskDto;
import pl.sqer.dto.tasks.TaskTimeDto;
import pl.sqer.dto.type.TypeEnum;
import pl.sqer.service.dozer.Dozer;

/**
 * The Class TaskServiceImpl.
 */
@Stateless(name = "taskService")
public class TaskServiceImpl implements TaskService {

	/** The task dao. */
	@EJB(name = "taskDao")
	private TaskDAO taskDAO;

	/** The resolution dao. */
	@EJB(name = "resolutionDao")
	private ResolutionDAO resolutionDAO;

	/** The task time dao. */
	@EJB(name = "taskTimeDao")
	private TaskTimeDAO taskTimeDAO;

	/** The comment dao. */
	@EJB(name = "commentDao")
	private CommentDAO commentDAO;

	/** The dozer. */
	@EJB(name = "dozer")
	private Dozer dozer;

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.sqer.service.task.TaskService#selectAll()
	 */
	@Override
	public List<TaskDto> selectAll() {
		final List<TaskEntity> list = taskDAO.selectAll();
		final List<TaskDto> results = new ArrayList<TaskDto>();
		for (final TaskEntity entity : list) {
			results.add(dozer.map(entity, TaskDto.class, "selectAllTasks"));
		}

		return results;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.sqer.service.task.TaskService#findById(java.lang.Integer)
	 */
	@Override
	public TaskDto findById(final Integer id) {
		final TaskEntity entity = taskDAO.findById(id);
		final TaskDto task = dozer.map(entity, TaskDto.class);
		return task;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.sqer.service.task.TaskService#findByUser(java.lang.Integer)
	 */
	@Override
	public List<TaskDto> findByUser(final Integer userId) {
		final List<TaskEntity> list = taskDAO.findByUser(userId);
		final List<TaskDto> results = new ArrayList<TaskDto>();
		for (final TaskEntity entity : list) {
			results.add(dozer.map(entity, TaskDto.class));
		}

		return results;
	}

	@Override
	public List<TaskDto> findByUserAndResolution(final Integer userId,
			final ResolutionEnum resolution) {
		final List<TaskEntity> list = taskDAO.findByUserAndResolution(userId,
				resolution);
		final List<TaskDto> results = new ArrayList<TaskDto>();
		for (final TaskEntity entity : list) {
			results.add(dozer.map(entity, TaskDto.class));
		}

		return results;
	}

	@Override
	public Long selectNextTaskNumber() {
		return taskDAO.selectNextTaskNumber();
	}

	@Override
	public TaskDto save(final TaskDto task) {
		if (task != null) {
			task.setNumber(selectNextTaskNumber());
			task.setLastUpdateDate(new Date());
			task.setCreationDate(new Date());
			final TaskEntity toPersist = dozer.map(task, TaskEntity.class);
			taskDAO.persist(toPersist);
			final TaskDto updatedTask = findById(toPersist.getId());
			return updatedTask;
		}
		return task;
	}

	@Override
	public TaskDto update(final TaskDto task) {
		if (task != null) {
			taskDAO.update(dozer.map(task, TaskEntity.class));
			final TaskDto updatedTask = findById(task.getId());
			return updatedTask;
		}
		return task;
	}

	@Override
	public Map<String, String> selectResolutionChartData() {
		final Long countCannotReproduce = taskDAO
				.countByResolution(ResolutionEnum.CANNOT_REPRODUCE);
		final Long countDuplicate = taskDAO
				.countByResolution(ResolutionEnum.DUPLICATE);
		final Long countFixed = taskDAO.countByResolution(ResolutionEnum.FIXED);
		final Long countIncomplete = taskDAO
				.countByResolution(ResolutionEnum.INCOMPLETE);
		final Long countResolved = taskDAO
				.countByResolution(ResolutionEnum.RESOLVED);
		final Long countUnresolved = taskDAO
				.countByResolution(ResolutionEnum.UNRESOLVED);
		final Long countWantFix = taskDAO
				.countByResolution(ResolutionEnum.WONT_FIX);
		final Long countWorksFine = taskDAO
				.countByResolution(ResolutionEnum.WORKS_FINE);
		final Long countInProgress = taskDAO
				.countByResolution(ResolutionEnum.IN_PROGRESS);
		final Map<String, String> map = new HashMap<String, String>();
		map.put(Messages.getMessage("lbl_resolution_cannot_reproduce"),
				countCannotReproduce.toString());
		map.put(Messages.getMessage("lbl_resolution_duplicate"),
				countDuplicate.toString());
		map.put(Messages.getMessage("lbl_resolution_fixed"),
				countFixed.toString());
		map.put(Messages.getMessage("lbl_resolution_incomplete"),
				countIncomplete.toString());
		map.put(Messages.getMessage("lbl_resolution_resolved"),
				countResolved.toString());
		map.put(Messages.getMessage("lbl_resolution_unresolved"),
				countUnresolved.toString());
		map.put(Messages.getMessage("lbl_resolution_wont_fix"),
				countWantFix.toString());
		map.put(Messages.getMessage("lbl_resolution_works_fine"),
				countWorksFine.toString());
		map.put(Messages.getMessage("lbl_resolution_in_progress"),
				countInProgress.toString());

		return map;

	}

	@Override
	public Map<String, String> selectTypeChartData() {
		final Long countBug = taskDAO.countByType(TypeEnum.BUG);
		final Long countChangeRequest = taskDAO
				.countByType(TypeEnum.CHANGE_REQUEST);
		final Long countNewItem = taskDAO.countByType(TypeEnum.NEW_ITEM);
		final Long countTask = taskDAO.countByType(TypeEnum.TASK);
		final Long countTest = taskDAO.countByType(TypeEnum.TEST);
		final Map<String, String> map = new HashMap<String, String>();
		map.put(Messages.getMessage("lbl_type_bug"), countBug.toString());
		map.put(Messages.getMessage("lbl_type_change_request"),
				countChangeRequest.toString());
		map.put(Messages.getMessage("lbl_type_test"), countTest.toString());
		map.put(Messages.getMessage("lbl_type_task"), countTask.toString());
		map.put(Messages.getMessage("lbl_type_new_item"),
				countNewItem.toString());

		return map;
	}

	@Override
	public void delete(final TaskDto task) {
		if (task != null) {
			taskDAO.delete(task.getId());
		}
	}

	@Override
	public TaskDto changeResolution(final TaskDto task,
			final ResolutionEnum resolution) {
		if (task != null && resolution != null) {
			// get task to update
			final TaskEntity taskEntity = taskDAO.findById(task.getId());
			// get selected resolution
			final ResolutionEntity resolutionEntity = resolutionDAO
					.findByName(resolution.getName());
			// change resolution
			taskEntity.setResolution(resolutionEntity);
			// save changes
			taskDAO.update(taskEntity);
			// return updated TaskDto
			return dozer.map(taskEntity, TaskDto.class);
		}
		return task;
	}

	@Override
	public TaskTimeDto reportWorkTime(final BigDecimal time, final Date date,
			final Integer userId, final Integer taskId) {
		if (time != null && date != null && userId != null && taskId != null) {
			final TaskTimeEntity entity = new TaskTimeEntity();
			entity.setUser(new UserEntity(userId));
			entity.setTask(new TaskEntity(taskId));
			entity.setTime(time);
			entity.setDate(date);
			taskTimeDAO.persist(entity);
			return dozer.map(entity, TaskTimeDto.class);
		} else {
			return null;
		}
	}

	@Override
	public void saveEstimation(final BigDecimal estimation, final Integer taskId) {
		if (estimation != null && taskId != null) {
			final TaskEntity entity = taskDAO.findById(taskId);
			entity.setEstimated(estimation);
			taskDAO.persist(entity);
		}
	}

	@Override
	public List<TaskDto> findByResolution(final List<ResolutionEnum> resolutions) {
		final List<TaskEntity> list = taskDAO.findByResolution(resolutions);
		final List<TaskDto> results = new ArrayList<TaskDto>();
		for (final TaskEntity entity : list) {
			results.add(dozer.map(entity, TaskDto.class));
		}

		return results;
	}
}

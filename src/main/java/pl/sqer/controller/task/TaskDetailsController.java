package pl.sqer.controller.task;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import pl.sqer.controller.common.AbstractController;
import pl.sqer.controller.common.Identity;
import pl.sqer.controller.common.Navigation;
import pl.sqer.dao.common.Messages;
import pl.sqer.dto.comment.CommentDto;
import pl.sqer.dto.priority.PriorityDto;
import pl.sqer.dto.resolution.ResolutionEnum;
import pl.sqer.dto.tasks.TaskDto;
import pl.sqer.dto.tasks.TaskTimeDto;
import pl.sqer.dto.type.TypeDto;
import pl.sqer.dto.users.UserDto;
import pl.sqer.service.comment.CommentService;
import pl.sqer.service.priority.PriorityService;
import pl.sqer.service.resolution.ResolutionService;
import pl.sqer.service.task.TaskService;
import pl.sqer.service.type.TypeService;
import pl.sqer.service.user.UserService;

/**
 * The Class TaskDetailsController.
 */
@ManagedBean(name = "taskDetails")
@ViewScoped
public class TaskDetailsController extends AbstractController implements
		Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1678043799405843751L;

	/** The Constant FORM_ID. */
	private static final String FORM_ID = "taskDetails";

	/** The Constant TABLE_FORM_ID. */
	private static final String TABLE_FORM_ID = "taskDatatable";

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(TasksController.class);

	/** The edited. */
	private TaskDto edited;

	/** The task id. */
	private Integer taskId;

	/** estimation used to estimate task. */
	private BigDecimal estimation;

	/** time used to report work. */
	private BigDecimal time;

	/** date used to report work. */
	private Date date;

	/** the comment used to add comment to task. */
	private String comment;

	/** The priority list. */
	private List<PriorityDto> priorityList;

	/** The type list. */
	private List<TypeDto> typeList;

	/** The type service. */
	@EJB
	private TypeService typeService;

	/** The priority service. */
	@EJB
	private PriorityService priorityService;

	/** The task service. */
	@EJB
	private TaskService taskService;

	/** The comment service. */
	@EJB
	private CommentService commentService;

	/** The resolution service. */
	@EJB
	private ResolutionService resolutionService;

	/** The user service. */
	@EJB
	private UserService userService;

	/** The identity. */
	@ManagedProperty(value = "#{identity}")
	private Identity identity;

	/**
	 * Instantiates a new task details controller.
	 */
	public TaskDetailsController() {

	}

	/**
	 * Inits the.
	 */
	public void init() {
		LOG.info("Initialize");
		if (!FacesContext.getCurrentInstance().isPostback()) {
			if (taskId != null) {
				edited = taskService.findById(taskId);
			} else {
				edited = new TaskDto();
				edited.setCreator(identity.getUser());
				edited.setResolution(resolutionService.selectUnresolved());
			}
		}
	}

	/**
	 * Save new state of task.
	 *
	 * @return the string
	 */
	public String save() {
		LOG.info("Save");
		if (edited != null) {
			if (edited.getId() != null) {
				edited = taskService.update(edited);
			} else {
				edited = taskService.save(edited);
			}
			Messages.showInfoMessage(FORM_ID,
					TaskMessages.TASK_MSG_SAVE_SUCCESS, true);
			return Navigation.goToTaskDetails(edited.getId());
		} else {
			LOG.error("Edited task is null");
			return null;
		}
	}

	/**
	 * Change resolution task type.
	 *
	 * @param resolution
	 *            the new resolution
	 */
	public void changeResolution(final ResolutionEnum resolution) {
		LOG.info(String.format("Enter changeResolution(%s)",
				resolution.getName()));
		edited = taskService.changeResolution(edited, resolution);
		LOG.info("Exit changeResolution");
	}

	/**
	 * Save copy of task.
	 */
	public void saveAsNew() {
		LOG.info("Enter saveAsNew");

		LOG.info("Exit saveAsNew");
	}

	/**
	 * Delete the task and redirect to tasks table.
	 *
	 * @return the string
	 */
	public String delete() {
		LOG.info("Enter delete");
		if (edited != null) {
			taskService.delete(edited);
			Messages.showInfoMessage(TABLE_FORM_ID,
					TaskMessages.TASK_MSG_DELETE_SUCCESS, true);
			LOG.info("Exit delete");
			return Navigation.goToTasks();
		} else {
			LOG.debug("Edited object is null");
			LOG.info("Exit delete");
			return null;
		}
	}

	/**
	 * Changes task resolution to 'Open'.
	 */
	public void open() {
		LOG.info("Enter open");
		edited = taskService
				.changeResolution(edited, ResolutionEnum.UNRESOLVED);
		LOG.info("Exit open");
	}

	/**
	 * Checks if task is in progress
	 *
	 * @return true if in progress
	 */
	public boolean isInProgress() {
		return edited.getResolution() != null ? ResolutionEnum.IN_PROGRESS
				.getName().equals(edited.getResolution().getName()) : false;
	}

	/**
	 * Reports work time.
	 */
	public void reportWork() {
		LOG.info("Enter reportWork");
		if (time != null && date != null) {
			final TaskTimeDto taskTime = taskService.reportWorkTime(time, date,
					identity.getUser().getId(), edited.getId());
			if (taskTime != null) {
				edited.getTaskTimes().add(taskTime);
			}
			clearReportWorkDialog();
		}
		LOG.info("Exit reportWork");
	}

	/**
	 * Saves estimation to task.
	 */
	public void saveEstimation() {
		LOG.info("Enter saveEstimation");
		if (estimation != null) {
			taskService.saveEstimation(estimation, edited.getId());
			edited.setEstimated(estimation);
		}
		LOG.info("Exit saveEstimation");
	}

	/**
	 * Adds comment to task.
	 */
	public void addComment() {
		LOG.info("Enter addComment");
		if (!StringUtils.isEmpty(comment)) {
			final CommentDto newComment = commentService.addComment(comment,
					edited.getId(), identity.getUser().getId());
			if (newComment != null) {
				edited.getComments().add(newComment);
			}
			clearComment();
		} else {
			Messages.showWarnMessage(FORM_ID,
					TaskMessages.TASK_MSG_COMMENT_EMPTY);
		}
		LOG.info("Exit addComment");
	}

	/**
	 * Deletes comment.
	 *
	 * @param comment
	 *            the comment
	 */
	public void deleteComment(final CommentDto comment) {
		LOG.info("Enter addComment");
		if (comment != null) {
			commentService.deleteComment(comment.getId());
			edited.getComments().remove(comment);
		}
		LOG.info("Exit addComment");
	}

	/**
	 * Clears comment area.
	 */
	public void clearComment() {
		comment = StringUtils.EMPTY;
	}

	/**
	 * Clears report work dialog inputs.
	 */
	public void clearReportWorkDialog() {
		date = null;
		time = null;
	}

	/**
	 * Clears estimate dialog inputs.
	 */
	public void clearEstimateDialog() {
		estimation = null;
	}

	/**
	 * Autocomplete assigned user text.
	 *
	 * @param query
	 *            the query text
	 * @return list of users
	 */
	public List<UserDto> assignedUserCompleteText(final String query) {
		LOG.info("Assigned user autocomplete event");
		if (!StringUtils.isEmpty(query)) {
			return userService.findByUsername(query);
		} else {
			return new ArrayList<UserDto>();
		}
	}

	/**
	 * Gets the priority list.
	 *
	 * @return the priorityList
	 */
	public List<PriorityDto> getPriorityList() {
		if (priorityList == null) {
			priorityList = priorityService.selectAll();
		}
		return priorityList;
	}

	/**
	 * Sets the priority list.
	 *
	 * @param priorityList
	 *            the priorityList to set
	 */
	public void setPriorityList(final List<PriorityDto> priorityList) {
		this.priorityList = priorityList;
	}

	/**
	 * Gets the type list.
	 *
	 * @return the typeList
	 */
	public List<TypeDto> getTypeList() {
		LOG.info("Get type list");
		if (typeList == null) {
			typeList = typeService.selectAll();
		}
		return typeList;
	}

	/**
	 * Checks if task is resolved.
	 *
	 * @return true if resolved
	 */
	public boolean isResolved() {
		if (edited != null) {
			return !ResolutionEnum.UNRESOLVED.getName().equals(
					edited.getResolution().getName());
		} else {
			return false;
		}
	}

	/**
	 * Gets estimated time for task.
	 *
	 * @return task estimated time
	 */
	public BigDecimal getEstimatedTime() {
		if (edited != null) {
			return edited.getEstimated() == null ? null : edited.getEstimated();
		} else {
			return null;
		}
	}

	/**
	 * Gets the estimation.
	 *
	 * @return the estimation
	 */
	public BigDecimal getEstimation() {
		return getEstimatedTime();
	}

	/**
	 * Sets the estimation.
	 *
	 * @param estimation
	 *            the new estimation
	 */
	public void setEstimation(final BigDecimal estimation) {
		this.estimation = estimation;
	}

	/**
	 * Gets logged time in task.
	 *
	 * @return logged time
	 */
	public BigDecimal getLoggedTime() {
		if (edited.getTaskTimes() != null) {
			BigDecimal logged = new BigDecimal(0);
			for (final TaskTimeDto time : edited.getTaskTimes()) {
				logged = logged.add(time.getTime());
			}
			return logged;
		} else {
			return new BigDecimal(0);
		}
	}

	/**
	 * Gets remaining time for task.
	 *
	 * @return remaining time
	 */
	public BigDecimal getRemainingTime() {
		if (edited != null) {
			final BigDecimal estimated = getEstimatedTime();
			final BigDecimal logged = getLoggedTime();
			if (estimated != null && logged != null) {
				final BigDecimal remaining = estimated.subtract(logged);
				return remaining.doubleValue() > 0 ? remaining
						: new BigDecimal(0);
			} else {
				return null;
			}

		} else {
			return null;
		}
	}

	/**
	 * Sets the type list.
	 *
	 * @param typeList
	 *            the typeList to set
	 */
	public void setTypeList(final List<TypeDto> typeList) {
		this.typeList = typeList;
	}

	/**
	 * Gets the edited.
	 *
	 * @return the edited
	 */
	public TaskDto getEdited() {
		return edited;
	}

	/**
	 * Sets the edited.
	 *
	 * @param edited
	 *            the new edited
	 */
	public void setEdited(final TaskDto edited) {
		this.edited = edited;
	}

	/**
	 * Gets the task id.
	 *
	 * @return the task id
	 */
	public Integer getTaskId() {
		return taskId;
	}

	/**
	 * Sets the task id.
	 *
	 * @param taskId
	 *            the new task id
	 */
	public void setTaskId(final Integer taskId) {
		this.taskId = taskId;
	}

	/**
	 * Gets the identity.
	 *
	 * @return the identity
	 */
	public Identity getIdentity() {
		return identity;
	}

	/**
	 * Sets the identity.
	 *
	 * @param identity
	 *            the new identity
	 */
	public void setIdentity(final Identity identity) {
		this.identity = identity;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public BigDecimal getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time
	 *            the new time
	 */
	public void setTime(final BigDecimal time) {
		this.time = time;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the new date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment
	 *            the new comment
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}

}
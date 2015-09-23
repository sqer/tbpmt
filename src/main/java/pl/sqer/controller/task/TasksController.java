package pl.sqer.controller.task;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import pl.sqer.dao.common.Messages;
import pl.sqer.dto.tasks.TaskDto;
import pl.sqer.service.task.TaskService;

/**
 * The Class TasksController.
 */
@ManagedBean(name = "tasks")
@ViewScoped
public class TasksController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1678043799405843751L;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(TasksController.class);

	private static final String TABLE_FORM_ID = "taskDatatable";

	/** The selected. */
	private TaskDto selected;

	/** The task service. */
	@EJB
	private TaskService taskService;

	/** The list. */
	private List<TaskDto> list;

	/**
	 * Instantiates a new tasks controller.
	 */
	public TasksController() {
	}

	/**
	 * Creates new
	 *
	 * @return the task create page
	 */
	public String createNew() {
		LOG.info("Enter createNew");
		return "tasks_details.xhtml?faces-redirect=true";
	}

	/**
	 * Deletes selected task
	 */
	public void delete() {
		LOG.info("Enter delete");
		if (selected != null) {
			taskService.delete(selected);
			list.remove(selected);
			selected = null;
			Messages.showInfoMessage(TABLE_FORM_ID,
					TaskMessages.TASK_MSG_DELETE_SUCCESS);
		} else {
			LOG.info("User not select any task");
			Messages.showWarnMessage(TABLE_FORM_ID,
					TaskMessages.TASK_MSG_PLEASE_SELECT_TASK_SUMMARY,
					TaskMessages.TASK_MSG_PLEASE_SELECT_TASK_DETAILS);
		}
		LOG.info("Exit delete");
	}

	/**
	 * Gets list of tasks dtos
	 *
	 * @return the tasks list
	 */
	public List<TaskDto> getList() {
		LOG.info("Gets list");
		if (list == null) {
			list = taskService.selectAll();
		}
		return list;
	}

	/**
	 * On row select.
	 *
	 * @param event
	 *            the event
	 */
	public void onRowSelect(final SelectEvent event) {
		LOG.info("On row select event");
		final FacesMessage msg = new FacesMessage("Car Selected",
				((TaskDto) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Got to edit.
	 *
	 * @return the string
	 */
	public String gotToEdit() {
		LOG.info("Enter gotToEdit");
		if (selected != null) {
			return "tasks_details.xhtml?id=" + selected.getId()
					+ "&faces-redirect=true";
		} else {
			LOG.info("User not select any task");
			Messages.showWarnMessage(TABLE_FORM_ID,
					TaskMessages.TASK_MSG_PLEASE_SELECT_TASK_SUMMARY,
					TaskMessages.TASK_MSG_PLEASE_SELECT_TASK_DETAILS);
			return null;
		}
	}

	/**
	 * On row select.
	 */
	public void onRowSelect() {

	}

	public TaskDto getSelected() {
		return selected;
	}

	public void setSelected(final TaskDto selected) {
		this.selected = selected;
	}

}
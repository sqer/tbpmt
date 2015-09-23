package pl.sqer.controller.agile;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import pl.sqer.controller.common.Identity;
import pl.sqer.controller.common.Navigation;
import pl.sqer.dto.resolution.ResolutionEnum;
import pl.sqer.dto.tasks.TaskDto;
import pl.sqer.service.task.TaskService;

/**
 * The Class AgileController.
 */
@ManagedBean(name = "agile")
@ViewScoped
public class AgileController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1678043799405843751L;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(AgileController.class);

	/** The identity. */
	@ManagedProperty(value = "#{identity}")
	Identity identity;

	/** The task service. */
	@EJB
	private TaskService taskService;

	/** The to do list. */
	private List<TaskDto> toDoList;

	/** The in progress list. */
	private List<TaskDto> inProgressList;

	/** The done list. */
	private List<TaskDto> doneList;

	/**
	 * Instantiates a user controller.
	 */
	public AgileController() {
	}

	/**
	 * Redirects to task details based on given id
	 * 
	 * @param id
	 *            the id of task
	 * @return the task detaisl url
	 */
	public String goToTask(final Integer id) {
		return Navigation.goToTaskDetails(id);
	}

	public List<TaskDto> getToDoList() {
		LOG.info("Gets list");
		if (toDoList == null) {
			toDoList = taskService.findByResolution(Arrays
					.asList(ResolutionEnum.UNRESOLVED));
		}
		return toDoList;
	}

	public List<TaskDto> getInProgressList() {
		LOG.info("Gets list");
		if (inProgressList == null) {
			inProgressList = taskService.findByResolution(Arrays
					.asList(ResolutionEnum.IN_PROGRESS));
		}
		return inProgressList;
	}

	public List<TaskDto> getDoneList() {
		LOG.info("Gets list");
		if (doneList == null) {
			doneList = taskService.findByResolution(Arrays.asList(
					ResolutionEnum.CANNOT_REPRODUCE, ResolutionEnum.DUPLICATE,
					ResolutionEnum.FIXED, ResolutionEnum.INCOMPLETE,
					ResolutionEnum.RESOLVED, ResolutionEnum.WONT_FIX,
					ResolutionEnum.WORKS_FINE));
		}
		return doneList;
	}

	/**
	 * @return the identity
	 */
	public Identity getIdentity() {
		return identity;
	}

	/**
	 * @param identity
	 *            the identity to set
	 */
	public void setIdentity(final Identity identity) {
		this.identity = identity;
	}

}
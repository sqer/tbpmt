package pl.sqer.controller.common;

import java.io.Serializable;

/**
 * The Class Navigation.
 */

public final class Navigation implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Navigate to task details page
	 *
	 * @param id
	 *            the task id
	 * @return the url string
	 */
	public static String goToTaskDetails(final Integer id) {
		return "tasks_details.xhtml?id=" + id + "&faces-redirect=true";
	}

	/**
	 * Navigate to tasks
	 *
	 * @return the url string
	 */
	public static String goToTasks() {
		return "tasks.xhtml?faces-redirect=true";
	}

}

package pl.sqer.controller.users;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import pl.sqer.controller.common.Identity;
import pl.sqer.dao.common.Messages;
import pl.sqer.dto.users.UserDto;
import pl.sqer.service.user.UserService;

/**
 * The Class UsersController.
 */
@ManagedBean(name = "users")
@ViewScoped
public class UsersController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1678043799405843751L;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(UsersController.class);

	/** The identity. */
	@ManagedProperty(value = "#{identity}")
	Identity identity;

	/** The selected. */
	private UserDto selected;

	/** The user service. */
	@EJB
	private UserService userService;

	/** The list. */
	private List<UserDto> list;

	/**
	 * Instantiates a user controller.
	 */
	public UsersController() {
	}

	/**
	 * Creates new
	 */
	public String createNew() {
		LOG.info("Enter createNew");
		return "users_details.xhtml?faces-redirect=true";
	}

	/**
	 * Deletes entry
	 */
	public void delete() {
		LOG.info("Enter delete");
		if (selected != null) {
			userService.delete(selected);
		} else {
			LOG.info("User not select any user");
			Messages.showWarnMessage("usersDatatable",
					UsersMessages.USER_MSG_PLEASE_SELECT_USER);
		}
		LOG.info("Exit delete");
	}

	/**
	 * Gets list of user dtos
	 *
	 * @return the user list
	 */
	public List<UserDto> getList() {
		LOG.info("Gets list");
		if (list == null) {
			list = userService.selectAll();
		}
		return list;
	}

	/**
	 * Got to edit.
	 *
	 * @return the string
	 */
	public String gotToEdit() {
		LOG.info("Go to edit page");
		if (selected != null) {
			return "users_details.xhtml?id=" + selected.getId()
					+ "&faces-redirect=true";
		} else {
			LOG.info("User not select any user");
			Messages.showWarnMessage("usersDatatable",
					UsersMessages.USER_MSG_PLEASE_SELECT_USER);
			return null;
		}
	}

	public UserDto getSelected() {
		return selected;
	}

	public void setSelected(final UserDto selected) {
		this.selected = selected;
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
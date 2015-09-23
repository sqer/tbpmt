package pl.sqer.controller.common;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import pl.sqer.controller.users.UsersMessages;
import pl.sqer.dao.common.Messages;
import pl.sqer.dto.users.UserDto;
import pl.sqer.service.user.UserService;

/**
 * The Class Identity.
 */
@SessionScoped
@ManagedBean(name = "identity")
public class Identity implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(Identity.class);

	private static final String FORM_ID = "account";

	/** The user. */
	private UserDto user;

	@EJB
	private UserService userService;

	/**
	 * @return the user
	 */
	public UserDto getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(final UserDto user) {
		this.user = user;
	}

	public void save() {
		LOG.info("Enter save");
		userService.update(user);
		Messages.showInfoMessage(FORM_ID, UsersMessages.USER_MSG_UPDATE_SUCCESS);
		LOG.info("Exit save");
	}

}

package pl.sqer.authentication;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import pl.sqer.controller.common.Identity;
import pl.sqer.dao.common.Messages;
import pl.sqer.dto.users.UserDto;
import pl.sqer.service.user.UserService;

/**
 * The Class LoginController.
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(LoginController.class);

	/** The logged successfully. */
	private static boolean LOGGED_SUCCESSFULLY = true;

	/** The identity. */
	@ManagedProperty(value = "#{identity}")
	Identity identity;

	/** The user service. */
	@EJB
	UserService userService;

	/** The password. */
	private String password;

	/** The message. */
	private String message;

	/** The uname. */
	private String uname;

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(final String uname) {
		this.uname = uname;
	}

	@PostConstruct
	public void init() {
		LOG.info("Enter login page");
	}

	/**
	 * Login project.
	 *
	 * @return the string
	 */
	public String loginProject() {
		// final boolean result = UserDAO.login(uname, password);
		if (LOGGED_SUCCESSFULLY == userService.authenticate(uname, password)) {
			LOG.info("Logged successfully");

			final UserDto user = userService.findByUsenameUnique(uname);
			identity.setUser(user);

			// get Http Session and store username
			final HttpSession session = Util.getSession();
			session.setAttribute("username", uname);

			return "index.xhtml?faces-redirect=true";
		} else {
			LOG.info("Unable to login");
			Messages.showWarnMessage(null,
					AuthenticationMessages.LOGIN_MSG_INVALID_SUMMARY);
			return "login";
		}
	}

	/**
	 * Logout.
	 *
	 * @return the string
	 */
	public String logout() {
		final HttpSession session = Util.getSession();
		session.invalidate();
		return "login";
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
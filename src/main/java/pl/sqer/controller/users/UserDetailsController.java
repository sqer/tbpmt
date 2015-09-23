package pl.sqer.controller.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import pl.sqer.controller.common.AbstractController;
import pl.sqer.controller.common.Identity;
import pl.sqer.dao.common.Messages;
import pl.sqer.dto.role.RoleDto;
import pl.sqer.dto.users.UserDto;
import pl.sqer.service.dozer.Dozer;
import pl.sqer.service.role.RoleService;
import pl.sqer.service.user.UserService;

/**
 * The Class UsersDetailsController.
 */
@ManagedBean(name = "userDetails")
@ViewScoped
public class UserDetailsController extends AbstractController implements
		Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1678043799405843751L;

	/** The Constant FORM_ID. */
	private static final String FORM_ID = "userDetails";

	/** The Constant TABLE_FORM_ID. */
	private static final String TABLE_FORM_ID = "usersDatatable";

	/** The Constant LOG. */
	private static final Logger LOG = Logger
			.getLogger(UserDetailsController.class);

	/** The edited. */
	private UserDto edited;

	/** The user id. */
	private Integer userId;

	/** The selected available role. */
	private RoleDto selectedAvailableRole;

	/** The selected assigned role. */
	private RoleDto selectedAssignedRole;

	/** The available roles. */
	private List<RoleDto> availableRoles;

	/** The assigned roles. */
	private List<RoleDto> assignedRoles;

	/** The user service. */
	@EJB
	private UserService userService;

	/** The role service. */
	@EJB
	private RoleService roleService;

	/** The dozer. */
	@EJB
	private Dozer dozer;

	/** The identity. */
	@ManagedProperty(value = "#{identity}")
	private Identity identity;

	/**
	 * Instantiates a new user details controller.
	 */
	public UserDetailsController() {
		// NOP
	}

	/**
	 * Inits the.
	 */
	public void init() {
		LOG.info("Enter init");
		if (!FacesContext.getCurrentInstance().isPostback()) {
			if (userId != null) {
				edited = userService.findById(userId);
			} else {
				edited = new UserDto();
			}
			initRoles(edited);
		}
		LOG.info("Exit init");
	}

	/**
	 * Inits the roles.
	 *
	 * @param user
	 *            the user
	 */
	private void initRoles(final UserDto user) {
		availableRoles = roleService.selectAll();
		if (CollectionUtils.isNotEmpty(user.getRoles())) {
			availableRoles.removeAll(user.getRoles());
			assignedRoles = user.getRoles();
			sortRoleLists();
		} else {
			assignedRoles = new ArrayList<RoleDto>();
		}
	}

	/**
	 * Sorts role list
	 */
	private void sortRoleLists() {
		Collections.sort(availableRoles);
		Collections.sort(assignedRoles);
	}

	/**
	 * Save.
	 */
	public void save() {
		LOG.info("Enter save");
		if (edited != null) {
			Integer id = null;
			// pre save actions
			preSave();
			if (edited.getId() != null) {
				id = userService.update(edited);
				Messages.showInfoMessage(FORM_ID,
						UsersMessages.USER_MSG_UPDATE_SUCCESS);
			} else {
				id = userService.save(edited);
				Messages.showInfoMessage(FORM_ID,
						UsersMessages.USER_MSG_SAVE_SUCCESS);
			}
			edited = userService.findById(id);
			initRoles(edited);
		} else {
			LOG.error("Edited user is null");
		}
		LOG.info("Exit save");
	}

	private void preSave() {
		final List<RoleDto> roles = dozer.map(assignedRoles, RoleDto.class);
		edited.setRoles(roles);
	}

	/**
	 * Save as new.
	 */
	public void saveAsNew() {
		LOG.info("Enter saveAsNew");
		LOG.info("Exit saveAsNew");
	}

	/**
	 * Delete.
	 */
	public String delete() {
		LOG.info("Enter delete");
		if (edited != null) {
			userService.delete(edited);
			Messages.showInfoMessage(TABLE_FORM_ID,
					UsersMessages.USER_MSG_DELETE_SUCCESS, true);
			LOG.info("Exit delete");
			return "users.xhtml?faces-redirect=true";
		} else {
			LOG.debug("Edited object is null");
			LOG.info("Exit delete");
			return null;
		}
	}

	/**
	 * Assign role.
	 */
	public void assignRole() {
		LOG.info("Enter assignRole");
		if (selectedAvailableRole != null) {
			assignedRoles.add(dozer.map(selectedAvailableRole, RoleDto.class));
			availableRoles.remove(selectedAvailableRole);
			sortRoleLists();
		} else {
			LOG.info("No selected role");
		}
		LOG.info("Exit assignRole");
	}

	/**
	 * Unassign role.
	 */
	public void unassignRole() {
		LOG.info("Enter unassignRole");
		if (selectedAssignedRole != null) {
			availableRoles.add(dozer.map(selectedAssignedRole, RoleDto.class));
			assignedRoles.remove(selectedAssignedRole);
			sortRoleLists();
		} else {
			LOG.info("No selected role");
		}
		LOG.info("Exit unassignRole");
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(final Integer userId) {
		this.userId = userId;
	}

	/**
	 * Gets the edited.
	 *
	 * @return the edited
	 */
	public UserDto getEdited() {
		return edited;
	}

	/**
	 * Sets the edited.
	 *
	 * @param edited
	 *            the new edited
	 */
	public void setEdited(final UserDto edited) {
		this.edited = edited;
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
	 * Gets the available roles.
	 *
	 * @return the available roles
	 */
	public List<RoleDto> getAvailableRoles() {
		return availableRoles;
	}

	/**
	 * Sets the available roles.
	 *
	 * @param availableRoles
	 *            the new available roles
	 */
	public void setAvailableRoles(final List<RoleDto> availableRoles) {
		this.availableRoles = availableRoles;
	}

	/**
	 * Gets the assigned roles.
	 *
	 * @return the assigned roles
	 */
	public List<RoleDto> getAssignedRoles() {
		return assignedRoles;
	}

	/**
	 * Sets the assigned roles.
	 *
	 * @param assignedRoles
	 *            the new assigned roles
	 */
	public void setAssignedRoles(final List<RoleDto> assignedRoles) {
		this.assignedRoles = assignedRoles;
	}

	/**
	 * Gets the selected available role.
	 *
	 * @return the selected available role
	 */
	public RoleDto getSelectedAvailableRole() {
		return selectedAvailableRole;
	}

	/**
	 * Sets the selected available role.
	 *
	 * @param selectedAvailableRole
	 *            the new selected available role
	 */
	public void setSelectedAvailableRole(final RoleDto selectedAvailableRole) {
		this.selectedAvailableRole = selectedAvailableRole;
	}

	/**
	 * Gets the selected assigned role.
	 *
	 * @return the selected assigned role
	 */
	public RoleDto getSelectedAssignedRole() {
		return selectedAssignedRole;
	}

	/**
	 * Sets the selected assigned role.
	 *
	 * @param selectedAssignedRole
	 *            the new selected assigned role
	 */
	public void setSelectedAssignedRole(final RoleDto selectedAssignedRole) {
		this.selectedAssignedRole = selectedAssignedRole;
	}

	/**
	 * Gets the user service.
	 *
	 * @return the user service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user service.
	 *
	 * @param userService
	 *            the new user service
	 */
	public void setUserService(final UserService userService) {
		this.userService = userService;
	}

}
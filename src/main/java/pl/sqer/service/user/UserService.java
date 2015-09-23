package pl.sqer.service.user;

import java.util.List;

import javax.ejb.Local;

import pl.sqer.dto.users.UserDto;

/**
 * The Interface UserService.
 */
@Local
public interface UserService {

	/**
	 * Select all.
	 *
	 * @return the list
	 */
	List<UserDto> selectAll();

	/**
	 * Find by id
	 *
	 * @param id
	 *            the user id
	 *
	 * @return user dto
	 */
	UserDto findById(Integer id);

	/**
	 * Find by username.
	 *
	 * @param text
	 *            the text
	 * @return the list
	 */
	List<UserDto> findByUsername(String text);

	/**
	 * Find by username unique user.
	 *
	 * @param text
	 *            the text
	 * @return the list
	 */
	UserDto findByUsenameUnique(String username);

	/**
	 * Authenticate user
	 *
	 * @param login
	 *            the login
	 * @param password
	 *            the password
	 * @return true if authenticate
	 */
	boolean authenticate(String login, String password);

	/**
	 * Saves user
	 *
	 * @param user
	 *            the user to save
	 * @return the object id
	 */
	Integer save(UserDto user);

	/**
	 * Delete the user entry
	 *
	 * @param user
	 *            the user to delete
	 */
	void delete(UserDto user);

	/**
	 * Updates user
	 *
	 * @param user
	 *            the user to update
	 */
	Integer update(UserDto user);

}

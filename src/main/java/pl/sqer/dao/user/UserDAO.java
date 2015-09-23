package pl.sqer.dao.user;

import java.util.List;

import javax.ejb.Local;

import pl.sqer.dao.common.BaseDAO;
import pl.sqer.dao.entity.user.UserEntity;

/**
 * The Interface UserDAO.
 */
@Local
public interface UserDAO extends BaseDAO<UserEntity> {

	/**
	 * Finds users by username
	 *
	 * @param text
	 *            the text to search
	 * @return list of users
	 */
	List<UserEntity> findByUsername(String text);

	/**
	 * Find by username unique user.
	 *
	 * @param text
	 *            the text
	 * @return the list
	 */
	UserEntity findByUsenameUnique(String username);

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
	 * Selects user encoded password
	 * 
	 * @param userId
	 *            the user id
	 * @return password
	 */
	String selectUserEncodedPassword(Integer userId);
}

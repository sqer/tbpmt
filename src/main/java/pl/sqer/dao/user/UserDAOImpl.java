package pl.sqer.dao.user;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import pl.sqer.dao.common.BaseDAOImpl;
import pl.sqer.dao.entity.user.UserEntity;

/**
 * The UserDAOImpl.
 */
@Stateless(name = "userDao")
public class UserDAOImpl extends BaseDAOImpl<UserEntity> implements UserDAO {

	public UserDAOImpl() {
		super(UserEntity.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserEntity> findByUsername(final String text) {
		final Query query = getNamedQuery("findByUsername");
		query.setString(PARAM_TEXT, getFormattedText(text));
		final List<UserEntity> list = query.list();
		return list;
	}

	@Override
	public boolean authenticate(final String login, final String password) {
		final Query query = getNamedQuery("authenticate");
		query.setString("login", login);
		query.setString("password", password);

		return query.uniqueResult() != null ? true : false;
	}

	@Override
	public UserEntity findByUsenameUnique(final String username) {
		final Query query = getNamedQuery("findByUsenameUnique");
		query.setString(PARAM_TEXT, username);
		final UserEntity user = (UserEntity) query.uniqueResult();
		return user;
	}

	@Override
	public String selectUserEncodedPassword(Integer userId) {
		final Query query = getNamedQuery("selectUserEncodedPassword");
		query.setInteger(PARAM_ID, userId);
		String password = (String) query.uniqueResult();
		return password;
	}
}

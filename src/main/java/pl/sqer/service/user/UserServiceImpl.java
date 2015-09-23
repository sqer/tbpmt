package pl.sqer.service.user;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import pl.sqer.dao.entity.user.UserEntity;
import pl.sqer.dao.user.UserDAO;
import pl.sqer.dto.users.UserDto;
import pl.sqer.service.dozer.Dozer;

/**
 * The Class UserServiceImpl.
 */
@Stateless
public class UserServiceImpl implements UserService {

	/** The user dao. */
	@EJB(name = "userDao")
	UserDAO userDAO;

	/** The dozer. */
	@EJB(name = "dozer")
	Dozer dozer;

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.sqer.service.user.UserService#selectAll()
	 */
	@Override
	public List<UserDto> selectAll() {
		final List<UserEntity> list = userDAO.selectAll();
		final List<UserDto> results = new ArrayList<UserDto>();
		for (final UserEntity entity : list) {
			results.add(dozer.map(entity, UserDto.class));
		}

		return results;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.sqer.service.user.UserService#findById(java.lang.Integer)
	 */
	@Override
	public UserDto findById(final Integer id) {
		final UserEntity entity = userDAO.findById(id);
		return dozer.map(entity, UserDto.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.sqer.service.user.UserService#findByUsername(java.lang.String)
	 */
	@Override
	public List<UserDto> findByUsername(final String text) {
		final List<UserEntity> list = userDAO.findByUsername(text);
		final List<UserDto> results = new ArrayList<UserDto>();
		for (final UserEntity entity : list) {
			results.add(dozer.map(entity, UserDto.class));
		}

		return results;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.sqer.service.user.UserService#authenticate(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean authenticate(final String login, final String password) {

		return userDAO.authenticate(login, DigestUtils.sha256Hex(password));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * pl.sqer.service.user.UserService#findByUsenameUnique(java.lang.String)
	 */
	@Override
	public UserDto findByUsenameUnique(final String username) {
		final UserEntity entity = userDAO.findByUsenameUnique(username);
		if (entity != null) {
			return dozer.map(entity, UserDto.class);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.sqer.service.user.UserService#save(pl.sqer.dto.users.UserDto)
	 */
	@Override
	public Integer save(final UserDto user) {
		if (user != null) {
			final UserEntity userEntity = dozer.map(user, UserEntity.class);
			encodeUserPassword(userEntity);
			userDAO.persist(userEntity);
			return userEntity.getId();
		} else {
			return null;
		}
	}

	/**
	 * Encode password within user entity.
	 *
	 * @param userEntity
	 *            user with password
	 */
	private void encodeUserPassword(final UserEntity userEntity) {
		if (StringUtils.isEmpty(userEntity.getPassword())) {
			userEntity.setPassword(userEntity.getUsername());
		}
		userEntity.setPassword(DigestUtils.sha256Hex(userEntity.getPassword()));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.sqer.service.user.UserService#update(pl.sqer.dto.users.UserDto)
	 */
	@Override
	public Integer update(final UserDto user) {
		if (user != null) {
			final UserEntity userEntity = dozer.map(user, UserEntity.class);
			final String actualPassword = selectUserEncodedPassword(userEntity
					.getId());
			if (!user.getPassword().equals(actualPassword)) {
				encodeUserPassword(userEntity);
			}
			userDAO.merge(userEntity);

			return user.getId();
		}
		return null;
	}

	/**
	 * Selects user encoded password
	 *
	 * @param userId
	 *            the user id
	 * @return password
	 */
	private String selectUserEncodedPassword(final Integer userId) {
		String password = userDAO.selectUserEncodedPassword(userId);
		if (password == null) {
			password = StringUtils.EMPTY;
		}
		return password;
	}

	@Override
	public void delete(final UserDto user) {
		if (user != null) {
			userDAO.delete(user.getId());
		}
	}
}

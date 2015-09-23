package pl.sqer.dao.common;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * The Class BaseDAOImpl.
 *
 * @param <T>
 *            the generic type
 */

public abstract class BaseDAOImpl<T> implements BaseDAO<T> {

	/** The Constant PARAM_ID. */
	protected static final String PARAM_ID = "id";

	/** The Constant PARAM_TEXT. */
	protected static final String PARAM_TEXT = "text";

	/** The entity class. */
	Class<T> entityClass;

	/** The session. */
	@PersistenceContext
	Session session;

	/**
	 * Instantiates a new base dao impl.
	 *
	 * @param entityClass
	 *            the entity class
	 */
	public BaseDAOImpl(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Instantiates a new base dao impl.
	 *
	 * @param entityClass
	 *            the entity class
	 * @param session
	 *            the session
	 */
	public BaseDAOImpl(final Class<T> entityClass, final Session session) {
		this.entityClass = entityClass;
		this.session = session;
	}

	/**
	 * Select all.
	 *
	 * @return the list
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> selectAll() {

		// Save the Model object
		final Query query = getNamedQuery("selectAll");

		final List<T> results = query.list();

		return results;
	}

	/**
	 * Gets current session
	 *
	 * @return session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * Gets Named Query for given class and query name.
	 *
	 * @param name
	 *            the name of query
	 * @return the named query
	 */
	public Query getNamedQuery(final String name) {
		final String fullName = new StringBuffer()
				.append(entityClass.getSimpleName()).append(".").append(name)
				.toString();
		return getSession().getNamedQuery(fullName);
	}

	/**
	 * Gets Named Query for given class and query name.
	 *
	 * @param clazz
	 *            the class of query
	 * @param name
	 *            the name of query
	 * @return the named query
	 */
	public Query getNamedQuery(final Class clazz, final String name) {
		final String fullName = new StringBuffer()
				.append(clazz.getSimpleName()).append(".").append(name)
				.toString();
		return getSession().getNamedQuery(fullName);
	}

	/**
	 * Count all.
	 *
	 * @return the int
	 */
	@Override
	public int countAll() {
		final Query query = getNamedQuery("countAll");

		final Long count = (Long) query.uniqueResult();

		return count.intValue();
	}

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T findById(final Integer id) {
		final Query query = getNamedQuery("findById");
		query.setInteger(PARAM_ID, id);
		final T result = (T) query.uniqueResult();
		return result;
	}

	@Override
	public void persist(final T object) {
		getSession().save(object);
	}

	@Override
	public void update(final T object) {
		getSession().update(object);
	}

	@Override
	public void merge(final T object) {
		getSession().merge(object);
	}

	@Override
	public void delete(final Integer id) {
		final Object persistentInstance = session.load(entityClass, id);
		getSession().delete(persistentInstance);
	}

	/**
	 * Gets the formatted text.
	 *
	 * @param text
	 *            the text
	 * @return the formatted text
	 */
	protected String getFormattedText(final String text) {
		return new StringBuffer().append("%%").append(text).append("%%")
				.toString();
	}

}

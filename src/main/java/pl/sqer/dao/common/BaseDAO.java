package pl.sqer.dao.common;

import java.util.List;

/**
 * The Interface BaseDao.
 *
 * @param <T>
 *            the generic type
 */
public interface BaseDAO<T> {

	/**
	 * Select all T objects.
	 *
	 * @return list of T objects
	 */
	List<T> selectAll();

	/**
	 * Count all.
	 *
	 * @return the int
	 */
	int countAll();

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	T findById(Integer id);

	/**
	 * Persist the object.
	 *
	 * @param object
	 *            the object
	 */
	void persist(T object);

	/**
	 * Updates the object.
	 *
	 * @param object
	 *            the object
	 */
	void update(T object);

	/**
	 * Merges the object.
	 *
	 * @param object
	 *            the object
	 */
	void merge(T object);

	/**
	 * Delete the object by id
	 * 
	 * @param id
	 *            the id of object
	 */
	void delete(Integer id);

}

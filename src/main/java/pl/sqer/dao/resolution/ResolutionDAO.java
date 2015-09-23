package pl.sqer.dao.resolution;

import javax.ejb.Local;

import pl.sqer.dao.common.BaseDAO;
import pl.sqer.dao.entity.resolution.ResolutionEntity;

/**
 * The Interface ResolutionDAO.
 */
@Local
public interface ResolutionDAO extends BaseDAO<ResolutionEntity> {

	/**
	 * Finds resolution by resolution name
	 *
	 * @param name
	 *            the name of resolution
	 * @return {@link ResolutionEntity}
	 */
	ResolutionEntity findByName(String name);

}

package pl.sqer.dao.resolution;

import javax.ejb.Stateless;

import org.hibernate.Query;

import pl.sqer.dao.common.BaseDAOImpl;
import pl.sqer.dao.entity.resolution.ResolutionEntity;

/**
 * The ResolutionDAOImpl.
 */
@Stateless(name = "resolutionDao")
public class ResolutionDAOImpl extends BaseDAOImpl<ResolutionEntity> implements
		ResolutionDAO {

	private final static String PARAM_NAME = "name";

	public ResolutionDAOImpl() {
		super(ResolutionEntity.class);
	}

	@Override
	public ResolutionEntity findByName(final String name) {
		final Query query = getNamedQuery("findByName");
		query.setString(PARAM_NAME, name);
		return (ResolutionEntity) query.uniqueResult();
	}
}

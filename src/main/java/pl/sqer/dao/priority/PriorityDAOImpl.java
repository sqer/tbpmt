package pl.sqer.dao.priority;

import javax.ejb.Stateless;

import org.hibernate.Session;

import pl.sqer.dao.common.BaseDAOImpl;
import pl.sqer.dao.entity.priority.PriorityEntity;

/**
 * The PriorityDAOImpl.
 */
@Stateless(name = "priorityDao")
public class PriorityDAOImpl extends BaseDAOImpl<PriorityEntity> implements
		PriorityDAO {

	public PriorityDAOImpl() {
		super(PriorityEntity.class);
	}

	public PriorityDAOImpl(final Session session) {
		super(PriorityEntity.class, session);
	}
}

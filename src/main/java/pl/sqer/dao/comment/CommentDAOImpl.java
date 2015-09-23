package pl.sqer.dao.comment;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import pl.sqer.dao.common.BaseDAOImpl;
import pl.sqer.dao.entity.comment.CommentEntity;

/**
 * The CommentDAOImpl.
 */
@Stateless(name = "commentDao")
public class CommentDAOImpl extends BaseDAOImpl<CommentEntity> implements
		CommentDAO {

	public CommentDAOImpl() {
		super(CommentEntity.class);
	}

	@SuppressWarnings(value = "unchecked")
	@Override
	public List<CommentEntity> findByUser(final Integer userId) {
		final Query query = getNamedQuery("findByUser");
		query.setInteger("userId", userId);
		return query.list();
	}

	@SuppressWarnings(value = "unchecked")
	@Override
	public List<CommentEntity> findByTask(final Integer taskId) {
		final Query query = getNamedQuery("findByTask");
		query.setInteger("taskId", taskId);
		return query.list();
	}

}

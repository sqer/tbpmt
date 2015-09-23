package pl.sqer.dao.taskTime;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import pl.sqer.dao.common.BaseDAOImpl;
import pl.sqer.dao.entity.taskTime.TaskTimeEntity;

/**
 * The TaskTimeDAOImpl.
 */
@Stateless(name = "taskTimeDao")
public class TaskTimeDAOImpl extends BaseDAOImpl<TaskTimeEntity> implements
		TaskTimeDAO {

	public TaskTimeDAOImpl() {
		super(TaskTimeEntity.class);
	}

	@SuppressWarnings(value = "unchecked")
	@Override
	public List<TaskTimeEntity> findByUser(final Integer userId) {
		final Query query = getNamedQuery("findByUser");
		query.setInteger("userId", userId);
		return query.list();
	}

	@SuppressWarnings(value = "unchecked")
	@Override
	public List<TaskTimeEntity> findByTask(final Integer taskId) {
		final Query query = getNamedQuery("findByTask");
		query.setInteger("taskId", taskId);
		return query.list();
	}

}

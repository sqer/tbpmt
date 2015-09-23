package pl.sqer.dao.task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import org.hibernate.Query;

import pl.sqer.dao.common.BaseDAOImpl;
import pl.sqer.dao.entity.task.TaskEntity;
import pl.sqer.dto.resolution.ResolutionEnum;
import pl.sqer.dto.type.TypeEnum;

/**
 * The TaskDAOImpl.
 */
@Stateless(name = "taskDao")
public class TaskDAOImpl extends BaseDAOImpl<TaskEntity> implements TaskDAO {

	public TaskDAOImpl() {
		super(TaskEntity.class);
	}

	@SuppressWarnings(value = "unchecked")
	@Override
	public List<TaskEntity> findByUser(final Integer userId) {
		final Query query = getNamedQuery("findByUser");
		query.setInteger("userId", userId);
		return query.list();
	}

	@Override
	public Long selectNextTaskNumber() {
		final Query query = getNamedQuery("selectTaskNumber");
		final Long number = (Long) query.uniqueResult();
		if (number != null) {
			return number + 1;
		} else {
			return new Long(1);
		}

	}

	@Override
	public Long countByResolution(final ResolutionEnum resolution) {
		final Query query = getNamedQuery("countByResolution");
		query.setString("resolution", resolution.getName());
		return (Long) query.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TaskEntity> findByUserAndResolution(final Integer userId,
			final ResolutionEnum resolution) {
		final Query query = getNamedQuery("findByUserAndResolution");
		query.setInteger("userId", userId);
		query.setString("resolution", resolution.getName());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TaskEntity> findByResolution(
			final List<ResolutionEnum> resolutions) {
		final Set<String> resolutionsNames = new HashSet<String>();
		for (final ResolutionEnum item : resolutions) {
			resolutionsNames.add(item.getName());
		}

		final Query query = getNamedQuery("findByResolution");
		query.setParameterList("resolutions", resolutionsNames);
		return query.list();
	}

	@Override
	public Long countByType(final TypeEnum type) {
		final Query query = getNamedQuery("countByType");
		query.setString("type", type.getName());
		return (Long) query.uniqueResult();
	}
}

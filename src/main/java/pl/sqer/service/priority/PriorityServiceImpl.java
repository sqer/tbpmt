package pl.sqer.service.priority;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.sqer.dao.entity.priority.PriorityEntity;
import pl.sqer.dao.priority.PriorityDAO;
import pl.sqer.dto.priority.PriorityDto;
import pl.sqer.service.dozer.Dozer;

/**
 * The Class PriorityServiceImpl.
 */
@Stateless(name = "priorityService")
public class PriorityServiceImpl implements PriorityService {

	/** The priority dao. */
	@EJB
	private PriorityDAO priorityDAO;

	/** The dozer. */
	@EJB
	private Dozer dozer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.sqer.service.priority.PriorityService#selectAll()
	 */
	@Override
	public List<PriorityDto> selectAll() {
		final List<PriorityEntity> list = priorityDAO.selectAll();
		final List<PriorityDto> results = new ArrayList<PriorityDto>();
		for (final PriorityEntity entity : list) {
			results.add(dozer.map(entity, PriorityDto.class));
		}

		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.sqer.service.priority.PriorityService#findById(java.lang.Integer)
	 */
	@Override
	public PriorityDto findById(final Integer id) {
		final PriorityEntity entity = priorityDAO.findById(id);
		final PriorityDto priority = dozer.map(entity, PriorityDto.class);
		return priority;
	}
}

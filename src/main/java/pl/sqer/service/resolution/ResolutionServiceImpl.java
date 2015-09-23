package pl.sqer.service.resolution;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.sqer.dao.entity.resolution.ResolutionEntity;
import pl.sqer.dao.resolution.ResolutionDAO;
import pl.sqer.dto.resolution.ResolutionDto;
import pl.sqer.dto.resolution.ResolutionEnum;
import pl.sqer.service.dozer.Dozer;

/**
 * The Class ResolutionServiceImpl.
 */
@Stateless(name = "resolutionService")
public class ResolutionServiceImpl implements ResolutionService {

	/** The resolution dao. */
	@EJB
	private ResolutionDAO resolutionDAO;

	/** The dozer. */
	@EJB
	private Dozer dozer;

	@Override
	public List<ResolutionDto> selectAll() {
		final List<ResolutionEntity> list = resolutionDAO.selectAll();
		final List<ResolutionDto> results = new ArrayList<ResolutionDto>();
		for (final ResolutionEntity entity : list) {
			results.add(dozer.map(entity, ResolutionDto.class));
		}

		return results;
	}

	@Override
	public ResolutionDto findById(final Integer id) {
		final ResolutionEntity entity = resolutionDAO.findById(id);
		final ResolutionDto resolution = dozer.map(entity, ResolutionDto.class);
		return resolution;
	}

	@Override
	public ResolutionDto selectUnresolved() {
		final ResolutionEntity resolution = resolutionDAO
				.findByName(ResolutionEnum.UNRESOLVED.getName());
		if (resolution != null) {
			return dozer.map(resolution, ResolutionDto.class);
		} else {
			return null;
		}
	}
}

package pl.sqer.service.type;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.sqer.dao.entity.type.TypeEntity;
import pl.sqer.dao.type.TypeDAO;
import pl.sqer.dto.type.TypeDto;
import pl.sqer.service.dozer.Dozer;

/**
 * The Class TaskServiceImpl.
 */
@Stateless(name = "typeService")
public class TypeServiceImpl implements TypeService {

	/** The type dao. */
	@EJB(name = "typeDao")
	private TypeDAO typeDAO;

	/** The dozer. */
	@EJB(name = "dozer")
	private Dozer dozer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.sqer.service.type.TypeService#selectAll()
	 */
	@Override
	public List<TypeDto> selectAll() {
		final List<TypeEntity> list = typeDAO.selectAll();
		final List<TypeDto> results = new ArrayList<TypeDto>();
		for (final TypeEntity entity : list) {
			results.add(dozer.map(entity, TypeDto.class));
		}

		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.sqer.service.type.TypeService#findById(java.lang.Integer)
	 */
	@Override
	public TypeDto findById(final Integer id) {
		final TypeEntity entity = typeDAO.findById(id);
		final TypeDto type = dozer.map(entity, TypeDto.class);
		return type;
	}
}

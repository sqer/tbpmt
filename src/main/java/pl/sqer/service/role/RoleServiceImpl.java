package pl.sqer.service.role;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.sqer.dao.entity.role.RoleEntity;
import pl.sqer.dao.role.RoleDAO;
import pl.sqer.dto.role.RoleDto;
import pl.sqer.service.dozer.Dozer;

/**
 * The Class RoleServiceImpl.
 */
@Stateless(name = "roleService")
public class RoleServiceImpl implements RoleService {

	/** The role dao. */
	@EJB(name = "roleDao")
	private RoleDAO roleDAO;

	/** The dozer. */
	@EJB(name = "dozer")
	private Dozer dozer;

	@Override
	public List<RoleDto> selectAll() {
		final List<RoleEntity> list = roleDAO.selectAll();
		final List<RoleDto> results = new ArrayList<RoleDto>();
		for (final RoleEntity entity : list) {
			results.add(dozer.map(entity, RoleDto.class));
		}

		return results;
	}

	@Override
	public RoleDto findById(final Integer id) {
		final RoleEntity entity = roleDAO.findById(id);
		final RoleDto role = dozer.map(entity, RoleDto.class);
		return role;
	}

}

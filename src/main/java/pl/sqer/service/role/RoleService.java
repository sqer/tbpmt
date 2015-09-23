package pl.sqer.service.role;

import java.util.List;

import javax.ejb.Local;

import pl.sqer.dto.role.RoleDto;

/**
 * The Interface RoleService.
 */
@Local
public interface RoleService {

	/**
	 * Select all.
	 *
	 * @return the list
	 */
	List<RoleDto> selectAll();

	/**
	 * Find by id
	 *
	 * @param id
	 *            the role id
	 *
	 * @return task dto
	 */
	RoleDto findById(Integer id);

}

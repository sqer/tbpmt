package pl.sqer.service.type;

import java.util.List;

import javax.ejb.Local;

import pl.sqer.dto.type.TypeDto;

/**
 * The Interface TypeService.
 */
@Local
public interface TypeService {

	/**
	 * Select all.
	 *
	 * @return the list
	 */
	List<TypeDto> selectAll();

	/**
	 * Find by id
	 * 
	 * @param id
	 *            the type id
	 * 
	 * @return type dto
	 */
	TypeDto findById(Integer id);

}

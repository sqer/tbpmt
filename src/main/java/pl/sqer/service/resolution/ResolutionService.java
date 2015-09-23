package pl.sqer.service.resolution;

import java.util.List;

import javax.ejb.Local;

import pl.sqer.dto.resolution.ResolutionDto;

/**
 * The Interface ResolutionService.
 */
@Local
public interface ResolutionService {

	/**
	 * Select all.
	 *
	 * @return the list
	 */
	List<ResolutionDto> selectAll();

	/**
	 * Find by id
	 *
	 * @param id
	 *            the priority id
	 *
	 * @return resolution dto
	 */
	ResolutionDto findById(Integer id);

	/**
	 * Select unresolved resolution
	 *
	 * @return {@link ResolutionDto}
	 */
	ResolutionDto selectUnresolved();

}

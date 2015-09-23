package pl.sqer.service.priority;

import java.util.List;

import javax.ejb.Local;

import pl.sqer.dto.priority.PriorityDto;

/**
 * The Interface PriorityService.
 */
@Local
public interface PriorityService {

	/**
	 * Select all.
	 *
	 * @return the list
	 */
	List<PriorityDto> selectAll();

	/**
	 * Find by id
	 * 
	 * @param id
	 *            the priority id
	 * 
	 * @return priority dto
	 */
	PriorityDto findById(Integer id);

}

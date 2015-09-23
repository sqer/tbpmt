package pl.sqer.service.dozer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.dozer.DozerBeanMapper;

import pl.sqer.dto.common.TBPMTDto;

/**
 * The Class Dozer.
 */
@Stateless(name = "dozer")
public class Dozer extends DozerBeanMapper implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;

	/** The mapping files. */
	List<String> mappingFiles = new ArrayList<String>();

	/**
	 * Instantiates a new dozer.
	 */
	public Dozer() {
		mappingFiles.add("/mappings/default.xml");
		this.setMappingFiles(mappingFiles);
	}

	public <T> List<T> map(final List<? extends TBPMTDto> source,
			final Class<T> targetClass) {
		final List<T> results = new ArrayList<T>();
		for (final Object toMap : source) {
			results.add(this.map(toMap, targetClass));
		}
		return results;
	}
}

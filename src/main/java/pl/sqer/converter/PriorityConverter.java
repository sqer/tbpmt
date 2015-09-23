package pl.sqer.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pl.sqer.dto.priority.PriorityDto;
import pl.sqer.service.priority.PriorityService;

/**
 * The Class PriorityConverter.
 */
@ManagedBean
@FacesConverter("priorityConverter")
public class PriorityConverter implements Converter {

	/** The priority service. */
	@EJB
	PriorityService priorityService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(final FacesContext fc, final UIComponent uic,
			final String value) {
		if (value != null && value.trim().length() > 0) {
			return priorityService.findById(Integer.parseInt(value));
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(final FacesContext fc, final UIComponent uic,
			final Object object) {
		if (object != null) {
			return String.valueOf(((PriorityDto) object).getId());
		} else {
			return null;
		}
	}
}
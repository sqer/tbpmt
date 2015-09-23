package pl.sqer.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pl.sqer.dto.role.RoleDto;
import pl.sqer.service.role.RoleService;

/**
 * The Class RoleConverter.
 */
@ManagedBean
@FacesConverter("roleConverter")
public class RoleConverter implements Converter {

	/** The role service. */
	@EJB
	RoleService roleService;

	@Override
	public Object getAsObject(final FacesContext fc, final UIComponent uic,
			final String value) {
		if (value != null && value.trim().length() > 0) {
			return roleService.findById(Integer.parseInt(value));
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(final FacesContext fc, final UIComponent uic,
			final Object object) {
		if (object != null) {
			return String.valueOf(((RoleDto) object).getId());
		} else {
			return null;
		}
	}
}
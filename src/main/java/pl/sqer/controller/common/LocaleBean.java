package pl.sqer.controller.common;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * The Class LocaleBean.
 */
@SessionScoped
@ManagedBean(name = "localeBean")
public class LocaleBean extends AbstractController {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;

	/** The Constant DEFAULT_DATE_FORMAT. */
	private final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";

	/** The locale. */

	private Locale locale = Locale.getDefault();

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(final Locale locale) {
		this.locale = locale;
	}

	/**
	 * @return the defaultDateFormat
	 */
	public String getDefaultDateFormat() {
		return DEFAULT_DATE_FORMAT;
	}

}

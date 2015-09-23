package pl.sqer.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class DateConverter.
 */
@ManagedBean(name = "dateConverter")
public class DateConverter {

	/** The Constant DEFAULT_DATE_FORMAT. */
	private final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";

	/** The Constant DEFAULT_DATE_FORMAT_WITH_TIME. */
	private final String DEFAULT_DATE_FORMAT_WITH_TIME = "yyyy/MM/dd hh:mm:ss";

	/**
	 * Convert date to string
	 *
	 * @param date
	 *            the date to convert
	 * @param time
	 *            add time to string
	 * @return string from date
	 */
	public String convertToString(final Date date, final boolean time) {
		if (date != null) {
			return new SimpleDateFormat(time ? DEFAULT_DATE_FORMAT_WITH_TIME
					: DEFAULT_DATE_FORMAT).format(date);
		} else {
			return StringUtils.EMPTY;
		}
	}

}

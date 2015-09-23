package pl.sqer.dao.common;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {

	/**
	 * Gets message from resource bundle
	 *
	 * @param key
	 *            the message key
	 * @return the message
	 */
	public static String getMessage(final String key) {
		final ResourceBundle bundle = ResourceBundle.getBundle("messages",
				FacesContext.getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}

	/**
	 * Shows message with given severity, message and details message for given
	 * component
	 *
	 * @param componentId
	 * @param severity
	 * @param message
	 * @param details
	 */
	private static void showMessage(final String componentId,
			final FacesMessage.Severity severity, final String message,
			final String details) {
		FacesContext.getCurrentInstance().addMessage(componentId,
				new FacesMessage(severity, getMessage(message), details));
	}

	/**
	 * Shows message with given severity, message and details message for given
	 * component
	 *
	 * @param componentId
	 *            the component for message
	 * @param severity
	 *            the severity
	 * @param message
	 *            the message
	 * @param details
	 *            the details message
	 * @param keep
	 *            the keep messages flag
	 */
	private static void showMessage(final String componentId,
			final FacesMessage.Severity severity, final String message,
			final String details, final boolean keep) {
		FacesContext.getCurrentInstance().addMessage(componentId,
				new FacesMessage(severity, getMessage(message), details));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(keep);
	}

	/**
	 * Shows global warn message
	 *
	 * @param message
	 *            the message key
	 */
	public static void showWarnMessage(final String message) {
		showMessage(null, FacesMessage.SEVERITY_WARN, message, null);
	}

	/**
	 * Shows warn message
	 *
	 * @param componentId
	 *            the component id
	 * @param title
	 *            the message title
	 * @param key
	 *            the message key
	 */
	public static void showWarnMessage(final String componentId,
			final String message, final String details) {
		showMessage(componentId, FacesMessage.SEVERITY_WARN, message, details);
	}

	/**
	 * Shows warn message
	 *
	 * @param componentId
	 *            the component id
	 * @param key
	 *            the message key
	 */
	public static void showWarnMessage(final String componentId,
			final String message) {
		showWarnMessage(componentId, message, null);
	}

	/**
	 * Shows info message
	 *
	 * @param componentId
	 *            the component id
	 * @param title
	 *            the message title
	 * @param key
	 *            the message key
	 */
	public static void showInfoMessage(final String componentId,
			final String message, final String details) {
		showMessage(componentId, FacesMessage.SEVERITY_INFO, message, details);
	}

	/**
	 * Shows info message
	 *
	 * @param componentId
	 *            the component id
	 * @param key
	 *            the message key
	 */
	public static void showInfoMessage(final String componentId,
			final String message) {
		showInfoMessage(componentId, message, null);
	}

	/**
	 * Shows info message
	 *
	 * @param componentId
	 *            the component id
	 * @param key
	 *            the message key
	 *
	 * @param keep
	 *            the keep messages flag
	 */
	public static void showInfoMessage(final String componentId,
			final String message, final boolean keep) {
		showMessage(componentId, FacesMessage.SEVERITY_INFO, message, null,
				keep);
	}

	/**
	 * Shows error message
	 *
	 * @param componentId
	 *            the component id
	 * @param title
	 *            the message title
	 * @param key
	 *            the message key
	 */
	public static void showErrorMessage(final String componentId,
			final String message, final String details) {
		showMessage(componentId, FacesMessage.SEVERITY_ERROR, message, details);
	}

	/**
	 * Shows error message
	 *
	 * @param componentId
	 *            the component id
	 * @param key
	 *            the message key
	 */
	public static void showErrorMessage(final String componentId,
			final String message) {
		showInfoMessage(componentId, message, null);
	}
}

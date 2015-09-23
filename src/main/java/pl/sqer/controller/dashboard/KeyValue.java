package pl.sqer.controller.dashboard;

import java.io.Serializable;

/**
 * The Class KeyValue.
 */
public class KeyValue implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;

	/** The key. */
	String key;

	/** The value. */
	String value;

	/**
	 * Instantiates a new key value.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public KeyValue(final String key, final String value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            the new key
	 */
	public void setKey(final String key) {
		this.key = key;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *            the new value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

}

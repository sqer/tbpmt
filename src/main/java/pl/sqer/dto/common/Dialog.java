package pl.sqer.dto.common;

/**
 * The Class Dialog.
 */
public class Dialog extends org.primefaces.component.dialog.Dialog {

	/**
	 * Show the dialog.
	 */
	public void show() {
		setVisible(true);
	}

	/**
	 * Hide the dialog.
	 */
	public void hide() {
		setVisible(false);
	}
}

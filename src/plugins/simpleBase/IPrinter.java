package plugins.simpleBase;

import javax.swing.JPanel;

import platform.IPlugin;

/**
 * 
 * Plugin that is of type "Printer".
 * Plugin to display the events collection
 */
public interface IPrinter  extends IPlugin {
	/**
	 * Returns events display.
	 * @param a the agenda to which the events collection 
	 * @param frame the application JPanel for refreshing
	 * @return the new JPanel with the events collection
	 */
	JPanel display(IAgenda a);
}

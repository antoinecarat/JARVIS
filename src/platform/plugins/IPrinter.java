package platform.plugins;

import javax.swing.JPanel;

import client.IAgenda;
import plugins.simpleBase.AgendaFrame;

/**
 * 
 * Plugin that is of type "Printer".
 * Plugin to display the events collection
 */
public interface IPrinter {
	/**
	 * Returns events display.
	 * @param a the agenda to which the events collection 
	 * @param frame the application JPanel for refreshing
	 * @return the new JPanel with the events collection
	 */
	JPanel display(IAgenda a, AgendaFrame frame);
}

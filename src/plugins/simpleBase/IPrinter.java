package plugins.simpleBase;

import javax.swing.JPanel;

import platform.IPlugin;

/**
 * Defines a plugin that display an agenda in a {@link JPanel}.
 */
public interface IPrinter  extends IPlugin {
	/**
	 * Returns events display.
	 * @param a the agenda to which the events collection
	 * @return the new JPanel with the events collection
	 */
	JPanel display(IAgenda a);
}

package plugins.simpleBase;

import platform.IPlugin;

/**
 * Defines a plugin that modify events in an agenda.
 */
public interface IModifier  extends IPlugin  {

	/**
	 * Changes (modify, delete...) a event of the agenda
	 * @param a the agenda to which the event is modify
	 * @param e the event modifies
	 */
	void modify(IAgenda a, IEvent e);
	
}
package platform.plugins;

import plugins.simpleBase.AgendaFrame;

/**
 * Plugin that is of type "creator". 
 * Plugin to create a new event.
 */
public interface ICreator {

	/**
	 * Creates a new event for a agenda.
	 * @param a the agenda to which the event is add
	 */
	void create(AgendaFrame a);
	
}

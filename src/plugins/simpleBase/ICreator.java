package plugins.simpleBase;

import platform.IPlugin;

/**
 * Plugin that is of type "creator". 
 * Plugin to create a new event.
 */
public interface ICreator  extends IPlugin  {

	/**
	 * Creates a new event for a agenda.
	 * @param a the agenda to which the event is add
	 */
	void create(IAgenda a);
	
}

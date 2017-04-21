package plugins.simpleBase;

import platform.IPlugin;

/**
 * Defines a plugin that create events in an agenda.
 */
public interface ICreator  extends IPlugin  {

	/**
	 * Create new event(s) in a given agenda.
	 * @param a the agenda
	 */
	void create(IAgenda a);
	
}

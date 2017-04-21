package platform;

/**
 * Defines a plugin and his minimal features.
 */
public interface IPlugin {

	/**
	 * Method called when the plugin is loaded.
	 */
	void startUp();
	
	/**
	 * React to an event.
	 * @param event The raised event.
	 * @param args A useful object linked to the event.
	 */
	void handleEvent(String event, Object args);
	
}

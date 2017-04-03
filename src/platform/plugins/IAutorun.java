package platform.plugins;

/**
 * Plugin that runs at the launch of the platform
 */
public interface IAutorun {

	/**
	 * Runs the plugin.
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	void run() throws ClassNotFoundException, InstantiationException, IllegalAccessException;
	
}

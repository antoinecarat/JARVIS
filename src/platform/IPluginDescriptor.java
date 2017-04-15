package platform;

import java.util.List;
import java.util.Map;

/**
 * 
 */
public interface IPluginDescriptor {

	/**
	 * Returns a map of properties of a plugin
	 * 				  (name, verbose, about, class, interface,
	 * 				   autorun, singleton ...)
	 * @return map of properties 
	 */
	Map<String, Object> getProperties();

	/**
	 * Adds a new property of the plugin to the map 
	 * @param key name of the property (name, verbose...)
	 * @param value corresponding value 
	 */
	void addProperty(String key, Object value);

	/**
	 * Removes a property of the plugin 
	 * @param key name of the property (name, verbose...)
	 */
	void removeProperty(String key);

	/**
	 * Returns the state of the plugin (running, available,failed) 
	 * @return state of the plugin
	 */
	PluginState getState();
	
	/**
	 * Changes the state of the plugin
	 * @param state the new state 
	 */
	void setState(PluginState state);
	
	public String toString();

	/**
	 * Returns a list of instances of the plugin
	 * @return list of instances
	 */
	List<IPlugin> getInstances();
	
	/**
	 * Adds a new instance of this plugin
	 * @param o the new instance 
	 */
	void addInstance(IPlugin o);

	void removeInstance(IPlugin o);
	
}
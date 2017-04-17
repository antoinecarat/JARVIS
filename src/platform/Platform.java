package platform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.yaml.snakeyaml.Yaml;

/**
 *  Defines a static platform which can manage plugins.
 */
public class Platform {

	private static Map<String, List<IPlugin>> eventSubscribers = null;
	
	private static List<IPluginDescriptor> pluginDescript;
	
	/**
	 * Returns the pluginDescriptor list.
	 * @return pluginDescript the list of pluginDescriptor.
	 */
	public static List<IPluginDescriptor> getPluginDescript() {
		return pluginDescript;
	}

	/**
	 * Sets the pluginDescriptor list.
	 * @param pluginDescript the new pluginDescriptor list.
	 */
	public static void setPluginDescript(List<IPluginDescriptor> pluginDescript) {
		Platform.pluginDescript = pluginDescript;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {		

		loadPluginDescriptors();
		
		// Run autoruns
		for (IPluginDescriptor plugin : pluginDescript) {
			if(plugin.getProperties().get("autorun").equals(true)){
				Thread obj = (Thread) loadPlugin(plugin, IAutorun.class);
				obj.start();
			}
		}		
	}

	/**
	 * Returns the list of plugins that should implement the need class.
	 * @param need the class which should be implemented.
	 * @return plugins the list of plugins that should implement need.
	 */
	public static List<IPluginDescriptor> getPlugins(Class<?> need) {

		List<IPluginDescriptor> plugins = new ArrayList<IPluginDescriptor>();
		
		for (IPluginDescriptor plugin : pluginDescript) {
			
			String interfacePath = (String) plugin.getProperties().get("interface");

			if(interfacePath.equals(need.getName())){
				plugins.add(plugin);
			}
		}
		
		return plugins;
	}
	
	/**
	 * Returns the list of plugins that should implement the need class.
	 * @param need the class which should be implemented.
	 * @param properties the map of properties to be matched.
	 * @return plugins the list of plugins that should implement need.
	 */
	public static List<IPluginDescriptor> getPlugins(Class<?> need, Map<String, Object> properties) {

		List<IPluginDescriptor> plugins = new ArrayList<IPluginDescriptor>();
		
		for (IPluginDescriptor plugin : pluginDescript) {
			
			String interfacePath = (String) plugin.getProperties().get("interface");

			if(interfacePath.equals(need.getName())){
				
				boolean matches = true;
				for (Object key : properties.keySet()){
					matches = matches && (plugin.getProperties().get(key).equals(properties.get(key)));
				}
				if (matches){
					plugins.add(plugin);
				}
			}
		}
		
		return plugins;
	}

	/**
	 * Loads all the pluginDescriptors corresponding to plugins listed in config.yaml.
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private static void loadPluginDescriptors() throws FileNotFoundException {
		InputStream input = new FileInputStream(new File("config.yaml"));
	    Yaml yaml = new Yaml();
	    pluginDescript = new ArrayList<IPluginDescriptor>();
	    
		Map<String, Object> map = (Map<String, Object>) yaml.load(input);
		List<String> applis = (List<String>) map.get("autoruns");
		
		for (String p : applis) {
			String[] tmp = p.split(Pattern.quote("."));
			String pluginFile = "pluginConfig/" + tmp[tmp.length - 1] + ".yaml";
			
			loadPluginDescriptorFromFile(pluginFile);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void loadPluginDescriptorFromFile(String pluginFile) throws FileNotFoundException {
		Yaml yaml = new Yaml();
		InputStream pluginConf;
		
		pluginConf = new FileInputStream(new File(pluginFile));
		Map<String, Object> prop = (Map<String, Object>) yaml.load(pluginConf);
		if (prop.containsKey("name") && prop.containsKey("about")
				&& prop.containsKey("class") && prop.containsKey("interface")
				&& prop.containsKey("autorun") && prop.containsKey("singleton") 
				&& prop.containsKey("killable")){
				
				if (prop.containsKey("dependencies")){
					for(String d : (List<String>) prop.get("dependencies")){
						String[] tmp = d.split(Pattern.quote("."));
						String depFile = "pluginConfig/" + tmp[tmp.length - 1] + ".yaml";
						loadPluginDescriptorFromFile(depFile);
					}
					prop.remove("dependencies");
				}
				pluginDescript.add(new PluginDescriptor(prop));
		} else {
			System.out.println("Missing essential property in " + pluginFile);
		}
	}

	/**
	 * Loads a instance of a plugin.
	 * @param iPluginDescriptor the pluginDescriptor which should be loaded.
	 * @param need the class which should be implemented by the plugin.
	 * @return obj the instance of the plugin.
	 */
	public static Object loadPlugin(IPluginDescriptor iPluginDescriptor, Class<?> need) {
		
		IPlugin obj = null;
		
		if(iPluginDescriptor.getState() == PluginState.AVAILABLE){
			try {
				Class<?> cl = Class.forName((String) iPluginDescriptor.getProperties().get("class"));
				
				if(need.isAssignableFrom(cl)){
					obj = (IPlugin) cl.newInstance();
					iPluginDescriptor.addInstance(obj);
					iPluginDescriptor.setState(PluginState.RUNNING);
					raiseEvent("plugin.launched", null);
				}else{
					throw new UnassignableException();
				}
								
			} catch (ClassNotFoundException | UnassignableException | InstantiationException | IllegalAccessException e) {
				iPluginDescriptor.setState(PluginState.FAILED);
				raiseEvent("plugin.crashed", null);
			}
		} else if (iPluginDescriptor.getState() == PluginState.RUNNING) {
			if (iPluginDescriptor.getProperties().get("singleton").equals(true)){
				obj = iPluginDescriptor.getInstances().get(0);
				raiseEvent("plugin.launched", null);
			} else {
				try {
					Class<?> cl = Class.forName((String) iPluginDescriptor.getProperties().get("class"));
					
					if(need.isAssignableFrom(cl)){
						obj = (IPlugin) cl.newInstance();
						iPluginDescriptor.addInstance(obj);
						raiseEvent("plugin.launched", null);
					}else{
						throw new UnassignableException();
					}
					
				} catch (ClassNotFoundException | UnassignableException | InstantiationException | IllegalAccessException e) {
					iPluginDescriptor.setState(PluginState.FAILED);
					raiseEvent("plugin.crashed", null);
				}
			}
		}
		return obj;
	}
	
	/**
	 * Kills an instance of the plugin.
	 * @param plugin the plugin to be killed.
	 */
	public static void killPlugin(IPlugin plugin){
		for (IPluginDescriptor pluginsDesc : pluginDescript){
			if (plugin.getClass().getName().equals(pluginsDesc.getProperties().get("class"))){
				if (pluginsDesc.getProperties().get("killable").equals(true)){
					pluginsDesc.removeInstance(plugin);
					if (pluginsDesc.getInstances().size() == 0){
						pluginsDesc.setState(PluginState.AVAILABLE);
					}
					Platform.raiseEvent("plugin.killed", null);
				} else {
					System.out.println("This plugin cannot be killed.");
				}
			}
		}
	}

	/**
	 * Add a plugin to an event's subscribers list.
	 * @param event the event to be subscribed to.
	 * @param plugin the plugin to be added to subscribers.
	 */
	public static void subscribeEvent(String event, IPlugin plugin){
		if (eventSubscribers == null){
			eventSubscribers = new HashMap<String, List<IPlugin>>();
		}
		if (eventSubscribers.get(event) == null){
			List<IPlugin> list = new ArrayList<IPlugin>();
			list.add(plugin);
			eventSubscribers.put(event, list);
		} else {
			eventSubscribers.get(event).add(plugin);
		}
	}
	
	public static void raiseEvent(String event, List<Object> args){
		if (eventSubscribers == null){
			eventSubscribers = new HashMap<String, List<IPlugin>>();
		}
		for (String key : eventSubscribers.keySet()){
			String cat = event.split(Pattern.quote("."))[0];
			if (key.equals(event) || key.equals(cat)){
				for (IPlugin plugin : eventSubscribers.get(key)){
					plugin.handleEvent(event);
				}
			}
		}
	}
}

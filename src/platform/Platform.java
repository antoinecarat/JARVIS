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

import client.UnassignableException;
import platform.plugins.IAutorun;
import platform.plugins.IPlugin;

public class Platform {

	private static Map<String, List<IPlugin>> eventSubscribers = null;
	
	private static List<IPluginDescriptor> pluginDescript;
	
	public static List<IPluginDescriptor> getPluginDescript() {
		return pluginDescript;
	}

	public static void setPluginDescript(List<IPluginDescriptor> pluginDescript) {
		Platform.pluginDescript = pluginDescript;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {		

		loadPluginDescriptors();
		// Run autoruns
		for (IPluginDescriptor plugin : pluginDescript) {
			if(plugin.getProperties().get("autorun").equals("True")){
				IAutorun obj = (IAutorun) loadPlugin(plugin, IAutorun.class);

				obj.run();
			}
		}		
	}

	public static List<IPluginDescriptor> getExtensions(Class<?> need) throws ClassNotFoundException {

		List<IPluginDescriptor> plugins = new ArrayList<IPluginDescriptor>();
		
		for (IPluginDescriptor plugin : pluginDescript) {
			
			String interfacePath = plugin.getProperties().get("interface");

			if(interfacePath.equals(need.getName())){
				plugins.add(plugin);
			}
		}
		
		return plugins;
	}
	
	public static List<IPluginDescriptor> getExtensions(Class<?> need, Map<String, Object> properties) throws ClassNotFoundException {

		List<IPluginDescriptor> plugins = new ArrayList<IPluginDescriptor>();
		
		for (IPluginDescriptor plugin : pluginDescript) {
			
			String interfacePath = plugin.getProperties().get("interface");

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

	private static void loadPluginDescriptors() throws FileNotFoundException {

		
		InputStream input = new FileInputStream(new File("config.yaml"));
	    Yaml yaml = new Yaml();
	    Map<String, Object> map = (Map<String, Object>) yaml.load(input);
		List<String> plugins = (List<String>) map.get("plugins");

		IPluginDescriptor desc;
		pluginDescript = new ArrayList<IPluginDescriptor>();
		
		for (String p : plugins) {
			String[] tmp = p.split(Pattern.quote("."));
			String pluginFile = "pluginConfig/" + tmp[tmp.length - 1] + ".yaml";
			
			InputStream pluginConf = new FileInputStream(new File(pluginFile));
			Map<String, String> prop = (Map<String, String>) yaml.load(pluginConf);
			desc = new PluginDescriptor(prop);
			pluginDescript.add(desc);
			
		}
	}

	public static Object loadPlugin(IPluginDescriptor iPluginDescriptor, Class<?> need) {
		
		Object obj = null;
		
		if(iPluginDescriptor.getState() == PluginState.AVAILABLE){
			try {
				Class<?> cl = Class.forName(iPluginDescriptor.getProperties().get("class"));
				
				if(need.isAssignableFrom(cl)){
					obj = cl.newInstance();
					iPluginDescriptor.addInstance(obj);
				}else{
					throw new UnassignableException();
				}
				iPluginDescriptor.setState(PluginState.RUNNING);
				
			} catch (ClassNotFoundException | UnassignableException | InstantiationException | IllegalAccessException e) {
				iPluginDescriptor.setState(PluginState.FAILED);
			}
		} else if (iPluginDescriptor.getState() == PluginState.RUNNING) {
			if (iPluginDescriptor.getProperties().get("singleton").equals("True")){
				return iPluginDescriptor.getInstances().get(0);
			} else {
				try {
					Class<?> cl = Class.forName(iPluginDescriptor.getProperties().get("class"));
					
					if(need.isAssignableFrom(cl)){
						obj = cl.newInstance();
						iPluginDescriptor.addInstance(obj);
					}else{
						throw new UnassignableException();
					}
					iPluginDescriptor.setState(PluginState.RUNNING);
					
				} catch (ClassNotFoundException | UnassignableException | InstantiationException | IllegalAccessException e) {
					iPluginDescriptor.setState(PluginState.FAILED);
				}
			}
		}
		return obj;
	}

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
	
	public static void raiseEvent(String event){
		for (String key : eventSubscribers.keySet()){
			if (key.equals(event)){
				for (IPlugin plugin : eventSubscribers.get(key)){
					plugin.handleEvent(event);
				}
			}
		}
	}
}

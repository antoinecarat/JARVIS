package client;

import java.util.HashMap;
import java.util.Map;

public class PluginDescriptor implements IPluginDescriptor {

	Map<String, String> properties;
	
	public PluginDescriptor(Map<String, String> prop) {
		this.properties = prop;
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}
	
	public void addProperty(String key, String value){
		properties.put(key, value);
	}
	
	public void removeProperty(String key){
		properties.remove(key);
	}
	
	//TODO : add state and singleton
	
}

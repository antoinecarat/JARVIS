package platform;

import java.util.Map;

import client.PluginState;

public interface IPluginDescriptor {

	Map<String, String> getProperties();

	void addProperty(String key, String value);

	void removeProperty(String key);

	PluginState getState();
	
	void setState(PluginState state);
	
	public String toString();
	
}
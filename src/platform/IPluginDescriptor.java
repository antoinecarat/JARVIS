package platform;

import java.util.List;
import java.util.Map;

public interface IPluginDescriptor {

	Map<String, String> getProperties();

	void addProperty(String key, String value);

	void removeProperty(String key);

	PluginState getState();
	
	void setState(PluginState state);
	
	public String toString();

	List<Object> getInstances();
	
	void addInstance(Object o);
	
}